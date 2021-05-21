package com.books.bankingapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Transaction transaction);

    @Query("SELECT * FROM `Transaction` ORDER BY time DESC")
    LiveData<List<Transaction>> getAllTransaction();

    @Query("DELETE FROM `Transaction`")
    void deleteAll();

}
