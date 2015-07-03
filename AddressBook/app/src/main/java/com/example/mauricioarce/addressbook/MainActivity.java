package com.example.mauricioarce.addressbook;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mauricio Arce on 30/06/2015.
 */
public class MainActivity extends ActionBarActivity {

    private AddressReaderDbHelper helper;
    private List<ItemsGroup> contacts = new ArrayList<>();
    private AddressContract contract = new AddressContract();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        updateUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_contact:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                LayoutInflater inflater = this.getLayoutInflater();
                builder.setTitle("Add a contact");
                View v = inflater.inflate(R.layout.message_layout, null);
                final EditText contactName = (EditText) v.findViewById(R.id.text_cname);
                final EditText contactPhone = (EditText) v.findViewById(R.id.text_cphone);
                final EditText contactEmail = (EditText) v.findViewById(R.id.text_cemail);
                builder.setView(v);

                builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = contactName.getText().toString();
                        String phone = contactPhone.getText().toString();
                        String email = contactEmail.getText().toString();

                        helper = new AddressReaderDbHelper(MainActivity.this);
                        SQLiteDatabase db = helper.getWritableDatabase();
                        ContentValues values = new ContentValues();

                        values.clear();
                        values.put(AddressContract.Columns.CONTACT_NAME, name);
                        values.put(AddressContract.Columns.CONTACT_NUMBER, phone);
                        values.put(AddressContract.Columns.CONTACT_EMAIL, email);

                        db.insertWithOnConflict(AddressContract.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
                        updateUI();
                    }
                });
                builder.setNegativeButton("Cancel",null);
                builder.create().show();
                return true;
            default:
                return false;
        }
    }

    private void updateUI() {
        helper = new AddressReaderDbHelper(MainActivity.this);
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] columns = new String[]{AddressContract.Columns._ID,
                                        AddressContract.Columns.CONTACT_NAME,
                                        AddressContract.Columns.CONTACT_NUMBER,
                                        AddressContract.Columns.CONTACT_EMAIL};
        Cursor cursor = db.query(AddressContract.TABLE_NAME,
                                 columns,
                                 null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                String email = cursor.getString(3);
                ItemsGroup group = new ItemsGroup(name);
                group.getChildren().add(phone);
                group.getChildren().add(email);
                contacts.add(group);
            } while (cursor.moveToNext());
        }

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.exp_address);
        ContactsAdapter adapter = new ContactsAdapter(this, contacts);
        listView.setAdapter(adapter);
    }

}
