package com.books.bankingapp.allUsers;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.books.bankingapp.database.User;

import java.util.HashMap;
import java.util.Map;

public class UserListAdapter extends ListAdapter<User, UserViewHolder> {
    private static final String TAG = "Hello";
    private static Map<Integer, Integer> positionId;
    protected UserListAdapter(@NonNull DiffUtil.ItemCallback<User> diffCallback) {
        super(diffCallback);
        positionId=new HashMap<>();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return UserViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User current=getItem(position);
        positionId.put(position,current.getId());
        holder.bind(current.getName(),current.getEmail(),current.getBalance());
    }

    public static Map<Integer, Integer> getPositionId() {
        return positionId;
    }

    public static class UserDiff extends DiffUtil.ItemCallback<User>{

        @Override
        public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.getId()==newItem.getId();
        }
    }
}
