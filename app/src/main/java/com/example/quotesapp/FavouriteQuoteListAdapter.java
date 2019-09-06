package com.example.quotesapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FavouriteQuoteListAdapter extends RecyclerView.Adapter<FavouriteQuoteListAdapter.FavouriteQuoteViewHolder>  {

    public List<FavouriteQuoteListItem> listItems;
    private int activeFav;
    private int pos;

    public ArrayList<Integer> deleteList = new ArrayList<Integer>();

    int mExpandedPosition = -1;

    RecyclerView recyclerView;



    public FavouriteQuoteListAdapter(List <FavouriteQuoteListItem> listItems) {
        this.listItems = listItems;
    }

    @Override
    public FavouriteQuoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.favourite_quote_list_item, parent, false);
        return new FavouriteQuoteViewHolder(view);
    }


    @Override
    public void onBindViewHolder(FavouriteQuoteViewHolder holder, int position)  {
        FavouriteQuoteListItem listItem = listItems.get(position);
        holder.bind(listItem);


        final boolean isExpanded = (position == mExpandedPosition);
        final boolean isDeleted = (deleteList.contains(position));

        //Log.i("onBindViewHolder", "Setup isExpanded = " + isExpanded);

        holder.expandConstraint.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.textViewQuote.setMaxLines(isExpanded ? Integer.MAX_VALUE : 2);
        holder.itemView.setActivated(isExpanded);

        //Log.i("onBindViewHolder", "Setup isDeleted = " + isDeleted);

        holder.deleteButton.setBackgroundResource(isDeleted ? R.drawable.simple_crossed_heart_small : R.drawable.simple_grey_heart_small);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1 : position;


                //TransitionManager.beginDelayedTransition(recyclerView);   //- Can't make this damn thing work correctly!!

                //Log.i("onBindViewHolder", "Clicked isExpanded = " + isExpanded);

                pos = holder.getAdapterPosition();

                activeFav = position;
                Log.i("onClick", "Pos = " + pos + ". Active Fav = " + activeFav);
                notifyDataSetChanged();

                //make the view scroll to the expanded item
/*
                int val = 0;
                Log.i("position", Integer.toString(position));
0
                recyclerView.smoothScrollToPosition(5);*/

            }
        });


    }



/*
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        this.recyclerView = recyclerView;
    }*/

/*    //working simple expander
    @Override
    public void onBindViewHolder(FavouriteQuoteViewHolder holder, int position) {
        FavouriteQuoteListItem listItem = listItems.get(position);
        holder.bind(listItem);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean expanded = listItem.isExpanded();
                listItem.setExpanded(!expanded);
                notifyItemChanged(position);
            }
        });
    }
*/

    public int getActiveFav(){
        return activeFav;
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class FavouriteQuoteViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewQuote;
        private View expandConstraint;
        private View deleteButton;
        private View recyclerView;

        public FavouriteQuoteViewHolder(View itemView) {
            super(itemView);

            textViewQuote = itemView.findViewById(R.id.textViewQuote);
            expandConstraint = itemView.findViewById(R.id.expandConstraint);
            recyclerView = itemView.findViewById(R.id.recyclerView);

            deleteButton = itemView.findViewById(R.id.deleteButton);
        }

        private void bind(FavouriteQuoteListItem listItem){
            boolean expanded = listItem.isExpanded();
            expandConstraint.setVisibility(expanded ? View.VISIBLE : View.GONE);

            textViewQuote.setText(listItem.getQuote());
        }

    }


}
