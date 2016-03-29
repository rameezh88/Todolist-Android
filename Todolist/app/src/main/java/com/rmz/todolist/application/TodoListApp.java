package com.rmz.todolist.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by rameezh88 on 20/11/15.
 */
public class TodoListApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
