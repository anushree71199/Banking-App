package com.books.bankingapp.payment;

import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.books.bankingapp.database.User;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class SelectListAdapter extends ListAdapter<User, SelectViewHolder> {

    private static final String TAG = "RUN2";
    private static HashMap<Integer, Integer> positionId;
    private static int fromId;

    protected SelectListAdapter(@NonNull DiffUtil.ItemCallback<User> diffCallback, int fromId) {
        super(diffCallback);
        positionId = new HashMap<>();
        SelectListAdapter.fromId = fromId;
    }

    @NonNull
    @Override
    public SelectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return SelectViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectViewHolder holder, int position) {
        User current = getItem(position);
        positionId.put(position, current.getId());
        holder.bind(current.getName());
    }

    public static HashMap<Integer, Integer> getPositionMap() {
        return positionId;
    }

    public static int getFromId() {
        return fromId;
    }

    @Override
    public void submitList(@Nullable List<User> list) {

        try {
            assert list != null;
            for (User user : list) {
                if(user.getId()==fromId){
                    list.remove(user);
                }
            }
        } catch (Exception e) {
            Log.d(TAG, "submitList: " + e.toString());
        }
        super.submitList(list);
    }

    public static class SelectDiff extends DiffUtil.ItemCallback<User> {

        @Override
        public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.getId() == newItem.getId();
        }
    }
}
