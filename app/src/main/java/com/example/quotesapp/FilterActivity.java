package com.example.quotesapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class FilterActivity extends AppCompatActivity {

    ListView filterListView;
    ArrayList<String> startingFilters = new ArrayList<>();
    ArrayList<String> filters = new ArrayList<>();
    public ArrayList<String> enabledFilters = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;
    boolean defaultModeBool = false;
    boolean filtersChanged = false;


    @Override
    public void onBackPressed() {
        Log.i("Back button pressed", "Going back to main activity from filter activity");
        Log.i("starting Filters", startingFilters.toString());
        Log.i("enabled Filters", enabledFilters.toString());

        if (startingFilters.containsAll(enabledFilters) && enabledFilters.containsAll(startingFilters)) {
            Log.i("Filter Check", "Filters match!");
            super.onBackPressed();
        } else {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            super.onBackPressed();
            Log.i("Filter Check", "Filters don't match!");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        filtersChanged = false;

        Log.i("On FilterActivity...", "HI!");

        filterListView = findViewById(R.id.filtersListView);
        filterListView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, filters);
        filterListView.setAdapter(arrayAdapter);

        try {

            sqLiteDatabase = this.openOrCreateDatabase("QuoteDatabase", MODE_PRIVATE, null);

//            sqLiteDatabase.execSQL("DROP TABLE filterLibrary");
//            Log.i("Database", "Drop Table");

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS filterLibrary (filterName VARCHAR)");
//            Log.i("Database", "Create Table");

//            sqLiteDatabase.execSQL("INSERT INTO filterLibrary (filterName) VALUES ('categoryOne')");

        } catch (Exception e) {
            e.printStackTrace();
        }


        // Method that adds/removes selected filters from the database

        filterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CheckedTextView checkedTextView = (CheckedTextView) view;

                if (checkedTextView.isChecked()) {
                    if (defaultModeBool) {

                        try {
                            sqLiteDatabase.execSQL("DELETE FROM filterLibrary");
                        } catch (Exception e) {e.printStackTrace();}

                    } else {}
                    Log.i("Item Checked", filters.get(i));
                    // Add entry to filters list
                    enabledFilters.add(filters.get(i));
                    String addFilter = "INSERT INTO filterLibrary (filterName) VALUES ('" + filters.get(i) + "')";
                    try {
                        sqLiteDatabase.execSQL(addFilter);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.i("Item Adding", addFilter);
                    Log.i("Item Added", filters.get(i));

                } else {
                    Log.i("Item Unchecked", filters.get(i));
                    // Remove entry from filters list
                    enabledFilters.remove(filters.get(i));

                    String deleteFilter = "DELETE FROM filterLibrary WHERE filterName = '" + filters.get(i) + "'";
                    try {
                        sqLiteDatabase.execSQL(deleteFilter);
                    } catch (Exception e) {e.printStackTrace();}
                    Log.i("Item Removing", deleteFilter);
                    Log.i("Item Removed", filters.get(i));

                }
                Log.i("Filters List", filters.toString());
                Log.i("Enabled Filters", enabledFilters.toString());

                defaultMode();


                // confirm that the items are being correctly deleted and that no spare data is in the table
                try {

                    Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM filterLibrary", null);

                    int filterIndex = c.getColumnIndex("filterName");

                    c.moveToFirst();
                    if (c != null && c.getCount() > 0) {
                        do {
                            Log.i("Filter Entry", c.getString(filterIndex));
                        } while (c.moveToNext());
                        c.close();
                    } else {
                        Log.i("Filter Entry", "None present");
                    }
                } catch (Exception e) {e.printStackTrace();}
            }
        });


        // Work out how to have filters naturally appear in program - pre-programmed or discovered from quoteLibrary?

        filters.add("categoryOne");
        filters.add("categoryTwo");
        filters.add("categoryThree");
        filters.add("categoryFour");
        filters.add("categoryFive");
        filters.add("categorySix");
        filters.add("categorySeven");
        filters.add("categoryEight");
        filters.add("categoryNine");
        filters.add("categoryTen");




        // method that updates the checklist from the saved filters in the library on activation

        try {

            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM filterLibrary", null);

            int filterIndex = c.getColumnIndex("filterName");

            c.moveToFirst();
            if (c != null && c.getCount() > 0) {
                do {
                    Log.i("Filter Memory", c.getString(filterIndex));
                    enabledFilters.add(c.getString(filterIndex));
                } while (c.moveToNext());
                c.close();
            } else {
                Log.i("Filter Memory", "None present");
            }
        } catch (Exception e) {e.printStackTrace();}

        for (String filterName : enabledFilters) {
            filterListView.setItemChecked(filters.indexOf(filterName), true);
        }


        for (int i = 0; i < enabledFilters.size(); i++) {
            startingFilters.add(enabledFilters.get(i));
        }

    }


    // Method to check if all filters are off = default mode

    public void defaultMode () {

        if (enabledFilters.size() == 0) {
            defaultModeBool = true;
            Log.i("DEFAULT MODE", "ON");

            for (int i = 0; i < filters.size(); i++) {

                String addFilter = "INSERT INTO filterLibrary (filterName) VALUES ('" + filters.get(i) + "')";
                try {
                    sqLiteDatabase.execSQL(addFilter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // Code for visually altering the listView so that it looks like a special effect is in place




            // Toast to state default mode is enabled? Maybe a permanent message?

        } else {
            defaultModeBool = false;
            Log.i("DEFAULT MODE", "OFF");
        }

    }

    private int getIdFromName (String name) {
        return getResources().getIdentifier(name, "id", getPackageName());
    }

}
