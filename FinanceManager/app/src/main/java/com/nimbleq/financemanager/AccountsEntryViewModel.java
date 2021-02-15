package com.nimbleq.financemanager;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AccountsEntryViewModel extends AndroidViewModel {

    private AccountsEntryRepository accountsEntryRepository;
    private LiveData<List<AccountsEntry>> allEntriesOfAccount;

    public AccountsEntryViewModel(Application application){
        super(application);
        accountsEntryRepository=new AccountsEntryRepository(application);
    }

    public void insert(AccountsEntry accountsEntry){
        accountsEntryRepository.insert(accountsEntry);
    }

    public void delete(AccountsEntry accountsEntry){
        accountsEntryRepository.delete(accountsEntry);
    }

    public LiveData<List<AccountsEntry>> getAllEntriesOfAccount(){
        allEntriesOfAccount=accountsEntryRepository.getAllEntriesOfAccount();
        return allEntriesOfAccount;
    }

    public LiveData<Double> totalDebitOfAccount(){
        LiveData<Double> totalDebitOfAccount;
        totalDebitOfAccount=accountsEntryRepository.totalDebitOfAccount();
        return totalDebitOfAccount;
    }

    public LiveData<Double> totalCreditOfAccount(){
        LiveData<Double> totalCreditOfAccount;
        totalCreditOfAccount=accountsEntryRepository.totalCreditOfAccount();
        return totalCreditOfAccount;
    }

    public LiveData<Double> totalBalanceOfAccount(){
        LiveData<Double> totalBalanceOfAccount;
        totalBalanceOfAccount=accountsEntryRepository.totalBalanceOfAccount();
        return totalBalanceOfAccount;
    }

}
