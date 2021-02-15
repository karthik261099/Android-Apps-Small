package com.kbrosapp.account;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AccountsEntryAdapter extends RecyclerView.Adapter<AccountsEntryAdapter.AccountsEntryHolder> {
    private List<AccountsEntry> accountsEntries = new ArrayList<>();
    ViewGroup parentForContext;
    public AccountsEntryViewModel accountsEntryViewModel;

    @NonNull
    @Override
    public AccountsEntryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.entries_card, parent, false);
        parentForContext = parent;
        accountsEntryViewModel = ViewModelProviders.of((FragmentActivity) parentForContext.getContext()).get(AccountsEntryViewModel.class);
        return new AccountsEntryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final AccountsEntryHolder holder, int position) {

        final AccountsEntry currentAccountsEntry = accountsEntries.get(position);
        String dateString = new SimpleDateFormat("dd MMM yy").format(new Date(currentAccountsEntry.getEntry_Date()));
        holder.textViewDate.setText(dateString);
        holder.textViewDescription.setText(currentAccountsEntry.getEntry_Dec());
        holder.textViewCredit.setText(String.valueOf(currentAccountsEntry.getEntry_Credit()));
        holder.textViewDebit.setText(String.valueOf(currentAccountsEntry.getEntry_Debit()));

        if (currentAccountsEntry.getEntry_Credit() == 0 && currentAccountsEntry.getEntry_Debit() == 0) {
            //SET BG BLUE
            holder.linearLayout.setBackgroundColor(Color.parseColor("#00BCD4"));
        } else if (currentAccountsEntry.getEntry_Debit() == 0) {
            //SET BG GREEN
            holder.linearLayout.setBackgroundColor(Color.parseColor("#00b200"));
        } else if (currentAccountsEntry.getEntry_Credit() == 0) {
            //SET BG RED
            holder.linearLayout.setBackgroundColor(Color.parseColor("#d00000"));
        }

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                Toast.makeText(parentForContext.getContext(), holder.textViewDescription.getText(), Toast.LENGTH_SHORT).show();

                final AlertDialog.Builder dialog=new AlertDialog.Builder(parentForContext.getContext());
                dialog.setMessage("Do you want to delete the entry?");
                dialog.setPositiveButton("YES",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        accountsEntryViewModel.delete(currentAccountsEntry);
                    }
                });
                dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //DO NOTHING
                    }
                });
                dialog.show();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return accountsEntries.size();
    }

    public void setAccountsEntries(List<AccountsEntry> accountsEntries) {
        this.accountsEntries = accountsEntries;
        notifyDataSetChanged();
    }

    class AccountsEntryHolder extends RecyclerView.ViewHolder {
        private TextView textViewDate;
        private TextView textViewDescription;
        private TextView textViewCredit;
        private TextView textViewDebit;
        private LinearLayout linearLayout;

        public AccountsEntryHolder(@NonNull View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewCredit = itemView.findViewById(R.id.textViewCredit);
            textViewDebit = itemView.findViewById(R.id.textViewDebit);
            linearLayout = itemView.findViewById(R.id.linearLayoutForEntryCard);
        }
    }

}
