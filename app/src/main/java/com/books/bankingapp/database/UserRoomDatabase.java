package com.books.bankingapp.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class,Transaction.class},version=1,exportSchema = false)
public abstract class UserRoomDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract TransactionDao transactionDao();
    private static volatile UserRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS=4;
    public static final ExecutorService databaseWriterExecutor=
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static UserRoomDatabase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (UserRoomDatabase.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                            UserRoomDatabase.class,"user_database")
                           // .addCallback(sRoomDatabase)
                            .build();
                }
            }
        }

        return INSTANCE;
    }


//    Adding user to database
//    private static RoomDatabase.Callback sRoomDatabase=new RoomDatabase.Callback(){
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//
//            databaseWriterExecutor.execute(new Runnable() {
//                @Override
//                public void run() {
//                    UserDao userDao=INSTANCE.userDao();
//                    userDao.deleteAll();
//                    User user1=new User(1,"Hari","hari@gmail.com",5000);
//                    userDao.insert(user1);
//
//                    User user2=new User(2,"Ram","ram@gmail.com",6000);
//                    userDao.insert(user2);
//                }
//            });
//        }
//    };
}
