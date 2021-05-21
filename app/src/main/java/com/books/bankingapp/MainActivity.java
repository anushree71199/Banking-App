package com.books.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.books.bankingapp.allUsers.AllUsers;
import com.books.bankingapp.showTransaction.ShowTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button allUsersButton = findViewById(R.id.all_users);
        Button allTransaction=findViewById(R.id.show_transaction);
        allUsersButton.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, AllUsers.class);
            startActivity(intent);
        });
        allTransaction.setOnClickListener(v->{
            Intent intent=new Intent(MainActivity.this, ShowTransaction.class);
            startActivity(intent);
        });
    }

}