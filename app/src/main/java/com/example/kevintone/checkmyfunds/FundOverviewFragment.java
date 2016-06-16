package com.example.kevintone.checkmyfunds;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import android.widget.Toast;
import android.widget.TextView;
import android.graphics.Color;


import android.support.v7.widget.RecyclerView;

/**
 * Created by kevintone on 6/7/16.
 */
public class FundOverviewFragment extends Fragment{

    private static final String TAG = "FundOverviewFragment";
    private RecycledViewFundsAdapter mFundsAdapter;
    private RecyclerView mRecyclerView;
    private HashMap<String, Integer> classColor;
    FundDatabase myDB;
    private ArrayList<Transaction> fundHistory;
    Transaction transaction;

    @Override
    public void onCreate(Bundle savedBundleInstance) {
        super.onCreate(savedBundleInstance);
        fundHistory = new ArrayList<>();
        classColor = new HashMap<String, Integer>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedBundleInstance) {
        View rootView = inflater.inflate(R.layout.fundoverview_layout, container, false);
        rootView.setTag(TAG);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycledview_funds);

        myDB = new FundDatabase(getActivity());

        /*
         * Sets the current  usage number on the funds overview page.
         * The getCurrentBalance method adds up all the transactions to give  finals total
         * If the final total is positive -> Text will be green
         *      Else-> Negative -> Text will be Red
         */
        TextView textViewGetBalance = (TextView) rootView.findViewById(R.id.textViewGetCurrentBalance);
        double currentBalance = myDB.getCurrentBalance();
        if(currentBalance <= 0) {
            textViewGetBalance.setTextColor(Color.RED);
            textViewGetBalance.setText(String.format("%.2f", currentBalance));
        } else {
            textViewGetBalance.setTextColor(Color.GREEN);
            textViewGetBalance.setText("+" +String.format("%.2f", currentBalance));
        }

        /*
         * Gets a list of every Unique class and assigns it a random color (for now)
         * When the list is populated, the classifications for repeated classes should
         * appear the same color.
         *
        ArrayList<String> classificationList = myDB.getUniqueClasses();
        Random rand = new Random();
        for(int i = 0; i < classificationList.size(); i++) {
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);
            classColor.put(classificationList.get(i), Color.rgb(r,g,b));
        }
        View viewClassification = (View) rootView.findViewById(R.id.sidebar);
*/

        Cursor data = myDB.getListContents();
        int numRows = data.getCount();

        if (numRows == 0) {
            Toast.makeText(getActivity(), "Database is currently empty", Toast.LENGTH_LONG).show();
        } else {
            int i = 0;

            while (data.moveToNext()) {
                transaction = new Transaction(data.getString(0), data.getString(1), data.getString(2),
                        data.getDouble(3));
                fundHistory.add(transaction);
            }

            mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), R.drawable.divider));
            mFundsAdapter = new RecycledViewFundsAdapter(fundHistory);
            mRecyclerView.setAdapter(mFundsAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        }
        return rootView;
    }

}
