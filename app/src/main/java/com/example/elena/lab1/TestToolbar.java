package com.example.elena.lab1;

import android.app.AlertDialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TestToolbar extends AppCompatActivity {
    private String item1Message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "FloatingActionButton is clicked.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        item1Message = "HOME";
    }
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.toolbar_menu, m);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem mi) {
        int id = mi.getItemId();
        switch (id) {
            case R.id.action_home:
            Log.d("Toolbar", "Home menu selected.");
            Snackbar.make(findViewById(android.R.id.content), item1Message, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            break;
            case R.id.action_exit:
                Log.d("Toolbar", "Car menu selected.");
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Do you want to go back?");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // onBackPressed();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.action_edit:
                Log.d("Toolbar", "Edit menu selected.");
                onCreateDialog();
                break;
            case R.id.action_about:
                Toast toast = Toast.makeText(this, item1Message, Toast.LENGTH_LONG);
                toast.show();
                break;
        }
        return true;
    }

    private void onCreateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog, null);
        builder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.new_message);

        builder.setPositiveButton(R.string.change_message, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                item1Message = edt.getText().toString();
                Snackbar.make(findViewById(android.R.id.content), "Message is changed.", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
       builder.setNegativeButton(R.string.back, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                onBackPressed();
            }
        });
        AlertDialog b = builder.create();
        b.show();

    }
}
