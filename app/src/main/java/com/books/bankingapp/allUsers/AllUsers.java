package com.books.bankingapp.allUsers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.books.bankingapp.MainActivity;
import com.books.bankingapp.R;
import com.books.bankingapp.database.User;
import com.books.bankingapp.viewmodel.UserViewModel;

import java.util.List;

public class AllUsers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final UserListAdapter adapter = new UserListAdapter(new UserListAdapter.UserDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        UserViewModel mUserViewModel = new ViewModelProvider(this, getDefaultViewModelProviderFactory()).get(UserViewModel.class);

        mUserViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                adapter.submitList(users);
            }
        });
          mUserViewModel.insert(new User(1,"Bhawana","bhawana@gmail.com",20000));
          mUserViewModel.insert(new User(2,"Ranu","ranu@gmail.com",5000));
          mUserViewModel.insert(new User(3,"Manisha","manisha@gmail.com",30000));
          mUserViewModel.insert(new User(4,"Abhilasha","abhilasha@gmail.com",10000));
          mUserViewModel.insert(new User(5,"Aayushi","aayushi@gmail.com",5820));
          mUserViewModel.insert(new User(6,"Sakshi","sakshi@gmail.com",2110));
          mUserViewModel.insert(new User(7,"Sneha","sneha@gmail.com",24200));
          mUserViewModel.insert(new User(8,"Kritika","kritika@gmail.com",3200));
          mUserViewModel.insert(new User(9,"Aanamika","aanamika@gmail.com",52000));
          mUserViewModel.insert(new User(10,"Shivani","shivani@gmail.com",8522));
          mUserViewModel.insert(new User(0,"Swati","swati@gmail.com",9000));
//          mUserViewModel.insert(new User(0,"Vignesh","vignesh@gmail.com",7000));

//        mUserViewModel.deleteUser(new User(12,"Vignesh","vignesh@gmail.com",7000));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AllUsers.this, MainActivity.class));
        finish();
    }
}