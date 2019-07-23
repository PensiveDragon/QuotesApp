package com.example.quotesapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FavViewerActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    TextView quoteTextView;

    int id;
    String quoteContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_viewer);

        quoteTextView = findViewById(R.id.quoteTextView);

        Intent intent = getIntent();

        id = intent.getIntExtra("id", -1);

        findQuote();

    }

    public void findQuote () {
        try {

            sqLiteDatabase = this.openOrCreateDatabase("QuoteDatabase", MODE_PRIVATE, null);

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS quoteLibrary (quote VARCHAR, author VARCHAR, categories VARCHAR, favourite BOOLEAN, id INTEGER PRIMARY KEY)");

            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM quoteLibrary WHERE id = " + id , null);

            int quoteIndex = c.getColumnIndex("quote");
            int authorIndex = c.getColumnIndex("author");
            int idIndex = c.getColumnIndex("id");

            c.moveToFirst();
            if (c != null) {
                Log.i("Quote ID", c.getString(idIndex));

                Log.i("QuoteContent", c.getString(quoteIndex) + " - " + c.getString(authorIndex));
                quoteContent = c.getString(quoteIndex) + "\n - " + c.getString(authorIndex);

                quoteTextView.setText(quoteContent);
                c.close();
            }

            /*

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
*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("hasBackPressed", true);
        finish();
        super.onBackPressed();

    }

    // functionality to load in the passed over quote id from the favourites list


}
