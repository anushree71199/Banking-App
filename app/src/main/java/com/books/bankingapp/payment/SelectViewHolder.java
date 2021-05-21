package com.books.bankingapp.payment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.books.bankingapp.R;

import java.util.HashMap;

public class SelectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private static final String TAG = "RUN3";
    private Context context;
    private final TextView selectName;

    public SelectViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        itemView.setOnClickListener(this);
        selectName = itemView.findViewById(R.id.user_select);
    }

    public void bind(String name) {
        selectName.setText(name);
    }

    public static SelectViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_select_user, parent, false);
        return new SelectViewHolder(view);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, Payment.class);
        int toId = 0,fromId=0;
        try {
            HashMap<Integer,Integer> map=SelectListAdapter.getPositionMap();
            toId = map.get(getAdapterPosition());
            fromId=SelectListAdapter.getFromId();
        } catch (Exception e) {
            Log.d(TAG, "onClick: "+e.toString());
        }
        intent.putExtra("fromId",fromId);
        intent.putExtra("toId",toId);
        context.startActivity(intent);
    }
}
