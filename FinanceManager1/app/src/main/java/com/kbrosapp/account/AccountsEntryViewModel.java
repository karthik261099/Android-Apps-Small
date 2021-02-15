package com.kbrosapp.account;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AccountsEntryViewModel extends AndroidViewModel {
    private AccountsEntryRepository repository;
    private LiveData<List<AccountsEntry>> allEntriesOfAccount;


    public AccountsEntryViewModel(@NonNull Application application) {
        super(application);
        repository=new AccountsEntryRepository(application);
    }

    public void insert(AccountsEntry accountsEntry){
        repository.insert(accountsEntry);
    }

    public void delete(AccountsEntry accountsEntry){
        repository.delete(accountsEntry);
    }

    public LiveData<List<AccountsEntry>> getAllEntriesOfAccount(){
        allEntriesOfAccount=repository.getAllEntriesOfAccount();
        return allEntriesOfAccount;
    }

    public LiveData<Double> totalDebitOfAccount(){
        LiveData<Double> totalDebitOfAccount;
        totalDebitOfAccount=repository.totalDebitOfAccount();
        return totalDebitOfAccount;
    }

    public LiveData<Double> totalCreditOfAccount(){
        LiveData<Double> totalCreditOfAccount;
        totalCreditOfAccount=repository.totalCreditOfAccount();
        return totalCreditOfAccount;
    }

    public LiveData<Double> totalBalanceOfAccount(){
        LiveData<Double> totalBalanceOfAccount;
        totalBalanceOfAccount=repository.totalBalanceOfAccount();
        return totalBalanceOfAccount;
    }


}
