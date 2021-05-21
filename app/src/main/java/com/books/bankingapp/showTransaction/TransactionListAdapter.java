package com.books.bankingapp.showTransaction;

import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.books.bankingapp.database.Transaction;

public class TransactionListAdapter extends ListAdapter<Transaction,ViewHolder> {
    private static final String TAG = "RUN6";

    protected TransactionListAdapter(@NonNull DiffUtil.ItemCallback<Transaction> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction current=getItem(position);
        Log.d(TAG, "onBindViewHolder: "+current.getFromName()+" "+current.getToName());
        holder.bind(current.getFromName(),current.getToName(),current.getAmount(),current.getTime());
    }

    public static class TransactionDiffUtil extends DiffUtil.ItemCallback<Transaction>{

        @Override
        public boolean areItemsTheSame(@NonNull Transaction oldItem, @NonNull Transaction newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Transaction oldItem, @NonNull Transaction newItem) {
            return oldItem.getId()==newItem.getId();
        }
    }
}
