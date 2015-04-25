package com.example.asus.myapplication;

import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.asus.myapplication.R.id.editText;
import android.util.Log;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * MainActivity will be initiated if the user chooses to search by classroom wing and number.
 * This activity queries the user for inputs and performs an algorithm to determine if the search has
 * a hit. It then executes an appropriate action corresponding to whether or not there is a hit.
 * @file MainActivity.java
 * @author Steven Tang
 */

public class MainActivity extends ActionBarActivity {
    private EditText classRoom;
    private EditText classNumber;
    private final String EXTRA_MESSAGE = "com.example.asus.myapplication.MESSAGE";
    private final String URL = "http://10.0.2.2:";
    private final String roomPath = "/room/all";
    private final int port = 27017;
    private final String space = " ";
    private String jsonData = "";

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //overrides thread policy to allow network operations
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * readJson will perform an http request on the specified url to obtain JSON information as a string
     */
    private void readJson(){
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(URL + Integer.toString(port) + roomPath);
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
            Log.d(TAG," http request failed at " + URL + Integer.toString(port) + roomPath);
            e.printStackTrace();
        }
        httpGet.abort();
    }

    /**
     * Gets the value of the user input for classroom wing
     * @return String
     */
    private String getRoomName(){
        classRoom = (EditText)findViewById(R.id.editText2);
        String message = classRoom.getText().toString();
        if (message == null || message.isEmpty())
            return null;
        return message;
    }

    /**
     * Gets the value of the user input for classroom number as a string
     * @return String
     */
    private String getRoomNumber(){
        classNumber = (EditText)findViewById(editText);
        String message = classNumber.getText().toString();
        if (message == null || message.isEmpty())
            return null;
        return message;
    }

    /**
     * Checks to see if className and classNumber entered by the user exists in the database
     * @param className User input of class wing
     * @param classNumber User input of class number
     * @return boolean
     */
    private boolean isJsonData(String className, String classNumber){
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject curr = jsonArray.getJSONObject(i);
                String wing = curr.getString("Wing");
                String roomNum = curr.getString("RoomNum");
                String value = wing + space + roomNum;
                if (value.equalsIgnoreCase(className+space+classNumber)){
                    return true;
                }
            }
        }
        catch (JSONException e) {
            Log.d(TAG, " JSON error.");
            e.printStackTrace();
            System.exit(0);
        }
        return false;
    }

    /**
     * Executed once the submit button has been clicked. Will query results and see if a match has been found.
     * Initiates a new activity depending on results.
     * @param view Handles screen layouts and interaction with the user
     */
    public void sendMessage(View view) {
        readJson();
        String className = getRoomName();
        String classNumber = getRoomNumber();
        if (!isJsonData(className, classNumber)){
            Intent intent = new Intent(this, ErrorActivity.class);
            intent.putExtra(EXTRA_MESSAGE, "MainActivity");
            startActivity(intent);
        }
        else{
            Log.i(TAG, " good value");
        }
    }
}
