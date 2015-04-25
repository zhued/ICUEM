package com.example.asus.myapplication;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity3 extends ActionBarActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    private final String EXTRA_MESSAGE = "com.example.asus.myapplication.MESSAGE";
    private final String URL = "http://10.0.2.2:";
    private final String classPath = "/class/all";
    private final String roomPath = "/room/all";
    private final int port = 27017;
    private final String space = " ";
    private String jsonData = "";
    private EditText className;
    private String roomId = null;
    private String TAG = MainActivity3.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity3);

        //overrides thread policy to allow network operations
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Class Names");

        // Adding child data
        List<String> ClassRoomNames = getAllClassNames();
        Set<String> hs = new HashSet<>();
        hs.addAll(ClassRoomNames);
        ClassRoomNames.clear();
        ClassRoomNames.addAll(hs);

        listDataChild.put(listDataHeader.get(0), ClassRoomNames); // Header, Child data

    }

    private void readJson(String path){
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(URL + Integer.toString(port) + path);
        try{
            HttpResponse response = httpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            //200 means OK
            if (statusCode == 200){
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonData += line;
                }
                inputStream.close();
            }
        }
        catch (Exception e){
            Log.d(TAG, " http request failed at " + URL + Integer.toString(port) + classPath);
            e.printStackTrace();
        }
        httpGet.abort();
    }

    private List<String> getAllClassNames(){
        List<String> classNames = new ArrayList<String>();
        readJson(classPath);
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject curr = jsonArray.getJSONObject(i);
                String name = curr.getString("Name");
                if (name != null)
                    classNames.add(name);
            }
        }
        catch (JSONException e) {
            Log.d(TAG, " JSON error.");
            e.printStackTrace();
            System.exit(0);
        }
        return classNames;
    }

    private String getJsonData (String JsonField1){
        try {
            if (JsonField1 == null)
                return null;

            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject curr = jsonArray.getJSONObject(i);
                //only goes into this statement the second time getJsonData is called.
                // Attempts to find roomId match for both tables
                if (roomId != null){
                    String id = curr.getString("RoomID");
                        if (id.equalsIgnoreCase(roomId)){
                            String wing = curr.getString("Wing");
                            String roomNum = curr.getString("RoomNum");
                            String value = wing + space + roomNum;
                            return value;
                    }
                    continue;
                }
                String classValue = curr.getString("Name");
                if (JsonField1.equalsIgnoreCase(classValue)) {
                    roomId = curr.getString("RoomID");
                    //return some value to validate that field has been found
                    return roomId;
                }
            }
        }
        catch (JSONException e) {
            Log.d(TAG, " JSON error.");
            e.printStackTrace();
            System.exit(0);
        }
        Log.d(TAG, " should not return null");
        return null;
    }

    public String getClassName() {
        className = (EditText)findViewById(R.id.editText6);
        String message = className.getText().toString();
        if (message == null || message.isEmpty())
            return null;
        return message;
    }

    public void evalMessage(View view){
        //clear out jsonData
        jsonData = "";
        readJson(classPath);
        String classValue = getClassName();
        if (getJsonData(classValue) == null){
            Intent intent = new Intent(this, ErrorActivity.class);
            intent.putExtra(EXTRA_MESSAGE, "MainActivity2");
            startActivity(intent);
        }
        //clear out jsonData
        jsonData = "";
        readJson(roomPath);
        String val = getJsonData(classValue);
        Log.d(TAG, val);

    }

}
