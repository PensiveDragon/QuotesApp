package com.example.quotesapp;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity {

    TextView quoteTextView;
    Button favouriteButton;
    Button menuButton;
    Button favouritesMenuButton;
    Button filterMenuButton;
    ViewGroup expandedMenu;
    SQLiteDatabase sqLiteDatabase;
    String quoteContent;
    List quoteContentArray = new ArrayList();
    List selectedQuoteIds = new ArrayList();
    int favouriteIndex;
    int favouriteCount;
    int favouriteLimit = 100;
    public int max = 5;
    public int quoteNo = 0;
    TextView counterTextView;
    String counterString;
    ArrayList<String> filters = new ArrayList<>();
    Set generatedCategories = new TreeSet();
    CardView cardView;

    String searchString;
    boolean menuOpen = false;
    ImageView backgroundImageView;
    int backgroundInt = 1;
    int screenWidth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteTextView = findViewById(R.id.quoteTextView);
        favouriteButton = findViewById(R.id.favouriteButton);
        counterTextView = findViewById(R.id.counterTextView);
        menuButton = findViewById(R.id.menu_button);
        favouritesMenuButton = findViewById(R.id.favourites_menu_button);
        filterMenuButton = findViewById(R.id.filter_menu_button);
        expandedMenu = findViewById(R.id.menuFrame);
        cardView = findViewById(R.id.cardView);
        backgroundImageView = findViewById(R.id.backgroundImageView);

        // Code that checks the screen size, for adaptive swiping
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        Log.i("GestureListener", "Screen width = " + screenWidth);

        // Method that checks if the complete database of quotes is loaded, and loads any missing ones.
        checkQuoteDatabase();

        // Method that checks if the categories have loaded in, if there are none, it will generate them.
        checkFilterLibrary();

        // method that checks that there are some enabled filters.
        //checkActiveFilters();

        // method that counts the number of favourites the user currently has stored.
        countFavourites();

        // method that makes the search string from the filters list.
        generateSearchString();

        // method that selects the 5 quotes to be displayed.
        chooseQuote();

        // creates class that handles the gesture detection.
        //onTouchGestureListener = new OnTouchGestureListener(this, null, cardView);

        ConstraintLayout background = findViewById(R.id.background);

        background.setOnTouchListener(new OnTouchGestureListener(this, null, cardView) {

            @Override
            public void onSwipeLeft() {

                Log.i("INFO", "onSwipeLeft");

                if (quoteNo == (max - 1)) {
                    quoteNo = 0;
                    Log.i("SETTING QUOTE", quoteContentArray.get(quoteNo).toString());
                } else {
                    quoteNo++;
                    Log.i("SETTING QUOTE", quoteContentArray.get(quoteNo).toString());
                }
                updateBackground();

                cardView.animate()
                        .scaleX(.5f)
                        .scaleY(.5f)
                        .translationX(-screenWidth)
                        .translationY(0)
                        .setDuration(500)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {

                                cardView.animate()
                                        .translationX(screenWidth)
                                        .translationY(0)
                                        .setDuration(0)
                                        .withEndAction(new Runnable() {
                                            @Override
                                            public void run() {

                                                quoteTextView.setText(quoteContentArray.get(quoteNo).toString());

                                                checkFavourite();
                                                updateTextView();


                                                cardView.animate()
                                                        .scaleX(1f)
                                                        .scaleY(1f)
                                                        .translationX(0)
                                                        .translationY(0)
                                                        .setDuration(500);

                                                ObjectAnimator colorfade = ObjectAnimator.ofObject(cardView, "backgroundColor", new ArgbEvaluator(), 0xBFFFFFFF, 0x00FFFFFF);
                                                colorfade.setStartDelay(250);
                                                colorfade.setDuration(150);
                                                colorfade.start();
                                            }
                                        });
                            }
                        })
                        .start();
//                updateBackground();
            }

            @Override
            public void onSwipeRight() {

                Log.i("INFO", "onSwipeRight");

                if (quoteNo == 0) {
                    quoteNo = (max - 1);
                } else {
                    quoteNo--;
                }
                updateBackground();

                cardView.animate()
                        .scaleX(.5f)
                        .scaleY(.5f)
                        .translationX(screenWidth)
                        .translationY(0)
                        .setDuration(500)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {

                                cardView.animate()
                                        .translationX(-screenWidth)
                                        .translationY(0)
                                        .setDuration(0)
                                        .withEndAction(new Runnable() {
                                            @Override
                                            public void run() {

                                                quoteTextView.setText(quoteContentArray.get(quoteNo).toString());
                                                checkFavourite();
                                                updateTextView();


                                                cardView.animate()
                                                        .scaleX(1f)
                                                        .scaleY(1f)
                                                        .translationX(0)
                                                        .translationY(0)
                                                        .setDuration(500);

                                                ObjectAnimator colorfade = ObjectAnimator.ofObject(cardView, "backgroundColor", new ArgbEvaluator(), 0xBFFFFFFF, 0x00FFFFFF);
                                                colorfade.setStartDelay(250);
                                                colorfade.setDuration(150);
                                                colorfade.start();
                                            }
                                        });
                            }
                        })
                        .start();
                //updateBackground();
            }

        });

        /*
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
        */
    }

    public void updateBackground() {



        backgroundImageView.animate()
                .alpha(0)
                .setStartDelay(0)
                .setDuration(400)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("MainActivity", "QuoteNo is: " + quoteNo);

                        switch (quoteNo) {
                            case (0) : backgroundImageView.setImageResource(R.drawable.bg1);
                            break;

                            case (1) : backgroundImageView.setImageResource(R.drawable.bg2);
                            break;

                            case (2) : backgroundImageView.setImageResource(R.drawable.bg3);
                            break;

                            case (3) : backgroundImageView.setImageResource(R.drawable.bg4);
                            break;

                            case (4) : backgroundImageView.setImageResource(R.drawable.bg5);
                            break;
                        }

                        backgroundImageView.animate()
                                .alpha(0.2f)
                                .setDuration(200);
                    }
                });


    }

    public void checkQuoteDatabase () {
        try {
            sqLiteDatabase = this.openOrCreateDatabase("QuoteDatabase", MODE_PRIVATE, null);

            //sqLiteDatabase.execSQL("DROP TABLE quoteLibrary");

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS quoteLibrary (quote VARCHAR, author VARCHAR, categories VARCHAR, favourite BOOLEAN, id INTEGER PRIMARY KEY)");

            Cursor c = sqLiteDatabase.rawQuery("SELECT COUNT (*) FROM quoteLibrary", null);

            c.moveToFirst();
            Log.i("Check Database", "Database Count: " + (c.getInt(0)));


            // CALLS METHOD FROM QUOTELIBRARY
            ArrayList<Quote> quoteMasterArray = new ArrayList<>(QuoteLibrary.createLibrary());

            Log.i("Check Database" , "MasterArray Count: " + (quoteMasterArray.size()));

            // Example insert line:
            //"INSERT INTO quoteLibrary (quote, author, categories, favourite) VALUES ('Quality is not an act, it is a habit.', 'Aristotle', 'Self Improvement, Perception', 0)";


            // METHOD UPDATES NEW ENTRIES TO DATABASE
            if (c.getInt(0) == quoteMasterArray.size()) {
                //Database looks up to date
                Log.i("Database Check", "Database appears up to date.");
            } else {
                Log.i("Database Check", "Database is not up to date, updating...");

                String updateString;
                for (int i=c.getInt(0); i < quoteMasterArray.size(); i++) {

                    String categoriesAssembly = "";

                    for (int j = 0; j < quoteMasterArray.get(i).getCategories().size(); j++) {
                        categoriesAssembly += quoteMasterArray.get(i).getCategories().get(j);
                        if (j < quoteMasterArray.get(i).getCategories().size()-1) {
                            categoriesAssembly += ", ";
                        }
                    }

                    updateString = "INSERT INTO quoteLibrary (quote, author, categories, favourite) VALUES ('"
                        + quoteMasterArray.get(i).getText() + "', '"
                        + quoteMasterArray.get(i).getAuthor() + "', '"
                        + categoriesAssembly + "', "
                        + quoteMasterArray.get(i).getFavouriteInt() + ")";

                    Log.i("UpdateString", updateString);

                    sqLiteDatabase.execSQL(updateString);

                    Log.i("Database Update", "Adding: " + "'" + quoteMasterArray.get(i).getText() + "', '" + quoteMasterArray.get(i).getAuthor() + "', '" + categoriesAssembly + "', " + quoteMasterArray.get(i).getFavouriteInt() + ")");
                }
                Log.i("Database Check", "Database is updated");
            }



/*
            // METHOD REBUILDS ENTIRE DATABASE
            if (c.getInt(0) == quoteMasterArray.size()) {
                //Database looks up to date
                Log.i("Database Check", "Database appears up to date.");
            } else {
                Log.i("Database Check", "Database is not up to date, rebuilding...");
                sqLiteDatabase.execSQL("DROP TABLE quoteLibrary");
                sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS quoteLibrary (quote VARCHAR, author VARCHAR, categories VARCHAR, favourite BOOLEAN, id INTEGER PRIMARY KEY)");
                String updateString;
                for (int i=c.getInt(0); i < quoteMasterArray.size(); i++) {

                    String categoriesAssembly = "";

                    for (int j = 0; j < quoteMasterArray.get(i).getCategories().size(); j++) {
                        categoriesAssembly += quoteMasterArray.get(i).getCategories().get(j);
                        if (j < quoteMasterArray.get(i).getCategories().size()-1) {
                            categoriesAssembly += ", ";
                        }
                    }

                    updateString = "INSERT INTO quoteLibrary (quote, author, categories, favourite) VALUES ('"
                            + quoteMasterArray.get(i).getText() + "', '"
                            + quoteMasterArray.get(i).getAuthor() + "', '"
                            + categoriesAssembly + "', "
                            + quoteMasterArray.get(i).getFavouriteInt() + ")";

                    //Log.i("UpdateString", updateString);

                    sqLiteDatabase.execSQL(updateString);

                    Log.i("Database Update", "Adding: " + "'" + quoteMasterArray.get(i).getText() + "', '" + quoteMasterArray.get(i).getAuthor() + "', '" + categoriesAssembly + "', " + quoteMasterArray.get(i).getFavouriteInt() + ")");
                }
                Log.i("Database Check", "Database is updated. " + quoteMasterArray.size() + " quotes.");
            }
*/

        } catch (Exception e) {e.printStackTrace();}
    }

    public void checkFilterLibrary () {
        try {
            sqLiteDatabase = this.openOrCreateDatabase("QuoteDatabase", MODE_PRIVATE, null);

            //sqLiteDatabase.execSQL("DROP TABLE filterLibrary");

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS filterLibrary (filterName VARCHAR, active BOOLEAN)");

            Cursor c = sqLiteDatabase.rawQuery("SELECT COUNT (*) FROM filterLibrary", null);

            c.moveToFirst();
            //Log.i("checkFilterLibrary", "Filter Library Count: " + (c.getInt(0)));

            if (c.getInt(0) == 0) {
                Log.i("checkFilterLibrary", "Filter Library is empty, generating categories");
                generateCategories();
            }

            Iterator<String> itr = generatedCategories.iterator();

            while(itr.hasNext()){
                sqLiteDatabase.execSQL("INSERT INTO filterLibrary (filterName, active) VALUES ('" + itr.next() + "', 0)");
            }

            Cursor k = sqLiteDatabase.rawQuery("SELECT COUNT (*) FROM filterLibrary", null);
            k.moveToFirst();
            Log.i("checkFilterLibrary", "New Filter Library Count: " + (k.getInt(0)));

        } catch (Exception e) {e.printStackTrace();}
    }

    public void generateCategories () {
        Log.i("MainActivity", "Generating Categories");

        try {
            sqLiteDatabase = this.openOrCreateDatabase("QuoteDatabase", MODE_PRIVATE, null);

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS quoteLibrary (quote VARCHAR, author VARCHAR, categories VARCHAR, favourite BOOLEAN, id INTEGER PRIMARY KEY)");

            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM quoteLibrary", null);

            int categoriesIndex = c.getColumnIndex("categories");
            String[] categoryResults;

            generatedCategories = new TreeSet();

            c.moveToFirst();
            if (c != null && c.getCount() > 0) {
                do {
                    //Log.i("GenerateCategories", "Categories Found! " + c.getString(categoriesIndex));
                    categoryResults = c.getString(categoriesIndex).split(", ");
                    for (int i = 0; i < categoryResults.length; i++) {
                        String result = categoryResults[i];
                        generatedCategories.add(result);
                    }
                } while (c.moveToNext());
                c.close();
                Log.i("GenerateCategories", generatedCategories.toString());
                filters.addAll(generatedCategories);
            } else  {
                Log.i("GenerateCategories", "ERROR: Couldn't generate any categories");
            }

        } catch (Exception e) {e.printStackTrace();}
    }

