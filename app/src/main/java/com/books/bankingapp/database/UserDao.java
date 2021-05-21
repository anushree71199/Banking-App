package com.books.bankingapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User user);

    @Query("DELETE FROM User WHERE id=:id")
    void delete(int id);

    @Query("DELETE FROM User")
    void deleteAll();

    @Query("SELECT * FROM User")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM User")
    List<User> getList();

    @Query("SELECT * FROM User WHERE name= :name AND email=:email")
    User getUser(String name,String email);

    @Query("SELECT * FROM USER WHERE id=:id")
    User getUserById(int id);

    @Query("UPDATE User SET balance= :balance WHERE name=:name AND email=:email")
    void setBalance(long balance,String name,String email);

    @Query("UPDATE User SET balance=:balance WHERE id=:id")
    void setBalance(long balance,int id);
}
