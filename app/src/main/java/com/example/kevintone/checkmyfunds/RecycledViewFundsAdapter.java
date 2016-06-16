package com.example.kevintone.checkmyfunds;

import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;

/**
 * Created by kevintone on 6/15/16.
 */
public class RecycledViewFundsAdapter extends RecyclerView.Adapter<RecycledViewFundsAdapter.ViewHolder> {

    private static String TAG = "RecycledViewFundsAdapter";
    private ArrayList<Transaction> fundHistory;

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

        //Get Classification
        viewHolder.getViewClassification().setBackgroundColor(Color.BLUE);

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
