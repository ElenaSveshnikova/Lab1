package com.example.elena.lab1;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.content.Intent;
import android.provider.MediaStore;
import android.widget.Toast;
import android.widget.Switch;

public class ListItemsActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "ListItemsActivity";

    static final int REQUEST_IMAGE_CAPTURE = 1;
    Switch switchButton;
    CheckBox checkButton;
    ImageButton imButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        Log.i(ACTIVITY_NAME, "In onCreate()");

        imButton = (ImageButton) findViewById(R.id.imButton);
        imButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        switchButton = (Switch) findViewById(R.id.exampleButton2);

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                CharSequence text;
                int duration;

                if (buttonView.isChecked()) {
                    text = "Switch is On";
                    duration = Toast.LENGTH_SHORT;

                } else {
                    text = "Switch is Off";
                    duration = Toast.LENGTH_LONG;

                }

                Toast toast = Toast.makeText(ListItemsActivity.this, text, duration); //this is the ListActivity
                toast.show();
            }
        });

        checkButton = (CheckBox)findViewById(R.id.exampleButton1);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Store values at the time of the login attempt.
                AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
// 2. Chain together various setter methods to set the dialog characteristics
                builder.setMessage(R.string.dialog_message); //Add a dialog message to strings.xml

                builder.setTitle(R.string.dialog_title);
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent resultIntent = new Intent(  );
                                resultIntent.putExtra("Response", "My information to share");
                                setResult(ListItemsActivity.RESULT_OK, resultIntent);
                                finish();

                            }
                        });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });
          }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageButton imButton = (ImageButton) findViewById(R.id.imButton);
            imButton.setImageBitmap(imageBitmap);
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


