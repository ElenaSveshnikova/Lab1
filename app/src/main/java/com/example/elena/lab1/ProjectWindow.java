package com.example.elena.lab1;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class ProjectWindow extends AppCompatActivity {

    private final String ACTIVITY_NAME = ListItemsActivity.class.getSimpleName();

    private Button addButton;
    private EditText editText;
    private ListView listView;
    ArrayList<String> data = new ArrayList<String>();
    ArrayAdapter adapter;

    private SQLiteDatabase db;  //a SQLiteDatabase object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_window);
        //Log.i(ACTIVITY_NAME, "In onCreate()");
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        final ChatAdapter messageAdapter = new ChatAdapter(this);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(messageAdapter);
        addButton = (Button) findViewById(R.id.add);
        editText = (EditText) findViewById(R.id.editText4);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        ChatDatabaseHelper Cdb = new ChatDatabaseHelper(this);

        db = Cdb.getWritableDatabase(); //open it for both read and write

        Cursor cursor = db.query(ChatDatabaseHelper.TABLE_NAME, new String[]
                {ChatDatabaseHelper.KEY_MESSAGE}, null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));
            data.add(cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));
            cursor.moveToNext();
        }
        Log.i(ACTIVITY_NAME, "Cursor’s  column count = " + cursor.getColumnCount());

        for (int i = 0; i < cursor.getColumnCount(); i++) {
            Log.i(ACTIVITY_NAME, "Column" + (i + 1) + "; " + cursor.getColumnName(i));
        }
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String strTmp = editText.getText().toString();
                data.add(strTmp);
                messageAdapter.notifyDataSetChanged();
                editText.setText("");

                //save message to database
                ContentValues cValue = new ContentValues();
                cValue.put(ChatDatabaseHelper.KEY_MESSAGE, strTmp);
                db.insert(ChatDatabaseHelper.TABLE_NAME, "NULL", cValue);
            }
        });
        listView.setAdapter(messageAdapter);


    }

    class ChatAdapter extends ArrayAdapter<String> {

        Context myContext;

        public ChatAdapter(Context ctx) {
            super(ctx, 0);
            // myContext = ctx;
        }

        public int getCount() {
            return data.size();
        }

        public String getItem(int position) {
            return data.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent) {//In this method the adapter creates the row layout and maps the data to the views in the layout.
            View result = null;
            LayoutInflater inflater = ProjectWindow.this.getLayoutInflater();

            //if (position % 2 == 0)
                result = inflater.inflate(R.layout.project_row, null);
            //else
                //result = inflater.inflate(R.layout.project_row_right, null);

            TextView message = (TextView) result.findViewById(R.id.message_text);//????
            message.setText(getItem(position));
            return result;

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


        db.close();
    }

}