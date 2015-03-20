package com.example.asus.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.*;
import android.content.Intent;
import android.util.Log;

import org.w3c.dom.Text;

import static com.example.asus.myapplication.R.id.editText;


public class MainActivity extends ActionBarActivity {
    private EditText classRoom;
    private EditText classNumber;
    public final static String EXTRA_MESSAGE = "com.example.asus.myapplication.MESSAGE";
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void sendMessage(View view) {
        Intent intent = new Intent(this, ErrorActivity.class);
        classRoom = (EditText)findViewById(R.id.editText2);
        classNumber = (EditText)findViewById(editText);
        String message = classRoom.getText().toString();
        String message2 = classNumber.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message+' '+message2);
        startActivity(intent);
    }
}
