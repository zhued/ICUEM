
package com.example.asus.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * This Activity will act as the main page of the app, where you can choose to login as user or as guest
 * @file StartActivity.java
 * @author Steven Tang
 */

public class StartActivity extends ActionBarActivity {

    /**
     * This constructs an initial activity - the main page of this app
     * @param savedInstanceState the instance savedInstanceState will initially be null
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Use activity_login design as the layout for this activity
        setContentView(R.layout.activity_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
     * Initiates a new activity once the login as Guest button has been clicked.
     * @param view Handles screen layouts and interaction with the user
     */
    public void loginAsGuest(View view){
        Intent intent = new Intent(this, SelectLoginTypeActivity.class);
        startActivity(intent);
    }

}
