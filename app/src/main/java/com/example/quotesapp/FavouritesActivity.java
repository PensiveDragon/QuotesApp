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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FavouritesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
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
/*
        try {
            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM quoteLibrary WHERE id = " + FavouriteQuoteListItem.getId(quoteNo), null);
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
*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

//        ListView listView = findViewById(R.id.listView);

//        List<Map<String,String>> quoteData = new ArrayList<>();

        try {

            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("QuoteDatabase", MODE_PRIVATE, null);

            // Do I need the below line here?
//            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS quoteLibrary (quote VARCHAR, author VARCHAR, categories VARCHAR, favourite BOOLEAN, id INTEGER PRIMARY KEY)");

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
/*                    Map<String, String> quoteInfo = new HashMap<>();
                    quoteInfo.put("quote", c.getString(quoteIndex));
                    quoteInfo.put("author", c.getString(authorIndex));
                    quoteData.add(quoteInfo);
*/
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
/*
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, quoteData,android.R.layout.simple_list_item_2 , new String[] {"quote", "author"}, new int[] {android.R.id.text1, android.R.id.text2});
        listView.setAdapter(simpleAdapter);
*/
        adapter = new FavouriteQuoteListAdapter(listItems);
        recyclerView.setAdapter(adapter);

        Log.i("Find Favourites", "Finished");
/*
        for (int i = 1; i <= 5; i++) {
            Map<String, String> quoteInfo = new HashMap<>();
            quoteInfo.put("quote", "Quote Content " + i);
            quoteInfo.put("author", "Author " + i);
            quoteData.add(quoteInfo);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, quoteData,android.R.layout.simple_list_item_2 , new String[] {"quote", "author"}, new int[] {android.R.id.text1, android.R.id.text2});
        listView.setAdapter(simpleAdapter);
        */
    }
}
