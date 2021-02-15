package com.kbrosapp.account;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "AccountsEntry")
public class AccountsEntry {

    @PrimaryKey(autoGenerate = true)
    private int Entry_Id;

    private Long Entry_Date;

    private String Entry_Dec;

    private double Entry_Credit;

    private double Entry_Debit;

    public AccountsEntry(Long Entry_Date, String Entry_Dec, double Entry_Credit, double Entry_Debit) {
        this.Entry_Date = Entry_Date;
        this.Entry_Dec = Entry_Dec;
        this.Entry_Credit = Entry_Credit;
        this.Entry_Debit = Entry_Debit;
    }

    public void setEntry_Id(int entry_Id) {
        Entry_Id = entry_Id;
    }


    public int getEntry_Id() {
        return Entry_Id;
    }

    public Long getEntry_Date() {
        return Entry_Date;
    }

    public String getEntry_Dec() {
        return Entry_Dec;
    }

    public double getEntry_Credit() {
        return Entry_Credit;
    }

    public double getEntry_Debit() {
        return Entry_Debit;
    }

}
