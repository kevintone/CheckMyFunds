package com.example.kevintone.checkmyfunds;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class OverviewFunds extends Fragment {

    private double currentBalance;
    private double averageMoneyUsed;
    private boolean hasOverTwoMonth;
    private ArrayList<Double> fundHistory;
    private ArrayAdapter<Double> mFundsAdapter;

    public OverviewFunds() {
        currentBalance = 0;
        averageMoneyUsed = 0;
        hasOverTwoMonth = false;
    }

    public void changeToBalance(double amount) {
        if(amount <= 0) {
            currentBalance -= amount;
        } else {
            //Then its a positive gain
            currentBalance += amount;
        }
    }

    public double getCurrentBalance(){
        return currentBalance;
    }

    public double getAverageMoneyUsed(){
        if(hasOverTwoMonth) {
            return averageMoneyUsed;
        }

        return -1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
