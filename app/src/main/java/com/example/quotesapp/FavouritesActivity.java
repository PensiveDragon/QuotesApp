package com.example.quotesapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FavouritesActivity extends AppCompatActivity {

    public RecyclerView rvFavourites;
    //RecyclerView.Adapter adapter;
    FavouriteQuoteListAdapter adapter;
    private List<FavouriteQuoteListItem> listItems;
    TextView textView;
    TextView refreshButton;
    ImageView imageView;
    SQLiteDatabase sqLiteDatabase;

    int favouriteIndex;
    Button deleteButton;
    Button maximiseButton;


    // When expanded out show button to maximise quote
    public void maximiseButtonClicked (View view) {


        int position = adapter.getActiveFav();

        // find a way of pulling the quote ID from the recyclerView to start this process off
        Log.i("Button Clicked", "Maximise Button Clicked! ID: " + listItems.get(position).getId());

        Toast.makeText(this, "This is the maximise button!", Toast.LENGTH_SHORT).show();

    }

    // Button to unfavourite a quote
    public void deleteButtonClicked (View view) {

        int position = adapter.getActiveFav();

        Log.i("Button Clicked", "Delete Button Clicked! ID: " + listItems.get(position).getId());

        sqLiteDatabase = this.openOrCreateDatabase("QuoteDatabase", MODE_PRIVATE, null);

        try {
            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM quoteLibrary WHERE id = " + listItems.get(position).getId(), null);
            c.moveToFirst();

            favouriteIndex = c.getColumnIndex("favourite");

            Log.i("deleteButtonClicked", "Favourite status = " + c.getString(favouriteIndex));

            // checks where the favourite bool is 0 (not fav'd) or 1 (fav'd)
            if (c.getInt(favouriteIndex) == 0) {

                Log.i("FavouriteIndex", Integer.toString(c.getInt(favouriteIndex)));

                // updates the icon

                adapter.deleteList.remove(Integer.valueOf(position));
                //Log.i("DeleteList", "Undelete Item: " + position);
                adapter.notifyItemChanged(position);

                // changes favourite value to 1
                sqLiteDatabase.execSQL("UPDATE quoteLibrary SET favourite = 1 WHERE id = " + listItems.get(position).getId());
                Log.i("Refavourited", "Quote ID: " + listItems.get(position).getId());              // logs data
                Toast.makeText(this, "Quote ref: " + listItems.get(position).getId() + " not to be deleted.", Toast.LENGTH_SHORT).show();  // best I can do for visual feedback
            } else {

                adapter.deleteList.add(Integer.valueOf(position));
                //Log.i("DeleteList", "Delete Item: " + position);
                adapter.notifyItemChanged(position);

                sqLiteDatabase.execSQL("UPDATE quoteLibrary SET favourite = 0 WHERE id = " + listItems.get(position).getId());   // changes favourite value to 0
                Log.i("Unfavourited", "Quote ID: " + listItems.get(position).getId());
                Toast.makeText(this, "Quote ref: " + listItems.get(position).getId() + " to be deleted.", Toast.LENGTH_SHORT).show();
            }
            c.close();

            checkRefreshButton();
            Log.i("DeleteList", "Positions: " + adapter.deleteList.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkRefreshButton () {
        Log.i("CheckRefreshButton", "Delete list size: " + adapter.deleteList.size());
        if (adapter.deleteList.size() > 0) {
            refreshButton.setEnabled(true);
            // set text to the number of favourited quotes and note to click to refresh the list
            refreshButton.setText(listItems.size() + " / 100 (Refresh)");
        } else {
            refreshButton.setEnabled(false);
            // update text to number of favourited quotes
            refreshButton.setText(listItems.size() + " / 100");

        }
    }

    public void refreshButtonPressed (View view) {
        Log.i("RefreshButton", "Button pressed!");
        Toast.makeText(this, "REFRESH BUTTON PRESSED!", Toast.LENGTH_SHORT).show();

        updateUI();
    }

    public void updateUI () {
        Log.i("Update UI", "Updating UI");


        listItems.clear();

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

        checkRefreshButton();
        Log.i("Find Favourites", "Finished");
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("hasBackPressed", true);
        finish();
        super.onBackPressed();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        Log.i("Favourites Activity", "Initialising Favourites Activity");

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        rvFavourites = findViewById(R.id.recyclerView);
        deleteButton = findViewById(R.id.deleteButton);
        maximiseButton = findViewById(R.id.maximiseButton);
        refreshButton = findViewById(R.id.refreshButton);

        rvFavourites.setHasFixedSize(true);
        rvFavourites.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<FavouriteQuoteListItem>();

        Log.i("Favourites Activity", "Calling updateUI from onCreate");
        updateUI();
    }
}
