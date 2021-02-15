package com.nimbleq.financemanager;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {AccountsEntry.class},version=1)
public abstract class AccountsDatabase extends RoomDatabase {

    private static AccountsDatabase instance;

    public abstract AccountsEntryDao accountsEntryDao();

    public static synchronized AccountsDatabase getInstance(Context context){

        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    AccountsDatabase.class,"AccountsDatabase")
                    .build();
        }
        return instance;
    }

}
