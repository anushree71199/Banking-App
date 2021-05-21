package com.books.bankingapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.books.bankingapp.database.Transaction;
import com.books.bankingapp.database.TransactionDao;
import com.books.bankingapp.database.User;
import com.books.bankingapp.database.UserDao;
import com.books.bankingapp.database.UserRoomDatabase;

import java.util.List;

public class UserRepository {

    private static final String TAG = "RUN2";
    private UserDao mUserDao;
    private TransactionDao mTransactionDao;
    private LiveData<List<User>> mAllUsers;
    private LiveData<List<Transaction>> mAllTransaction;

    public UserRepository(Application application) {
        UserRoomDatabase db = UserRoomDatabase.getDatabase(application);
        mUserDao = db.userDao();
        mTransactionDao=db.transactionDao();
        mAllUsers = mUserDao.getAllUsers();
        mAllTransaction=mTransactionDao.getAllTransaction();
    }

    public LiveData<List<User>> getAllUsers() {
        return mAllUsers;
    }

    public List<User> getList() {
        return mUserDao.getList();
    }

    public LiveData<List<Transaction>> getAllTransaction(){
        return mAllTransaction;
    }

    public User getUserById(int id) {
        return mUserDao.getUserById(id);
    }

    public void insert(final User user) {
        UserRoomDatabase.databaseWriterExecutor.execute(() -> mUserDao.insert(user));
    }

    public void insertTransaction(final Transaction transaction){
        UserRoomDatabase.databaseWriterExecutor.execute(()->mTransactionDao.insert(transaction));
    }

    public void deleteUser(final int id){
        UserRoomDatabase.databaseWriterExecutor.execute(()->mUserDao.delete(id));
    }

    public void deleteAllTransaction(){
        UserRoomDatabase.databaseWriterExecutor.execute(()-> mTransactionDao.deleteAll());
    }

    public void setBalance(long balance,int id){
        UserRoomDatabase.databaseWriterExecutor.execute(()->mUserDao.setBalance(balance, id));
    }

}
