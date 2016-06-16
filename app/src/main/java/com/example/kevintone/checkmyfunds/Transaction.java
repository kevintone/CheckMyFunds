package com.example.kevintone.checkmyfunds;


//import java.util.ArrayList;

/**
 * Created by kevintone on 6/7/16.
 */
public class Transaction {
    //private ArrayList<String> allClasses;
    private String description;
    private String dateTime;
    private String classText;
    private Double amountToChange;

    public Transaction(String description, String dateTime, String classText, Double amountToChange) {
        this.description = description;
        this.dateTime = dateTime;
        this.classText = classText;
        //this.allClasses = new ArrayList<String>();
        this.amountToChange = amountToChange;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getClassText() {
        return this.classText;
    }

    public void setClassText(String classText) {
        this.classText = classText;
    }

    public Double getAmountToChange() {
        return this.amountToChange;
    }

    public void setAmountToChange(Double amountToChange) {
        this.amountToChange = amountToChange;
    }
}
