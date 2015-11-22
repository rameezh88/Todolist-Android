package com.rmz.todolist.services;

import android.content.Context;

import com.rmz.todolist.db.DBUtility;

import java.sql.SQLException;

/**
 * Created by rameezh88 on 22/11/15.
 */
public class TodoListManager implements DBUtility.DatabaseOpenListener {
    private static TodoListManager instance = null;
    private DBUtility dbUtility;
    protected TodoListManager() {
        // Exists only to defeat instantiation.
    }
    public static TodoListManager getInstance(Context context) {
        if(instance == null) {
            instance = new TodoListManager();
            instance.init(context);
        }
        return instance;
    }

    private void init (Context context) {
        dbUtility = new DBUtility(context);
        dbUtility.setDatabaseOpenListener(this);
        try {
            dbUtility.openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void openingDatabaseFromAssets() {

    }

    @Override
    public void databaseIsOpen() {

    }
}
