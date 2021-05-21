package com.books.bankingapp.payment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.books.bankingapp.R;
import com.books.bankingapp.database.TimeStampConverter;
import com.books.bankingapp.database.Transaction;
import com.books.bankingapp.database.User;
import com.books.bankingapp.database.UserDao;
import com.books.bankingapp.database.UserRoomDatabase;
import com.books.bankingapp.singleUser.SingleUser;
import com.books.bankingapp.viewmodel.UserViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.Date;

public class Payment extends AppCompatActivity {
    private static final String TAG = "RUN4";

    private TextView fromName, toName;
    private Button sendMoney;
    private EditText amount;
    private UserViewModel model;
    private static User fromUser, toUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Intent intent = getIntent();
        int fromId = intent.getIntExtra("fromId", 0);
        int toId = intent.getIntExtra("toId", 0);

        model = new ViewModelProvider(this, getDefaultViewModelProviderFactory()).get(UserViewModel.class);

        fromName = findViewById(R.id.from_user_name);
        toName = findViewById(R.id.to_user_name);
        sendMoney = findViewById(R.id.send_money);
        amount = findViewById(R.id.amount);

        fromUser = getUser(fromId, false);
        toUser = getUser(toId, true);

        sendMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    long balance = fromUser.getBalance();
                    long transferAmount = Long.parseLong(amount.getText().toString());

                    InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    manager.hideSoftInputFromWindow(v.getWindowToken(), 0);

                    if (transferAmount > balance) {
                        amount.setText("");
                        Snackbar.make(v, "Insufficient Balance", Snackbar.LENGTH_SHORT).show();
                    } else if (transferAmount < 0) {
                        amount.setText("");
                        Snackbar.make(v, "Transfer amount cannot be negative", Snackbar.LENGTH_SHORT).show();
                    } else {

                        model.setBalance(balance - transferAmount, fromId);
                        model.setBalance(toUser.getBalance() + transferAmount, toId);

                        Transaction transaction = new Transaction();
                        transaction.setFromId(fromId);
                        transaction.setToId(toId);
                        transaction.setFromName(fromUser.getName());
                        transaction.setToName(toUser.getName());
                        transaction.setAmount(transferAmount);
                        transaction.setId(0);
                        transaction.setTime(TimeStampConverter.fromCalendar(Calendar.getInstance()));

                        model.insertTransaction(transaction);
                        MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(Payment.this);
                        //dialog.setMessage("Transaction Successful");
                        dialog.setTitle("Transaction Successful");
                        dialog.setCancelable(false);
                        dialog.setIcon(R.drawable.ic_check);
                        dialog.setPositiveButton( "Ok", (DialogInterface.OnClickListener) (dialog1, which) -> {
                            Log.d(TAG, "onDialog "+which);
                            Intent intent1 = new Intent(Payment.this, SingleUser.class);
                            intent1.putExtra("Id", fromId);
                            startActivity(intent1);
                        });
                        dialog.show();
                    }
                } catch (Exception e) {
                    Log.d(TAG, "onClick: Error"+e.toString());
                }
            }
        });
    }

    public User getUser(int id, boolean to) {
        final User[] user = new User[1];
        UserRoomDatabase.databaseWriterExecutor.execute(() -> {
            User current = model.getUserById(id);
            runOnUiThread(() -> {
                user[0] = current;
                if (to) {
                    toUser = user[0];
                    toName.setText(toUser.getName());
                } else {
                    fromUser = user[0];
                    fromName.setText(fromUser.getName());
                }
            });
        });

        if (user[0] == null) {
            return new User(1, "", "", 0);
        }
        return user[0];

    }

}