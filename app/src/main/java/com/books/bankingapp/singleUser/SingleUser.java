package com.books.bankingapp.singleUser;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.books.bankingapp.R;
import com.books.bankingapp.allUsers.AllUsers;
import com.books.bankingapp.database.User;
import com.books.bankingapp.database.UserRoomDatabase;
import com.books.bankingapp.payment.SelectUser;
import com.books.bankingapp.viewmodel.UserViewModel;

public class SingleUser extends AppCompatActivity {
    private static final String TAG = "RUN7";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_user);
        TextView userName = findViewById(R.id.single_user_name);
        TextView userEmail = findViewById(R.id.single_user_email);
        TextView userBalance = findViewById(R.id.single_user_balance);
        Button sendMoney = findViewById(R.id.button_send_money);
        Intent intent = getIntent();
        int id = intent.getIntExtra("Id", 0);
        final User[] current = new User[1];
        UserViewModel mUserViewModel = new ViewModelProvider(this, getDefaultViewModelProviderFactory()).get(UserViewModel.class);
        UserRoomDatabase.databaseWriterExecutor.execute(() -> {
            User user = mUserViewModel.getUserById(id);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    current[0] = user;
                    userName.setText(current[0].getName());
                    userEmail.setText(current[0].getEmail());
                    userBalance.setText(String.valueOf(current[0].getBalance()));
                }
            });
        });

        sendMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleUser.this, SelectUser.class);
                intent.putExtra("fromId", id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SingleUser.this, AllUsers.class));
        finish();
    }
}