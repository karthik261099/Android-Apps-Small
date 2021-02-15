package com.kbrosapp.account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;

    Calendar calendar;
    int year;
    int month;
    int dayOfMonth;
    TextView dateTextView;
    String dateString;

    AccountsEntryViewModel accountsEntryViewModel;
    RecyclerView recyclerView;
    public AccountsEntryAdapter adapter;

    TextView accCreditTextView;
    TextView accDebitTextView;
    TextView accBalanceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab=findViewById(R.id.floatingActionButton);

        accCreditTextView = findViewById(R.id.accCreditTextView);
        accDebitTextView = findViewById(R.id.accDebitTextView);
        accBalanceTextView = findViewById(R.id.accBalanceTextView);

        adapter = new AccountsEntryAdapter();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        accountsEntryViewModel = ViewModelProviders.of(MainActivity.this).get(AccountsEntryViewModel.class);
        accountsEntryViewModel.getAllEntriesOfAccount().observe(MainActivity.this, new Observer<List<AccountsEntry>>() {
            @Override
            public void onChanged(List<AccountsEntry> accountsEntries) {
                //Update recyclerview
                adapter.setAccountsEntries(accountsEntries);
            }
        });

        accountsEntryViewModel.totalCreditOfAccount().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                double creditOfAccount;
                if (aDouble == null) {
                    creditOfAccount = 0;
                } else {
                    creditOfAccount = aDouble;
                }
                accCreditTextView.setText(String.valueOf(creditOfAccount));

            }
        });

        accountsEntryViewModel.totalDebitOfAccount().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                double debitOfAccount;
                if (aDouble == null) {
                    debitOfAccount = 0;
                } else {
                    debitOfAccount = aDouble;
                }
                accDebitTextView.setText(String.valueOf(debitOfAccount));
            }
        });


        accountsEntryViewModel.totalBalanceOfAccount().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                double balanceOfAccount;
                if (aDouble == null) {
                    balanceOfAccount = 0;
                } else {
                    balanceOfAccount = aDouble;
                }
                accBalanceTextView.setText(String.valueOf(balanceOfAccount));

            }
        });

        //CODE FOR ACTION BUTTON TO ADD AN ENTRY
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //OPEN DIALOG TO ADD ENTRY
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.dialog_add_entry);

                dateTextView = dialog.findViewById(R.id.dateTextView);
                final RadioButton creditRadioButton = dialog.findViewById(R.id.creditRadioButton);
                final RadioButton debitRadioButton = dialog.findViewById(R.id.debitRadioButton);
                final EditText amountEditText = dialog.findViewById(R.id.amountEditText);
                final EditText descEditText = dialog.findViewById(R.id.descEditText);
                Button cancelButton = dialog.findViewById(R.id.cancelButton);
                Button addButton = dialog.findViewById(R.id.addButton);

                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                dateString = dayOfMonth + "-" + (month + 1) + "-" + year;

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date date = new Date(1962, 5, 5);
                try {
                    date = simpleDateFormat.parse(dateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String dateStringDisplay = new SimpleDateFormat("dd MMM, yyyy").format(new Date(date.getTime()));
                dateTextView.setText(dateStringDisplay);

                dateTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int yeara, int montha, int dayOfMontha) {
                                year = yeara;
                                month = montha;
                                dayOfMonth = dayOfMontha;
                                dateString = dayOfMonth + "-" + (month + 1) + "-" + year;

                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                                Date date = new Date(1962, 5, 5);
                                try {
                                    date = simpleDateFormat.parse(dateString);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                String dateStringDisplay = new SimpleDateFormat("dd MMM, yyyy").format(new Date(date.getTime()));
                                dateTextView.setText(dateStringDisplay);

                            }
                        }, year, month, dayOfMonth);
                        datePickerDialog.show();
                    }
                });


                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //CHECK IF ALL VALUES ENTERED
                        String descString = descEditText.getText().toString().trim();
                        String amountString = amountEditText.getText().toString().trim();

                        if (creditRadioButton.isChecked() || debitRadioButton.isChecked()) {
                            //Anyone radio is selected, now check for entered values

                            if (!descString.equals("") && !amountString.equals("") && !amountString.equals(".")) {
                                double amountDouble = Double.parseDouble(amountString);
                                //All things are fine, enter data to DB
                                AccountsEntry accountsEntry;

                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                                Date date = new Date(1962, 5, 5);
                                try {
                                    date = simpleDateFormat.parse(dateString);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                if (creditRadioButton.isChecked()) {
                                    //ADD THE CREDIT TRANSACTION
                                    accountsEntry = new AccountsEntry(date.getTime(), descString, amountDouble, 0 );
                                } else {//(debitRadioButton.isChecked())
                                    //ADD THE DEBIT TRANSACTION
                                    accountsEntry = new AccountsEntry(date.getTime(), descString, 0, amountDouble );
                                }

                                accountsEntryViewModel.insert(accountsEntry);

                                Toast.makeText(MainActivity.this, "DONE", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();

                                //refreshListItemFromYearMonthDropdownSelected();//IMPLEMENTED BELOW
                            } else {
                                Toast.makeText(MainActivity.this, "Enter All Details", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(MainActivity.this, "Credit or Debit?", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


                dialog.show();
            }
        });

    }
}