package com.example.elena.lab1;

import android.content.SharedPreferences;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.content.Intent;


public class LoginActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "LoginActivity";
    Button loginButton;
    EditText mEmailView;
    EditText passwordView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //loginButton = (Button) findViewById(R.id.button2);
        //mEmailView = (EditText) findViewById(R.id.editText2);
        //loginButton.setOnClickListener(new OnClickListener() {
            //@Override
           // public void onClick(View view) {

               // SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
               // SharedPreferences.Editor editor = sharedPref.edit();
               // editor.putString(getString(R.string.saved_email),
                //        mEmailView.getText().toString());
               // editor.commit();
               // LoginActivity.this.startActivity(new Intent(LoginActivity.this, StartActivity.class));
            //}
        //});
        Log.i(ACTIVITY_NAME, "In onCreate()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");


        //SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        //mEmailView.setText(sharedPref.getString(getString(R.string.saved_email), "email@domain.com"));

    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
}


