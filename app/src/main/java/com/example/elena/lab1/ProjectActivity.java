package com.example.elena.lab1;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.elena.lab1.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of Messages. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link MessageDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */


public class ProjectActivity extends AppCompatActivity {
    //private final String ACTIVITY_NAME = ListItemsActivity.class.getSimpleName();

    protected static final String ACTIVITY_NAME = "Project";
    private boolean mTwoPane;
    private Button addButton;
    private EditText editText;
    private ListView listView;
    static ArrayList<String> data;

    ArrayAdapter adapter;
    ChatDatabaseHelper Cdb;
    ChatAdapter messageAdapter;
    private String item1Message= "It is project Swear Not";
    private SQLiteDatabase db;  //a SQLiteDatabase object
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());


        data = new ArrayList<>();

        listView = (ListView) findViewById(R.id.listView);

       // messageAdapter=null;
        listView.setAdapter(messageAdapter);
       addButton = (Button) findViewById(R.id.add);
        editText = (EditText) findViewById(R.id.editText4);
        Cdb = new ChatDatabaseHelper(this);
        db = Cdb.getWritableDatabase(); //open it for both read and write

         adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);



        Cursor cursor = db.query(ChatDatabaseHelper.TABLE_NAME, new String[]
                {ChatDatabaseHelper.KEY_MESSAGE}, null, null, null, null, null, null);

        //Cdb.deleteMessages(db);

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




        messageAdapter = new ChatAdapter(this);
        listView.setAdapter(messageAdapter);

        if (findViewById(R.id.message_detail_container)!= null)

        {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

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
                Toast toast = Toast.makeText(this, R.string.about_message, Toast.LENGTH_LONG);
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
                //Snackbar.make(findViewById(android.R.id.content), "Message is changed.", Snackbar.LENGTH_SHORT)
                  //      .setAction("Action", null).show();
            }
        });
        builder.setNegativeButton(R.string.back, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //onBackPressed();
            }
        });
        AlertDialog b = builder.create();
        b.show();

    }
       /* View recyclerView = findViewById(R.id.message_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);*/


//    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
//        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(DummyContent.ITEMS));
//    }
//
//    public class SimpleItemRecyclerViewAdapter
//            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {
//
//        private final List<DummyContent.DummyItem> mValues;
//
//        public SimpleItemRecyclerViewAdapter(List<DummyContent.DummyItem> items) {
//            mValues = items;
//        }
//
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.message_list_content, parent, false);
//            return new ViewHolder(view);
//        }

//        @Override
//        public void onBindViewHolder(final ViewHolder holder, int position) {
//            holder.mItem = mValues.get(position);
//            holder.mIdView.setText(mValues.get(position).id);
//            holder.mContentView.setText(mValues.get(position).content);
//
//            holder.mView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mTwoPane) {
//                        Bundle arguments = new Bundle();
//                        arguments.putString(MessageDetailFragment.ARG_ITEM_ID, holder.mItem.id);
//                        MessageDetailFragment fragment = new MessageDetailFragment();
//                        fragment.setArguments(arguments);
//                        getSupportFragmentManager().beginTransaction()
//                                .replace(R.id.message_detail_container, fragment)
//                                .commit();
//                    } else {
//                        Context context = v.getContext();
//                        Intent intent = new Intent(context, MessageDetailActivity.class);
//                        intent.putExtra(MessageDetailFragment.ARG_ITEM_ID, holder.mItem.id);
//
//                        context.startActivity(intent);
//                    }
//                }
//            });
//        }

    //      @Override
    public int getItemCount() {
        return data.size();
    }

//        public class ViewHolder extends RecyclerView.ViewHolder {
//            public final View mView;
//            public final TextView mIdView;
//            public final TextView mContentView;
//            public DummyContent.DummyItem mItem;
//
//            public ViewHolder(View view) {
//                super(view);
//                mView = view;
//                mIdView = (TextView) view.findViewById(R.id.id);
//                mContentView = (TextView) view.findViewById(R.id.content);
//            }
//
//            @Override
//            public String toString() {
//                return super.toString() + " '" + mContentView.getText() + "'";
//            }
//
//        }
    //   }

    class ChatAdapter extends ArrayAdapter<String> {

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
            LayoutInflater inflater = ProjectActivity.this.getLayoutInflater();

            if (position % 2 == 0)
                result = inflater.inflate(R.layout.project_row, null);
            else
                result = inflater.inflate(R.layout.project_row, null);
            final String messageText = getItem(position) ;
            TextView message = (TextView) result.findViewById(R.id.message_text);//????
            message.setText(messageText);

            result.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(MessageDetailFragment.ARG_ITEM_ID, messageText);
                        MessageDetailFragment fragment = new MessageDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.message_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, MessageDetailActivity.class);
                        intent.putExtra(MessageDetailFragment.ARG_ITEM_ID, messageText);

                        context.startActivity(intent);
                    }
                }
            });
            return result;

        }
    }
}