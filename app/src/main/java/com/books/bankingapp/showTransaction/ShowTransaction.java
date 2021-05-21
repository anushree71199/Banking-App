package com.books.bankingapp.showTransaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.books.bankingapp.R;
import com.books.bankingapp.database.Transaction;
import com.books.bankingapp.viewmodel.UserViewModel;

import java.util.List;

public class ShowTransaction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_transaction);

        RecyclerView recyclerView=findViewById(R.id.recycler_show_transaction);
        final TransactionListAdapter adapter=new TransactionListAdapter(new TransactionListAdapter.TransactionDiffUtil());

        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        UserViewModel model=new ViewModelProvider(this,getDefaultViewModelProviderFactory()).get(UserViewModel.class);

        model.getAllTransaction().observe(this, adapter::submitList);

       // model.deleteAllTransaction();

    }
}