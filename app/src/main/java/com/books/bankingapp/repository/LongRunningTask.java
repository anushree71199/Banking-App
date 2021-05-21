package com.books.bankingapp.repository;

import com.books.bankingapp.database.User;
import com.books.bankingapp.database.UserDao;
import com.books.bankingapp.database.UserRoomDatabase;

import java.util.concurrent.Callable;

public class LongRunningTask implements Callable<User> {

    private final Integer id;
    private final UserDao userDao;
    private User user=null;

    public LongRunningTask(int id,UserDao userDao){
        this.id=id;
        this.userDao=userDao;
    }

    @Override
    public User call() throws Exception {
        UserRoomDatabase.databaseWriterExecutor.execute(() -> {
            user=userDao.getUserById(id);
        });

        return user;
    }
}
