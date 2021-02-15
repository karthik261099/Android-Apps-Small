package com.nimbleq.financemanager;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AccountsEntryDao {

    @Insert
    void insert(AccountsEntry accountsEntry);

    @Delete
    void delete(AccountsEntry accountsEntry);

    @Query("SELECT * FROM AccountsEntry ORDER BY Entry_Date ASC")
    LiveData<List<AccountsEntry>> getAllEntriesOfAccount();

    @Query("SELECT SUM(Entry_Debit) FROM AccountsEntry")
    LiveData<Double> totalDebitOfAccount();

    @Query("SELECT SUM(Entry_Credit) FROM AccountsEntry")
    LiveData<Double> totalCreditOfAccount();

    @Query("SELECT (SELECT SUM(Entry_Credit) FROM AccountsEntry)-(SELECT SUM(Entry_Debit) FROM AccountsEntry)")
    LiveData<Double> totalBalanceOfAccount();
}
