package com.books.bankingapp.payment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.books.bankingapp.R;
import com.books.bankingapp.database.User;
import com.books.bankingapp.viewmodel.UserViewModel;

import java.util.List;

public class SelectUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);
        Intent intent=getIntent();
        int fromId=intent.getIntExtra("fromId",0);

        RecyclerView recyclerView=findViewById(R.id.recycler_select);
        final SelectListAdapter adapter=new SelectListAdapter(new SelectListAdapter.SelectDiff(),fromId);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        UserViewModel model=new ViewModelProvider(this,getDefaultViewModelProviderFactory())
                .get(UserViewModel.class);

        LiveData<List<User>> allUsers=model.getAllUsers();

        allUsers.observe(this, adapter::submitList);
    }
}