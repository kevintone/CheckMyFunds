package com.example.kevintone.checkmyfunds;

import android.support.v7.app.AppCompatActivity;
import android.support.annotation.Nullable;
import android.view.View;
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
    Button btnAddTransaction, btnAddFunds;
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
        btnAddTransaction = (Button) findViewById(R.id.btnAddTransaction);
        btnAddFunds = (Button) findViewById(R.id.btnAddFunds);

        btnAddTransaction.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //A Transaction means that its a negative value ->
                String description = etDescription.getText().toString();
                Double amountToChange = Double.valueOf(etAmountToChange.getText().toString());
                String classText = etClassText.getText().toString();
                Double amountToNegative = amountToChange * 2;
                amountToChange = amountToChange - amountToNegative;

                if(description.length() != 0 && amountToChange != 0 &&
                        classText.length() != 0) {
                    addTransactionToDB(description, classText, amountToChange);
                    etDescription.setText("");
                    etAmountToChange.setText("");
                    etClassText.setText("");
                } else {
                    Toast.makeText(AddDataToDB.this, "You must enter something in all fields!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnAddFunds.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String description = etDescription.getText().toString();
                Double amountToChange = Double.valueOf(etAmountToChange.getText().toString());
                String classText = etClassText.getText().toString();

                if(description.length() != 0 && amountToChange != 0 &&
                        classText.length() != 0) {
                    addTransactionToDB(description, classText, amountToChange);
                    etDescription.setText("");
                    etAmountToChange.setText("");
                    etClassText.setText("");
                } else {
                    Toast.makeText(AddDataToDB.this, "You must enter something in all fields!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void addTransactionToDB(String description, String classification, Double amount) {
        boolean insertDataToDB = myDB.addTransactionToDatabase(description, classification, amount);

        if (insertDataToDB == true) {
            Toast.makeText(AddDataToDB.this, "Succesfully Added To DB", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(AddDataToDB.this, "ERROR: Could NOT Add to DB", Toast.LENGTH_LONG).show();
        }

    }

}
