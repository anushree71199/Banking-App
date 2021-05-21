package com.books.bankingapp.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName="User")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String email;

    private long balance;

    public User(int id, String name, String email, long balance){
        this.id=id;
        this.name=name;
        this.email=email;
        this.balance=balance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
