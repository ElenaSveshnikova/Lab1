package com.example.elena.lab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "StartActivity";

    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(ACTIVITY_NAME, "In onCreate()");
        setContentView(R.layout.activity_start);


        loginButton = (Button) findViewById(R.id.button2);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, ProjectActivity.class);
                startActivityForResult(intent, 5);
            }
        });

        //chatButton = (Button) findViewById(R.id.startChat);

        //chatButton.setOnClickListener(new View.OnClickListener(){
            //@Override
           // public void onClick(View view) {

              //  Log.i(ACTIVITY_NAME, "User clicked Start Chat");

              //  StartActivity.this.startActivity(new Intent(StartActivity.this, MessageListActivity.class));//????
       //     }
        //});


       // Button weatherButton = (Button) findViewById(R.id.weather);

       // weatherButton.setOnClickListener(new View.OnClickListener() {
           // @Override
          //  public void onClick(View view) {
       //         startActivity(new Intent(StartActivity.this, WeatherForecast.class));
          //  }
       // });

       // Button toolbarButton = (Button) findViewById(R.id.toolbarButton);
        //toolbarButton.setOnClickListener(new View.OnClickListener() {
          //  @Override
          //  public void onClick(View v) {
               // startActivity(new Intent(StartActivity.this, TestToolbar.class));
           // }
       // });

    loginButton = (Button) findViewById(R.id.button2);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Log.i(ACTIVITY_NAME, "User clicked Start Project");

                StartActivity.this.startActivity(new Intent(StartActivity.this, ProjectActivity.class));//????
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 5) {

            Log.i(ACTIVITY_NAME, "Returned to StartActivity.onActivityResult");
        }
        if(resultCode == StartActivity.RESULT_OK){
            String messagePassed = data.getStringExtra("Response");
            if (messagePassed.length() > 0) {
                CharSequence text = "ListItemsActivity passed: My information to share";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(this, text, duration);
                toast.show();
            }
        }
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
