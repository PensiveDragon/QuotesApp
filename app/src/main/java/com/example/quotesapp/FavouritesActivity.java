package com.example.quotesapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FavouritesActivity extends AppCompatActivity {

    public RecyclerView rvFavourites;
    RecyclerView.Adapter adapter;
    private List<FavouriteQuoteListItem> listItems;
    TextView textView;
    ImageView imageView;
    SQLiteDatabase sqLiteDatabase;



    //* Get favourites page to automatically fill in list from SQLite search for favourite tagged quotes

    //* When pressed, get list to expand out to show full quote

    // When expanded out show button to maximise quote
    public void maximiseButtonClicked (View view) {


        // find a way of pulling the quote ID from the recyclerView to start this process off
        Log.i("Button Clicked", "Maximise Button Clicked! ID: ");

        Toast.makeText(this, "This is the maximise button!", Toast.LENGTH_SHORT).show();

    }

    // Button to unfavourite a quote
    public void deleteButtonClicked (View view) {

        // find a way of pulling the quote ID from the expanded favourite quote in order to queue it for unfavouriting.
        Log.i("Button Clicked", "Delete Button Clicked! ID: ");

        Toast.makeText(this, "This is the delete button!", Toast.LENGTH_SHORT).show();

        sqLiteDatabase = this.openOrCreateDatabase("QuoteDatabase", MODE_PRIVATE, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        rvFavourites = findViewById(R.id.recyclerView);
        rvFavourites.setHasFixedSize(true);
        rvFavourites.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<FavouriteQuoteListItem>();

        try {

            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("QuoteDatabase", MODE_PRIVATE, null);

            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM quoteLibrary WHERE favourite = 1", null);

            int quoteIndex = c.getColumnIndex("quote");
            int authorIndex = c.getColumnIndex("author");
            int favouriteIndex = c.getColumnIndex("favourite");
            int categoriesIndex = c.getColumnIndex("categories");
            int idIndex = c.getColumnIndex("id");

            Log.i("Find Favourites", "Starting...");

            c.moveToFirst();
            if (c != null && c.getCount() > 0) {
                imageView.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.INVISIBLE);
                do {
                    Log.i("Favourite Quotes", "Quote ID: " + c.getString(idIndex) + ". " + c.getString(quoteIndex) + " - " + c.getString(authorIndex) + ". [" + c.getString(categoriesIndex) + "]");

                    FavouriteQuoteListItem listItem = new FavouriteQuoteListItem(c.getString(quoteIndex) + "\n - " + c.getString(authorIndex), c.getInt(idIndex));

                    listItems.add(listItem);
                } while (c.moveToNext());
                c.close();
            } else {
                imageView.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                Log.i("Favourites Memory", "No Favourites");
            }

        } catch (Exception e) {e.printStackTrace();}
        adapter = new FavouriteQuoteListAdapter(listItems);
        rvFavourites.setAdapter(adapter);

        Log.i("Find Favourites", "Finished");
    }
}