/*
    public void checkActiveFilters () {

        try {
            Log.i("Active Filter Check", "Commencing Check");

            sqLiteDatabase = this.openOrCreateDatabase("QuoteDatabase", MODE_PRIVATE, null);

            //sqLiteDatabase.execSQL("DROP TABLE filterLibrary");

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS filterLibrary (filterName VARCHAR, active BOOLEAN)");

            Cursor c = sqLiteDatabase.rawQuery("SELECT COUNT (*) FROM filterLibrary WHERE active = 1", null);

            if (c != null) {
                c.moveToFirst();
                if (c.getInt(0) == 0) {
                    Log.i("Filter Check Result", "No Filters Found, applying defaults");

                    Toast.makeText(this, "No enabled filters found.\nEnabling default filters...", Toast.LENGTH_SHORT).show();

//stuff here?

                } else {
                    Log.i("Filter Check Result", "Filters Found");
                }
                c.close();


            }
        } catch (Exception e) {e.printStackTrace();}
    }
*/

    public void countFavourites () {

        try {

            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("QuoteDatabase", MODE_PRIVATE, null);

            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM quoteLibrary WHERE favourite = 1", null);

            Log.i("countFavourites", "Starting...");

            c.moveToFirst();
            favouriteCount = c.getCount();
            Log.i("countFavourites", "Favourites Count = " + favouriteCount);
            c.close();

        } catch (Exception e) {e.printStackTrace();}
    }

    public void generateSearchString () {

        ArrayList<String> enabledFilters = new ArrayList<>();
        searchString = "SELECT * FROM quoteLibrary WHERE categories LIKE ";

        try {

            sqLiteDatabase = this.openOrCreateDatabase("QuoteDatabase", MODE_PRIVATE, null);

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS filterLibrary (filterName VARCHAR, active BOOLEAN)");

            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM filterLibrary WHERE active = 1", null);

            int filterIndex = c.getColumnIndex("filterName");


            c.moveToFirst();
            if (c != null && c.getCount() > 0) {
                do {
                    //Log.i("Filter Memory", c.getString(filterIndex));
                    enabledFilters.add(c.getString(filterIndex));
                } while (c.moveToNext());
                c.close();

            } else {

                Log.i("Filter Memory", "None present, using defaults");
                Toast.makeText(this, "No enabled filters found.\nEnabling default filters...", Toast.LENGTH_SHORT).show();

                c = sqLiteDatabase.rawQuery("SELECT * FROM filterLibrary", null);
                c.moveToFirst();

                do {
                    //Log.i("Filter Memory", c.getString(filterIndex));
                    enabledFilters.add(c.getString(filterIndex));
                } while (c.moveToNext());

            }
        } catch (Exception e) {e.printStackTrace();}

        Log.i("generateSearchString", enabledFilters.toString());

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
                    //Log.i("Quote ID", c.getString(idIndex));
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
                    //Log.i("Num", Integer.toString(num));
                    if (!selectedQuoteIds.contains(viableQuoteIds.get(num))) {
                        selectedQuoteIds.add(viableQuoteIds.get(num));
                        i = selectedQuoteIds.size();
                        //Log.i("New Quote ID", viableQuoteIds.get(num) + " added.");
                    }
                }

            } else if (viableQuoteIds.size() != 0) {
                max = viableQuoteIds.size();
                //Log.i("Max No. is", Integer.toString(viableQuoteIds.size()));

                while (i < max) {
                    Random random = new Random();
                    num = (random.nextInt(viableQuoteIds.size()));
                    //Log.i("Num", Integer.toString(num));
                    if (!selectedQuoteIds.contains(viableQuoteIds.get(num))) {
                        selectedQuoteIds.add(viableQuoteIds.get(num));
                        i = selectedQuoteIds.size();
                        //Log.i("New Quote ID", viableQuoteIds.get(num) + " added.");
                    }
                }
            } else {
                Log.i("ERROR chooseQuote", "What the heck, how did we end up with 0!?");
            }

            Log.i("chooseQuote", "ID List Done, chosen: " + selectedQuoteIds.toString());


            // List all quotes in Log & create Quote Content

            i = 0;
            while (i < max) {

                searchString = "SELECT * FROM quoteLibrary WHERE id = " + selectedQuoteIds.get(i);

                c = sqLiteDatabase.rawQuery(searchString, null);

                c.moveToFirst();

                Log.i("chooseQuote", "Quote ID: " + c.getString(idIndex) + ". " + c.getString(quoteIndex) + " - " + c.getString(authorIndex) + ". [" + c.getString(categoriesIndex) + "]");
                quoteContent = c.getString(quoteIndex) + "\n - " + c.getString(authorIndex);
                quoteContentArray.add(quoteContent);

                i++;
            }


            //Log.i("Quote Array", quoteContentArray.toString());

            quoteTextView.setText(quoteContentArray.get(quoteNo).toString());
            //Log.i("SETTING QUOTE", quoteContentArray.get(quoteNo).toString());

            //quoteTextView.setTextSize(50);

            updateTextView();


            //Log.i("Quote No", Integer.toString(quoteNo));
            c = sqLiteDatabase.rawQuery("SELECT * FROM quoteLibrary WHERE id = " + selectedQuoteIds.get(0), null);
            c.moveToFirst();
            //Log.i("Setup Favourite Check","ID: " + selectedQuoteIds.get(0) + ". 1 = Fav, 0 = Not Fav: " + c.getString(favouriteIndex));
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
/*
        int charCount = quoteTextView.getText().length();
        Log.i("MainActivity", "Quote character count = " + charCount);
        if (charCount < 80) {
            quoteTextView.setTextSize(36);
        } else if (charCount < 160) {
            quoteTextView.setTextSize(28);
        } else if (charCount < 240) {
            quoteTextView.setTextSize(24);
        }
        Log.i("MainActivity", "Updating text size");
        */
    }

    public void favouriteButtonClicked (View view) {

        Log.i("FavouriteButtonClicked", selectedQuoteIds.get(quoteNo).toString());

        try {
            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM quoteLibrary WHERE id = " + selectedQuoteIds.get(quoteNo), null);
            c.moveToFirst();
            Log.i("Favourite Button", c.getString(favouriteIndex));
            if (c.getInt(favouriteIndex) == 0) {
                if (favouriteCount < favouriteLimit) {
                    favouriteButton.setBackgroundResource(R.drawable.simple_red_heart_small);
                    sqLiteDatabase.execSQL("UPDATE quoteLibrary SET favourite = 1 WHERE id = " + selectedQuoteIds.get(quoteNo));
                    Log.i("Favourited", selectedQuoteIds.get(quoteNo).toString());
                    favouriteCount++;
                } else {
                    Log.i("FavouriteButtonClicked", "Unable to add, at favourites maximum.");
                    Toast.makeText(this, "You are at your favourites maximum of " + favouriteLimit + " quotes!", Toast.LENGTH_SHORT).show();
                }
            } else {
                favouriteButton.setBackgroundResource(R.drawable.simple_hollow_heart_small);
                sqLiteDatabase.execSQL("UPDATE quoteLibrary SET favourite = 0 WHERE id = " + selectedQuoteIds.get(quoteNo));
                Log.i("Unfavourited", selectedQuoteIds.get(quoteNo).toString());
                favouriteCount--;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void menuButtonClicked (View view) {
        Log.i("MainActivity", "Menu Button Clicked!");


        if (menuOpen) {

            favouritesMenuButton.setVisibility(View.GONE);
            //favouritesMenuButton.animate().translationXBy(150).setDuration(500);
            filterMenuButton.setVisibility(View.GONE);
            //filterMenuButton.animate().translationXBy(300).setDuration(500);
            menuOpen = false;
            TransitionManager.beginDelayedTransition(expandedMenu);

        } else {

            favouritesMenuButton.setVisibility(View.VISIBLE);
            //favouritesMenuButton.animate().translationXBy(-150).setDuration(500);
            filterMenuButton.setVisibility(View.VISIBLE);
            //filterMenuButton.animate().translationXBy(-300).setDuration(500);
            menuOpen = true;
            TransitionManager.beginDelayedTransition(expandedMenu);

        }
    }

    public void favouritesMenuButtonClicked (View view) {
        Log.i("MainActivity", "Favourites Menu Button Clicked!");

        Intent intent = new Intent(getApplicationContext(), FavouritesActivity.class);
        startActivityForResult(intent, 1);

        favouritesMenuButton.setVisibility(View.GONE);
        filterMenuButton.setVisibility(View.GONE);
        menuOpen = false;
        TransitionManager.beginDelayedTransition(expandedMenu);

    }

    public void filterMenuButtonClicked (View view) {
        Log.i("MainActivity", "Filter Menu Button Clicked!");

        Intent intent = new Intent(getApplicationContext(), FilterActivity.class);
        startActivity(intent);

        favouritesMenuButton.setVisibility(View.GONE);
        filterMenuButton.setVisibility(View.GONE);
        menuOpen = false;
        TransitionManager.beginDelayedTransition(expandedMenu);
    }


/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
*/

/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.favourites:
                Log.i("Menu Item", "Favourites selected");
                Intent intent = new Intent(getApplicationContext(), FavouritesActivity.class);
                startActivityForResult(intent, 1);
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
*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("onActivityResult", "Result called!");
        if (requestCode == 1) {
            checkFavourite();
            countFavourites();
            Log.i("onActivityResult", "Result code = 1!");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
