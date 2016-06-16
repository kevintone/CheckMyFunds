package com.example.kevintone.checkmyfunds;

import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

/**
 * Created by kevintone on 6/15/16.
 */
public class RecycledViewFundsAdapter extends RecyclerView.Adapter<RecycledViewFundsAdapter.ViewHolder> {

    private static String TAG = "RecycledViewFundsAdapter";
    private ArrayList<Transaction> fundHistory;
    private HashMap<String, Integer> classificationColor;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textDescription;
        private TextView textDate;
        private TextView textAmountToChange;
        private View viewClassification;

        public ViewHolder(View v) {
            super(v);
            textDescription = (TextView) v.findViewById(R.id.textDescription);
            textDate = (TextView) v.findViewById(R.id.textDate);
            textAmountToChange = (TextView) v.findViewById(R.id.textAmountToChange);
            viewClassification = (View) v.findViewById(R.id.sidebar);
            v.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                }
            });
        }

        public TextView getTextDescription() {
            return this.textDescription;
        }

        public TextView getTextDate() {
            return this.textDate;
        }

        public View getViewClassification() {
            return this.viewClassification;
        }

        public TextView getTextAmountToChange() {
            return this.textAmountToChange;
        }
    }

    public RecycledViewFundsAdapter(ArrayList<Transaction> funds) {
        this.fundHistory = funds;
        this.classificationColor = new HashMap<String, Integer>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_main, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Transaction transaction = fundHistory.get(position);

        //Get Description
        viewHolder.getTextDescription().setText(transaction.getDescription());
        viewHolder.getTextDescription().setTextColor(Color.BLACK);

        //Get Date/Time
        viewHolder.getTextDate().setText(transaction.getDateTime());
        viewHolder.getTextDate().setTextColor(Color.GRAY);

        /*
         * Get a Random Color for each classification
         * Gets a list of every Unique class and assigns it a random color (for now)
         * When the list is populated, the classifications for repeated classes should
         * appear the same color.
         *
         */
        String dbHasClassification = transaction.getClassText();
        Random rand = new Random();
        if (!classificationColor.containsKey(dbHasClassification)) {
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);
            classificationColor.put(dbHasClassification, Color.rgb(r,g,b));
            viewHolder.getViewClassification().setBackgroundColor(Color.rgb(r,g,b));
        } else {
            viewHolder.getViewClassification().setBackgroundColor(classificationColor.get(dbHasClassification));
        }



        //Get The amount of Transaction
        if(transaction.getAmountToChange() < 0) {
            //Negative Transaction --> Taking away from Balance, RED for NEGATIVE
            viewHolder.getTextAmountToChange().setText(String.format("%.2f", transaction.getAmountToChange()));
            viewHolder.getTextAmountToChange().setTextColor(Color.RED);
        } else {
            //Green for POSITIVE
            viewHolder.getTextAmountToChange().setText("+" + String.format("%.2f", transaction.getAmountToChange()));
            viewHolder.getTextAmountToChange().setTextColor(Color.GREEN);
        }


    }

    @Override
    public int getItemCount() {
        return fundHistory.size();
    }
}
