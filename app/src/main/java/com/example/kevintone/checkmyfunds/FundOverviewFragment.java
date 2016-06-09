package com.example.kevintone.checkmyfunds;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.database.Cursor;
import java.util.ArrayList;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by kevintone on 6/7/16.
 */
public class FundOverviewFragment extends Fragment{

    FundDatabase myDB;
    ArrayList<Transaction> fundHistory;
    ListView listView;
    Transaction transaction;

    @Override
    public void onCreate(Bundle savedBundleInstance) {
        super.onCreate(savedBundleInstance);
        fundHistory = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedBundleInstance) {
        View rootView = inflater.inflate(R.layout.fundoverview_layout, container, false);

        //setContentView(R.layout.fundoverview_layout);
        //myDB = new FundDatabase(this);
        //setContentView(R.layout.fundoverview_layout);
        myDB = new FundDatabase(getActivity());

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
                //Database Tests
                //System.out.println();
                //System.out.println();
                //i++;
            }

            TwoColumnListAdapter adapter = new TwoColumnListAdapter(getActivity(),
                    R.layout.fragment_main, fundHistory);
            listView = (ListView) rootView.findViewById(R.id.listview_funds);
            listView.setAdapter(adapter);
        }
        return rootView;
    }

}
