package com.books.bankingapp.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Transaction")
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "fromId")
    private int fromId;

    @ColumnInfo(name = "toId")
    private int toId;

    @ColumnInfo(name="fromName")
    private String fromName;

    @ColumnInfo(name="toName")
    private String toName;

    @ColumnInfo(name="amount")
    private long amount;

    @ColumnInfo(name="time")
    private String time;

    public Transaction(int id, int fromId, int toId, String fromName, String toName, long amount, String time) {
        this.id = id;
        this.fromId = fromId;
        this.toId = toId;
        this.fromName = fromName;
        this.toName = toName;
        this.amount = amount;
        this.time = time;
    }

    public Transaction() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getFromId() {
        return fromId;
    }

    public int getToId() {
        return toId;
    }

    public long getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }

    public String getFromName() {
        return fromName;
    }

    public String getToName() {
        return toName;
    }

    public String getTime() {
        return time;
    }
}
