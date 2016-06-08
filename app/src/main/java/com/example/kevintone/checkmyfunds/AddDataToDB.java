package com.example.kevintone.checkmyfunds;

import android.support.v7.app.AppCompatActivity;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.database.Cursor;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by kevintone on 6/8/16.
 */
public class AddDataToDB extends AppCompatActivity {
    FundDatabase myDB;
    EditText etDescription, etAmountToChange, etClassText;
    Button btnAdd;
    ArrayList<Transaction> fundHistory;
    ListView listview;
    Transaction transaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.addfunds_layout);

        myDB = new FundDatabase(this);
        etDescription = (EditText) findViewById(R.id.editTextDescription);
        etAmountToChange = (EditText) findViewById(R.id.editTextAmountToChange);
        etClassText = (EditText) findViewById(R.id.editTextClassText);



    }

    public void addTransactionToDB() {
        //TODO Add Transaction with insert method

    }

}
