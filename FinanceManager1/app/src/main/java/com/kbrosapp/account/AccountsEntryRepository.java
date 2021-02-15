package com.kbrosapp.account;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AccountsEntryRepository {

    private AccountsEntryDao accountsEntryDao;
    private LiveData<List<AccountsEntry>> allEntriesOfAccount;

    public AccountsEntryRepository(Application application){
        AccountsDatabase database=AccountsDatabase.getInstance(application);
        accountsEntryDao=database.accountsEntryDao();
    }

    public void insert(AccountsEntry accountsEntry){
        new InsertEntryAsyncTask(accountsEntryDao).execute(accountsEntry);
    }

    public void delete(AccountsEntry accountsEntry){
        new DeleteEntryAsyncTask(accountsEntryDao).execute(accountsEntry);
    }

    public LiveData<List<AccountsEntry>> getAllEntriesOfAccount(){
        allEntriesOfAccount=accountsEntryDao.getAllEntriesOfAccount();
        return allEntriesOfAccount;
    }

    public LiveData<Double> totalDebitOfAccount(){
        LiveData<Double> totalDebitOfAccount;
        totalDebitOfAccount=accountsEntryDao.totalDebitOfAccount();
        return totalDebitOfAccount;
    }

    public LiveData<Double> totalCreditOfAccount(){
        LiveData<Double> totalCreditOfAccount;
        totalCreditOfAccount=accountsEntryDao.totalCreditOfAccount();
        return totalCreditOfAccount;
    }

    public LiveData<Double> totalBalanceOfAccount(){
        LiveData<Double> totalBalanceOfAccount;
        totalBalanceOfAccount=accountsEntryDao.totalBalanceOfAccount();
        return totalBalanceOfAccount;
    }

    private static class InsertEntryAsyncTask extends AsyncTask<AccountsEntry, Void, Void> {
        private AccountsEntryDao accountsEntryDao;

        private InsertEntryAsyncTask(AccountsEntryDao accountsEntryDao){
            this.accountsEntryDao=accountsEntryDao;
        }

        @Override
        protected Void doInBackground(AccountsEntry... accountsEntries) {
            accountsEntryDao.insert(accountsEntries[0]);
            return null;
        }
    }


    private static class DeleteEntryAsyncTask extends AsyncTask<AccountsEntry,Void,Void>{
        private AccountsEntryDao accountsEntryDao;

        private DeleteEntryAsyncTask(AccountsEntryDao accountsEntryDao){
            this.accountsEntryDao=accountsEntryDao;
        }

        @Override
        protected Void doInBackground(AccountsEntry... accountsEntries) {
            accountsEntryDao.delete(accountsEntries[0]);
            return null;
        }
    }

}
