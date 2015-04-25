package com.example.asus.myapplication;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * MainActivity2 will be initiated if the user chooses to search by department and department id.
 * This activity queries the user for inputs and performs an algorithm to determine if the search has
 * a hit. It then executes an appropriate action corresponding to whether or not there is a hit.
 * Note that there can be multiple hits, which will return multiple results.
 * @file MainActivity2.java
 * @author Steven Tang
 */

public class MainActivity2 extends ActionBarActivity {

    private EditText Department;
    private EditText classId;
    private final String EXTRA_MESSAGE = "com.example.asus.myapplication.MESSAGE";
    private final String URL = "http://10.0.2.2:";
    private final String classPath = "/class/all";
    private final String roomPath = "/room/all";
    private final int port = 27017;
    private final String space = " ";
    private String jsonData = "";
    private String roomId = null;

    List<String> listDataHeader;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    HashMap<String, List<String>> listDataChild;

    private static final String TAG = MainActivity2.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);

        //overrides thread policy to allow network operations
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        expListView = (ExpandableListView) findViewById(R.id.expandableListView);

        prepareListData();

        //use list adapter
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    /**
     * Creates list of all the class room names and inserts it into expandable list view
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Department Class Mapping");

        // Adding child data
        List<String> ClassRoomNames = getAllClassNames();
        // Using hash set to prevent duplicate entries
        Set<String> hs = new HashSet<>();
        hs.addAll(ClassRoomNames);
        ClassRoomNames.clear();
        ClassRoomNames.addAll(hs);

        listDataChild.put(listDataHeader.get(0), ClassRoomNames); // Header, Child data

    }
    /**
     * readJson will connect to our REST API, where we can obtain json data regarding class
     * and room information as a string
     * @param path the path url, either class/all for classroom object or room/all for room object
     */
    private void readJson(String path){
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(URL + Integer.toString(port) + path);
        try{
            HttpResponse response = httpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
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
            Log.d(TAG, " http request failed at " + URL + Integer.toString(port) + path);
            e.printStackTrace();
        }
        httpGet.abort();
    }

    /**
     * Obtains the user input for department name.
     * @return String
     */
    private String getDepartmentName(){
        Department = (EditText)findViewById(R.id.editText5);
        String message = Department.getText().toString();
        if (message == null || message.isEmpty())
            return null;
        return message;
    }

    /**
     * Obtains the user input for department id.
     * @return String
     */
    private String getClassId(){
        classId = (EditText)findViewById(R.id.editText4);
        String message = classId.getText().toString();
        if (message == null || message.isEmpty())
            return null;
        return message;
    }

    /**
     * Casts string as JSONArray to perform a search on specific fields, noticeably the roomId
     * that corresponds to both tables within our database.
     * @param JsonField1 Either deptName or classroom wing
     * @param JsonField2 Either deptId or classroom number
     * @return String
     */
    private String getJsonData (String JsonField1, String JsonField2){
        try {
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
                String dept = curr.getString("Dept");
                String classNum = curr.getString("ClassNum");
                String value = dept + space + classNum;
                if (value.equalsIgnoreCase(JsonField1 + space + JsonField2)) {
                    roomId = curr.getString("RoomID");
                    //return some value to validate that field has been found
                    return value;
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

    /**
     * Map entries Name, dept and classNum into list format (e.g. Name+dept -> classNum)
     * @return List<String>
     */
    private List<String> getAllClassNames(){
        Log.d(TAG, " where is error 0");
        List<String> classNames = new ArrayList<String>();
        readJson(classPath);
        Log.d(TAG, " where is error 1");
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject curr = jsonArray.getJSONObject(i);
                String name = curr.getString("Name");
                String dept = curr.getString("Dept");
                String classNum = curr.getString("ClassNum");
                String value = dept + space + classNum;
                Log.d(TAG, " where is error 2");
                if (name != null)
                    classNames.add(value + " -> " + name);
            }
        }
        catch (JSONException e) {
            Log.d(TAG, " JSON error.");
            e.printStackTrace();
            System.exit(0);
        }
        return classNames;
    }

    /**
     * Executed once the submit button has been clicked. Will query results and see if a match has been found.
     * Initiates a new activity depending on results.
     * @param view Handles screen layouts and interaction with the user
     */
    public void evaluateMessage(View view){
        readJson(classPath);
        String deptName = getDepartmentName();
        String classId = getClassId();
        if (getJsonData(deptName, classId) == null){
            Intent intent = new Intent(this, ErrorActivity.class);
            intent.putExtra(EXTRA_MESSAGE, "MainActivity2");
            startActivity(intent);
        }
        //clear out jsonData
        jsonData = "";
        readJson(roomPath);
        String val = getJsonData(deptName, classId);
        Log.d(TAG, val);

    }

}
