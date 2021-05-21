package com.books.bankingapp.allUsers;

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
import com.books.bankingapp.singleUser.SingleUser;

public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private static final String TAG = "Hello";
    private Context context;
    private final TextView userName,userEmail,userBalance;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        context=itemView.getContext();
        userName=itemView.findViewById(R.id.user_name);
        userEmail=itemView.findViewById(R.id.user_email);
        userBalance=itemView.findViewById(R.id.user_balance);
    }

    public void bind(String name,String email,long balance){
        userName.setText(name);
        userEmail.setText(email);
        userBalance.setText(String.valueOf(balance));
    }

    public static UserViewHolder create(ViewGroup parent){
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item,parent,false);
        return new UserViewHolder(view);
    }


    @Override
    public void onClick(View v) {
        Intent intent=new Intent(context, SingleUser.class);
        int id=0;
        try {
            id = UserListAdapter.getPositionId().get(getAdapterPosition());
        }
        catch(Exception e){
            Log.d(TAG, "onClick: "+e.toString());
        }
        intent.putExtra("Id",id);
        context.startActivity(intent);
    }
}
