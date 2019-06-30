package com.example.quotesapp;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class FavouriteQuoteListAdapter extends RecyclerView.Adapter<FavouriteQuoteListAdapter.FavouriteQuoteViewHolder>  {

    public List<FavouriteQuoteListItem> listItems;
    /*private OnFavouriteListener mOnFavouriteListener;*/
    private int activeFav;

    int mExpandedPosition = -1;

    RecyclerView recyclerView;


    public FavouriteQuoteListAdapter(List <FavouriteQuoteListItem> listItems/*, OnFavouriteListener onFavouriteListener*/) {
        this.listItems = listItems;
        /*this.mOnFavouriteListener = onFavouriteListener;*/
    }

    @Override
    public FavouriteQuoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.favourite_quote_list_item, parent, false);
        return new FavouriteQuoteViewHolder(view/*, mOnFavouriteListener*/);
    }


    @Override
    public void onBindViewHolder(FavouriteQuoteViewHolder holder, int position)  {
        FavouriteQuoteListItem listItem = listItems.get(position);
        holder.bind(listItem);


        final boolean isExpanded = position == mExpandedPosition;
        holder.expandConstraint.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.textViewQuote.setMaxLines(isExpanded ? Integer.MAX_VALUE : 2);
        holder.itemView.setActivated(isExpanded);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1 : position;
                //TransitionManager.beginDelayedTransition(recyclerView);   - Can't make this damn thing work correctly!!

                activeFav = position;
                Log.i("Active Fav", Integer.toString(activeFav));
                notifyDataSetChanged();

                //make the view scroll to the expanded item

            }
        });
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        this.recyclerView = recyclerView;
    }

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



    @Override
    public int getItemCount() {
        return listItems.size();
    }


    public int getActiveFav() {
        return activeFav;
    }


    public class FavouriteQuoteViewHolder extends RecyclerView.ViewHolder/* implements View.OnClickListener*/ {

        private TextView textViewQuote;
        private View expandConstraint;
        /*OnFavouriteListener onFavouriteListener;*/

        public FavouriteQuoteViewHolder(View itemView /*, OnFavouriteListener onFavouriteListener*/) {
            super(itemView);

            textViewQuote = itemView.findViewById(R.id.textViewQuote);
            expandConstraint = itemView.findViewById(R.id.expandConstraint);
            recyclerView = itemView.findViewById(R.id.recyclerView);
           /*this.onFavouriteListener = onFavouriteListener;*/
        }

        private void bind(FavouriteQuoteListItem listItem){
            boolean expanded = listItem.isExpanded();
            expandConstraint.setVisibility(expanded ? View.VISIBLE : View.GONE);

            textViewQuote.setText(listItem.getQuote());

            /*itemView.setOnClickListener(this);*/
        }
/*
        @Override
        public void onClick(View view) {
            onFavouriteListener.onFavouriteClick(getAdapterPosition());

        }*/
    }/*

    public interface OnFavouriteListener {
        void onFavouriteClick(int position);
    }*/
}
