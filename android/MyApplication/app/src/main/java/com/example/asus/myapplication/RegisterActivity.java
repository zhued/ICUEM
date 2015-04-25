package com.example.asus.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterActivity extends ActionBarActivity {

    EditText username;
    EditText password;
    EditText confirmPassword;
    private String filePath;
    BufferedWriter bw = null;

    public static final String fileName = "C:\\Users\\Asus\\Desktop\\AccountData.txt";

    private String TAG = RegisterActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public String getUsername(){
        username = (EditText)findViewById(R.id.editText8);
        String user = username.getText().toString();
        if (user == null || user.isEmpty()){
            System.exit(0);
        }
        return user;
    }

    public String getPassword(){
        password = (EditText)findViewById(R.id.editText9);
        confirmPassword = (EditText)findViewById(R.id.editText10);
        String pass = password.getText().toString();
        String passConfirm = confirmPassword.getText().toString();
        if (!pass.equals(passConfirm)){
            System.exit(0);
        }
        return pass;
    }


    public void createFile(String username, String password){

        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
                Log.d(TAG, file.getAbsolutePath());
            }
            bw = new BufferedWriter(new FileWriter(fileName, true));
            bw.write(username);
            bw.newLine();
            bw.write(password);
            bw.flush();
            bw.close();

        }
        catch (IOException e){
            Log.d(TAG, " IO Exception");
            e.printStackTrace();
        }
    }

    public void createAccount(View view){
        String username = getUsername();
        String password = getPassword();
        Log.d(TAG, username + " " + password);
        createFile(username, password);
        Log.d(TAG, " File created successfully.");
    }
}
