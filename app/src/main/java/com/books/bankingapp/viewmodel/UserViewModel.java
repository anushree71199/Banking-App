package com.books.bankingapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.books.bankingapp.database.Transaction;
import com.books.bankingapp.database.User;
import com.books.bankingapp.repository.TaskRunner;
import com.books.bankingapp.repository.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository mUserRepository;
    private TaskRunner taskRunner;

    private LiveData<List<User>> mAllUsers;
    private LiveData<List<Transaction>> mAllTransaction;

    public UserViewModel(@NonNull Application application) {
        super(application);
        mUserRepository = new UserRepository(application);
        mAllUsers = mUserRepository.getAllUsers();
        mAllTransaction=mUserRepository.getAllTransaction();
        taskRunner = new TaskRunner();
    }


    public LiveData<List<User>> getAllUsers() {
        return mAllUsers;
    }

    public LiveData<List<Transaction>> getAllTransaction(){
        return mAllTransaction;
    }

    public List<User> getList() {
        return mUserRepository.getList();
    }

    public void insert(User user) {
        mUserRepository.insert(user);
    }

    public void deleteUser(User user){
        mUserRepository.deleteUser(user.getId());
    }

    public void deleteAllTransaction(){
        mUserRepository.deleteAllTransaction();
    }

    public void insertTransaction(Transaction transaction){
        mUserRepository.insertTransaction(transaction);
    }

    public User getUserById(int id) {
        return mUserRepository.getUserById(id);
    }

    public void setBalance(long balance,int id){
        mUserRepository.setBalance(balance, id);
    }
}
