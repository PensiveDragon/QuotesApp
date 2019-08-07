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
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class FilterActivity extends AppCompatActivity {

    ListView filterListView;
//    ArrayList<String> startingFilters = new ArrayList<>();
    ArrayList<String> filters = new ArrayList<>();
    public ArrayList<String> changedFilters = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;


    @Override
    public void onBackPressed() {
        Log.i("Back button pressed", "Going back to main activity from filter activity");
//        Log.i("starting Filters", startingFilters.toString());
//        Log.i("enabled Filters", enabledFilters.toString());

        // METHOD TO CHECK WHETHER THE MAIN ACTIVITY QUOTES NEED TO BE REFRESHED

        if (changedFilters.isEmpty()) {
            Log.i("FilterActivity", "OnBackPressed Filter Check, No Changes Found!");
            super.onBackPressed();
        } else {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            super.onBackPressed();
            Log.i("FilterActivity", "OnBackPressed Filter Check, Some Changes Found!");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        Log.i("FilterActivity", "Starting onCreate!");

        filterListView = findViewById(R.id.filtersListView);
        filterListView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, filters);
        filterListView.setAdapter(arrayAdapter);

        try {

            sqLiteDatabase = this.openOrCreateDatabase("QuoteDatabase", MODE_PRIVATE, null);

//            Log.i("Database", "Drop Table");
//            sqLiteDatabase.execSQL("DROP TABLE filterLibrary");

//            Log.i("Database", "Create Table");
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS filterLibrary (filterName VARCHAR, active BOOLEAN)");

//            sqLiteDatabase.execSQL("INSERT INTO filterLibrary (filterName, active) VALUES ('categoryOne', 0)");

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Method that adds/removes selected filters from the database

        filterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CheckedTextView checkedTextView = (CheckedTextView) view;

                Log.i("FilterActivity", "Filter Clicked: " + filters.get(i));

                if (checkedTextView.isChecked()) {

                    if (changedFilters.contains(filters.get(i))) {
                        changedFilters.remove(filters.get(i));
                    } else {
                        changedFilters.add(filters.get(i));
                    }
                    try {
                        sqLiteDatabase.execSQL("UPDATE filterLibrary SET active = 1 WHERE filterName = '" + filters.get(i) + "'");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    if (changedFilters.contains(filters.get(i))) {
                        changedFilters.remove(filters.get(i));
                    } else {
                        changedFilters.add(filters.get(i));
                    }
                    try {
                        sqLiteDatabase.execSQL("UPDATE filterLibrary SET active = 0 WHERE filterName = '" + filters.get(i) + "'");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Log.i("Filters List", filters.toString());
                Log.i("Changed Filters", changedFilters.toString());


                // confirm that the items are being correctly deleted and that no spare data is in the table
                try {

                    Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM filterLibrary", null);

                    int filterIndex = c.getColumnIndex("filterName");
                    int activeIndex = c.getColumnIndex("active");

                    c.moveToFirst();
                    if (c != null && c.getCount() > 0) {
                        do {
                            //Log.i("Filter Entry", c.getString(filterIndex));
                        } while (c.moveToNext());
                        c.close();
                    } else {
                        Log.i("Filter Entry", "None present");
                    }
                } catch (Exception e) {e.printStackTrace();}
            }
        });

        try {

            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM filterLibrary", null);

            int filterIndex = c.getColumnIndex("filterName");
            int activeIndex = c.getColumnIndex("active");

            c.moveToFirst();
            if (c != null && c.getCount() > 0) {
                do {
                    filters.add(c.getString(filterIndex));
                } while (c.moveToNext());
                c.close();
            } else {
                Log.i("FilterActivity", "onCreate: No filters present!");
            }
            Log.i("FilterActivity", "onCreate, Filters List: " + filters.toString());
        } catch (Exception e) {e.printStackTrace();}

        //# Work out how to have filters naturally appear in program - pre-programmed or discovered from quoteLibrary?
/*
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

        filters.add("Self Improvement");
        filters.add("Perception");
        filters.add("Action");
        filters.add("Motivation");
        filters.add("Responsibility");
        filters.add("Optimism");
        filters.add("Kindness");
        filters.add("Purpose");
        filters.add("Self Reliance");
        filters.add("Persistence");
*/
        // method that updates the checklist from the saved filters in the library on activation

        try {

            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM filterLibrary WHERE active = 1", null);

            int filterIndex = c.getColumnIndex("filterName");
            int activeIndex = c.getColumnIndex("active");

            Log.i("FilterActivity", "Number of Active Filters: " + c.getCount());

            c.moveToFirst();
            if (c != null && c.getCount() > 0) {
                do {
                    Log.i("FilterActivity", "Checking Filter: " + c.getString(filterIndex) + " | Index " + filters.indexOf(c.getString(filterIndex)));
                    filterListView.setItemChecked(filters.indexOf(c.getString(filterIndex)), true);

                } while (c.moveToNext());
                c.close();
            } else {
                Log.i("Filter Memory", "None present");
            }
        } catch (Exception e) {e.printStackTrace();}
/*
        for (String filterName : enabledFilters) {
            filterListView.setItemChecked(filters.indexOf(filterName), true);
        }


        for (int i = 0; i < enabledFilters.size(); i++) {
            startingFilters.add(enabledFilters.get(i));
        }
*/
    }

}
