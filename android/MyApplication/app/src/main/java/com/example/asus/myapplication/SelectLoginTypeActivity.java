package com.example.asus.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * This activity allows the user to choose between how they want to search for their classes.
 * @file SelectLoginTypeActivity.java
 * @author Steven Tang
 */

public class SelectLoginTypeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_login_type);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_login_type, menu);
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
     * This allows the user to search by classroom wing and number.
     * @param view Handles screen layouts and interaction with the user
     */
    public void clickLoginRoom(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * This allows the user to search by department name and department id.
     * @param view Handles screen layouts and interaction with the user
     */
    public void clickLoginDepartment(View view){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    /**
     * This allows the user to search by the name of the class they're enrolled in.
     * @param view Handles screen layouts and interaction with the user
     */
    public void clickLoginClassName(View view){
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }
}
