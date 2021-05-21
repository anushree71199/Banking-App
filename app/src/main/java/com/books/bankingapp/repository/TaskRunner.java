package com.books.bankingapp.repository;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskRunner {
private final ExecutorService executorService= Executors.newSingleThreadExecutor();
private final Handler handler=new Handler(Looper.getMainLooper());

public interface Callback<T>{
    void onComplete(T result);
}

public <T> void executeAsync(Callable<T> callable,Callback<T> callback){
    executorService.execute(()->{
        try {
            final T result=callable.call();
            handler.post(()->{
               callback.onComplete(result);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    });
}

}
