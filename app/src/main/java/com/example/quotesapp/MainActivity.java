package com.example.quotesapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView quoteTextView;
    Button favouriteButton;
    SQLiteDatabase sqLiteDatabase;
    String quoteContent;
    List quoteContentArray = new ArrayList();
    List selectedQuoteIds = new ArrayList();
    int favouriteIndex;
    public int max;
    public int quoteNo = 0;
    TextView counterTextView;
    String counterString;
    ArrayList<String> filters = new ArrayList<>();

    String searchString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteTextView = findViewById(R.id.quoteTextView);
        favouriteButton = findViewById(R.id.favouriteButton);
        counterTextView = findViewById(R.id.counterTextView);

        // Method that checks if the complete database of quotes is loaded, and loads any missing ones.
        checkDatabase();

        // method that checks that there are some enabled filters.
        checkFilters();

        // method that makes the search string from the filters list.
        generateSearchString();

        // method that selects the 5 quotes to be displayed.
        chooseQuote();

        ConstraintLayout background = findViewById(R.id.background);

        background.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                Log.i("INFO", "onSwipeLeft");
                if (quoteNo == (max - 1)) {
                    quoteNo = 0;
                    quoteTextView.setText(quoteContentArray.get(quoteNo).toString());
                    Log.i("SETTING QUOTE", quoteContentArray.get(quoteNo).toString());
                } else {
                    quoteNo++;
                    quoteTextView.setText(quoteContentArray.get(quoteNo).toString());
                    Log.i("SETTING QUOTE", quoteContentArray.get(quoteNo).toString());
                }
                checkFavourite();
                updateTextView();
            }

            @Override
            public void onSwipeRight() {
                Log.i("INFO", "onSwipeRight");
                if (quoteNo == 0) {
                    quoteNo = (max - 1);
                    quoteTextView.setText(quoteContentArray.get(quoteNo).toString());
                } else {
                    quoteNo--;
                    quoteTextView.setText(quoteContentArray.get(quoteNo).toString());
                }
                checkFavourite();
                updateTextView();
            }
        });
    }

    public void checkDatabase () {
        try {
            sqLiteDatabase = this.openOrCreateDatabase("QuoteDatabase", MODE_PRIVATE, null);

//            sqLiteDatabase.execSQL("DROP TABLE quoteLibrary");

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS quoteLibrary (quote VARCHAR, author VARCHAR, categories VARCHAR, favourite BOOLEAN, id INTEGER PRIMARY KEY)");

            Cursor c = sqLiteDatabase.rawQuery("SELECT COUNT (*) FROM quoteLibrary", null);

            c.moveToFirst();
            Log.i("Database Count", Integer.toString(c.getInt(0)));

            List<String> quoteMasterArray = new ArrayList();

            quoteMasterArray.add("'quote one', 'author one', 'categoryOne, categoryTwo', 0");
            quoteMasterArray.add("'quote two', 'author two', 'categoryOne, categoryThree', 0");
            quoteMasterArray.add("'quote three', 'author three', 'categoryTwo, categoryThree, categoryFour', 0");
            quoteMasterArray.add("'quote four', 'author one', 'categoryOne, categoryFour', 0");
            quoteMasterArray.add("'quote five', 'author four', 'categoryOne, categoryFour', 0");
            quoteMasterArray.add("'quote six', 'author four', 'categoryOne', 0");
            quoteMasterArray.add("'quote seven', 'author five', 'categoryFive', 0");
            quoteMasterArray.add("'quote eight is a really long quote to help test how well it fits into the display on the app as it could get quite excessive if it goes on too long', 'author six sir reginald de waltzy hammersmith who has too many titles for his own good', 'categoryFive', 0");
            quoteMasterArray.add("'quote nine tests the database rebuild feature', 'author six', 'categorySix, categoryOne, categoryFour', 0");
            quoteMasterArray.add("'quote ten tests the database update feature', 'author seven', 'categorySix, categoryOne, categoryFour, categoryThree', 0");

            Log.i("MasterArray Count", Integer.toString(quoteMasterArray.size()));

            // METHOD UPDATES NEW ENTRIES TO DATABASE
            if (c.getInt(0) == quoteMasterArray.size()) {
                //Database looks up to date
                Log.i("Database Check", "Database appears up to date.");
            } else {
                Log.i("Database Check", "Database is not up to date, updating...");

                String updateString;
                for (int i=c.getInt(0); i < quoteMasterArray.size(); i++) {
                    updateString = "INSERT INTO quoteLibrary (quote, author, categories, favourite) VALUES ("+ quoteMasterArray.get(i) +")";
                    sqLiteDatabase.execSQL(updateString);
                    Log.i("Database Update", "Adding: " + quoteMasterArray.get(i));
                }
                Log.i("Database Check", "Database is updated");
            }

            /* // METHOD REBUILDS ENTIRE DATABASE
            if (c.getInt(0) == quoteMasterArray.size()) {
                //Database looks up to date
                Log.i("Database Check", "Database appears up to date.");
            } else {
                Log.i("Database Check", "Database is not up to date, rebuilding...");
                sqLiteDatabase.execSQL("DROP TABLE quoteLibrary");
                sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS quoteLibrary (quote VARCHAR, author VARCHAR, categories VARCHAR, favourite BOOLEAN, id INTEGER PRIMARY KEY)");
                String updateString;
                for (int i=0; i < quoteMasterArray.size(); i++) {
                    updateString = "INSERT INTO quoteLibrary (quote, author, categories, favourite) VALUES ("+ quoteMasterArray.get(i) +")";
                    sqLiteDatabase.execSQL(updateString);
                    Log.i("Database Update", "Adding: " + quoteMasterArray.get(i));
                }
                Log.i("Database Check", "Database is updated");
            }
            */

        } catch (Exception e) {e.printStackTrace();}
    }

    public void checkFilters () {

        try {
            Log.i("Filter Check", "Commencing Check");

            sqLiteDatabase = this.openOrCreateDatabase("QuoteDatabase", MODE_PRIVATE, null);

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS filterLibrary (filterName VARCHAR)");

            Cursor c = sqLiteDatabase.rawQuery("SELECT COUNT (*) FROM filterLibrary", null);

            if (c != null) {
                c.moveToFirst();
                if (c.getInt(0) == 0) {
                    Log.i("Filter Check Result", "No Filters Found, applying defaults");

                    Toast.makeText(this, "No enabled filters found.\nEnabling default filters...", Toast.LENGTH_SHORT).show();

                    // Add default filters to the app

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

                } else {
                    Log.i("Filter Check Result", "Filters Found");
                }
                c.close();
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    public void generateSearchString () {

        ArrayList<String> enabledFilters = new ArrayList<>();
        searchString = "SELECT * FROM quoteLibrary WHERE categories LIKE ";

        try {

            sqLiteDatabase = this.openOrCreateDatabase("QuoteDatabase", MODE_PRIVATE, null);

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS filterLibrary (filterName VARCHAR)");

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
                Log.i("Filter Memory", "None present, using defaults");
                enabledFilters.addAll(filters);
            }
        } catch (Exception e) {e.printStackTrace();}

        for (String filterName : enabledFilters) {
            if (enabledFilters.indexOf(filterName) == 0) {
                searchString = searchString + "('%" + filterName + "%')";
            } else if (enabledFilters.indexOf(filterName) == enabledFilters.size()) {
                searchString = searchString + " OR categories LIKE ('%" + filterName + "%')";
            } else {
                searchString = searchString + " OR categories LIKE ('%" + filterName + "%')";
            }
        }
        Log.i("SearchString Builder", "Currently reads: " + searchString);

    }

    public void chooseQuote () {

        try {

            sqLiteDatabase = this.openOrCreateDatabase("QuoteDatabase", MODE_PRIVATE, null);

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS quoteLibrary (quote VARCHAR, author VARCHAR, categories VARCHAR, favourite BOOLEAN, id INTEGER PRIMARY KEY)");

            Cursor c = sqLiteDatabase.rawQuery(searchString, null);

            int quoteIndex = c.getColumnIndex("quote");
            int authorIndex = c.getColumnIndex("author");
            int categoriesIndex = c.getColumnIndex("categories");
            favouriteIndex = c.getColumnIndex("favourite");
            int idIndex = c.getColumnIndex("id");

            // List all viable IDs in Log & Array

            List viableQuoteIds = new ArrayList<>();


            c.moveToFirst();
            if (c != null) {
                do {
                    Log.i("Quote ID", c.getString(idIndex));
                    viableQuoteIds.add((c.getInt(idIndex)));
                } while (c.moveToNext());
                c.close();
            }

            Log.i("Viable IDs", viableQuoteIds.toString());

            // Randomly select 5 different quote IDs

            int i = 0;
            int num;


            if (viableQuoteIds.size() >= 5) {
                max = 5;

                while (i < max) {
                    Random random = new Random();
                    num = (random.nextInt(viableQuoteIds.size()));
                    Log.i("Num", Integer.toString(num));
                    if (!selectedQuoteIds.contains(viableQuoteIds.get(num))) {
                        selectedQuoteIds.add(viableQuoteIds.get(num));
                        i = selectedQuoteIds.size();
                        Log.i("New Quote ID", viableQuoteIds.get(num) + " added.");
                    }
                }

            } else if (viableQuoteIds.size() != 0) {
                max = viableQuoteIds.size();
                Log.i("Max No. is", Integer.toString(viableQuoteIds.size()));

                while (i < max) {
                    Random random = new Random();
                    num = (random.nextInt(viableQuoteIds.size()));
                    Log.i("Num", Integer.toString(num));
                    if (!selectedQuoteIds.contains(viableQuoteIds.get(num))) {
                        selectedQuoteIds.add(viableQuoteIds.get(num));
                        i = selectedQuoteIds.size();
                        Log.i("New Quote ID", viableQuoteIds.get(num) + " added.");
                    }
                }
            } else {
                Log.i("ERROR", "What the heck, how did we end up with 0!?");
            }

            Log.i("ID List Done", "Chosen: " + selectedQuoteIds.toString());


            // List all quotes in Log & create Quote Content

            i = 0;
            while (i < max) {

                searchString = "SELECT * FROM quoteLibrary WHERE id = " + selectedQuoteIds.get(i);

                c = sqLiteDatabase.rawQuery(searchString, null);

                c.moveToFirst();

                Log.i("Quote Assembly", "Quote ID: " + c.getString(idIndex) + ". " + c.getString(quoteIndex) + " - " + c.getString(authorIndex) + ". [" + c.getString(categoriesIndex) + "]");
                quoteContent = c.getString(quoteIndex) + "\n - " + c.getString(authorIndex);
                quoteContentArray.add(quoteContent);

                i++;
            }


            //Log.i("Quote Array", quoteContentArray.toString());

            quoteTextView.setText(quoteContentArray.get(quoteNo).toString());
            Log.i("SETTING QUOTE", quoteContentArray.get(quoteNo).toString());
            updateTextView();


            //Log.i("Quote No", Integer.toString(quoteNo));
            c = sqLiteDatabase.rawQuery("SELECT * FROM quoteLibrary WHERE id = " + selectedQuoteIds.get(0), null);
            c.moveToFirst();
            Log.i("Setup Favourite Check","ID: " + selectedQuoteIds.get(0) + ". 1 = Fav, 0 = Not Fav: " + c.getString(favouriteIndex));
            if (c.getInt(favouriteIndex) != 0) {
                favouriteButton.setBackgroundResource(R.drawable.simple_red_heart_small);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void checkFavourite () {

        try {
            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM quoteLibrary WHERE id = " + selectedQuoteIds.get(quoteNo), null);
            c.moveToFirst();
            Log.i("Favourite Check","ID: " + selectedQuoteIds.get(quoteNo) + " Fav State: " + c.getString(favouriteIndex));
            if (c.getInt(favouriteIndex) != 0) {
                favouriteButton.setBackgroundResource(R.drawable.simple_red_heart_small);
            } else {
                favouriteButton.setBackgroundResource(R.drawable.simple_hollow_heart_small);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTextView () {

        counterString = (quoteNo + 1) + " / " + (max);

        counterTextView.setText(counterString);
    }

    public void favouriteButtonClicked (View view) {

        try {
            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM quoteLibrary WHERE id = " + selectedQuoteIds.get(quoteNo), null);
            c.moveToFirst();
            //Log.i("Favourite Button", c.getString(favouriteIndex));
            if (c.getInt(favouriteIndex) == 0) {
                favouriteButton.setBackgroundResource(R.drawable.simple_red_heart_small);
                sqLiteDatabase.execSQL("UPDATE quoteLibrary SET favourite = 1 WHERE id = " + selectedQuoteIds.get(quoteNo));
                Log.i("Favourited", selectedQuoteIds.get(quoteNo).toString());
            } else {
                favouriteButton.setBackgroundResource(R.drawable.simple_hollow_heart_small);
                sqLiteDatabase.execSQL("UPDATE quoteLibrary SET favourite = 0 WHERE id = " + selectedQuoteIds.get(quoteNo));
                Log.i("Unfavourited", selectedQuoteIds.get(quoteNo).toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.favourites:
                Log.i("Menu Item", "Favourites selected");
                Intent intent = new Intent(getApplicationContext(), FavouritesActivity.class);
                startActivity(intent);
                return true;
            case R.id.filter:
                Log.i("Menu Item", "Filter selected");
                intent = new Intent(getApplicationContext(), FilterActivity.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }

}
