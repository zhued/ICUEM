package com.example.asus.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.util.Log;
import android.widget.TextView;


public class ErrorActivity extends ActionBarActivity {

    private static final String TAG = ErrorActivity.class.getSimpleName();
    private final String EXTRA_MESSAGE = "com.example.asus.myapplication.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
    }



    //@Override
    /*public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_error, menu);
        return true;
    }*/

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


    public void goToHomepage(View view) {
        Intent intent;
        String classFrom = getIntent().getStringExtra(EXTRA_MESSAGE);
        if (classFrom.equalsIgnoreCase("MainActivity")) {
            intent = new Intent(this, MainActivity.class);
        }
        else if (classFrom.equalsIgnoreCase("MainActivity2")){
            intent = new Intent(this, MainActivity2.class);
        }
        else{
            intent = new Intent(this, MainActivity3.class);
        }
        startActivity(intent);
    }
}
