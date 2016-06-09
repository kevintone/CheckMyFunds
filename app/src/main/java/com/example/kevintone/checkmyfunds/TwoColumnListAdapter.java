package com.example.kevintone.checkmyfunds;


import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Color;
import java.util.ArrayList;

/**
 * Created by kevintone on 6/8/16.
 */
public class TwoColumnListAdapter extends ArrayAdapter<Transaction> {

    private LayoutInflater mInflater;
    private ArrayList<Transaction> fundHistory;
    private int mViewResourceId;

    public TwoColumnListAdapter(Context context, int textViewResourceId, ArrayList<Transaction> fundHistory) {
        super(context, textViewResourceId, fundHistory);
        this.fundHistory = fundHistory;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        convertView = mInflater.inflate(mViewResourceId, null);

        Transaction funds = fundHistory.get(position);

        if(funds != null) {
            TextView textDescription = (TextView) convertView.findViewById(R.id.textDescription);
            TextView textAmountToChange = (TextView) convertView.findViewById(R.id.textAmountToChange);

            if(textDescription != null) {
                textDescription.setText(funds.getDescription());
            }

            if(textAmountToChange != null){
                if(funds.getAmountToChange() < 0) {
                    //Red for NEGATIVE Decrease
                    textAmountToChange.setText(funds.getAmountToChange().toString());
                    textAmountToChange.setTextColor(Color.RED);
                } else {
                    //Green for POSITIVE Increase
                    textAmountToChange.setText("+" + funds.getAmountToChange().toString());
                    textAmountToChange.setTextColor(Color.GREEN);
                }
            }
        }

        return convertView;
    }

}
