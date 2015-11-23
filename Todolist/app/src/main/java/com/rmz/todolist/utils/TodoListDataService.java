package com.rmz.todolist.utils;

import android.content.Context;
import android.database.Cursor;

import com.rmz.todolist.db.DBUtility;
import com.rmz.todolist.models.TodoList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by rameezh88 on 22/11/15.
 */
public class TodoListDataService implements DBUtility.DatabaseOpenListener {
    private static TodoListDataService instance = null;
    private DBUtility dbUtility;
    protected TodoListDataService() {
        // Exists only to defeat instantiation.
    }
    public static TodoListDataService getInstance(Context context) {
        if(instance == null) {
            instance = new TodoListDataService();
            instance.init(context);
        }
        return instance;
    }

    private void init(Context context) {
        dbUtility = new DBUtility(context);
        dbUtility.setDatabaseOpenListener(this);
        try {
            dbUtility.openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<TodoList> getAllLists() {
        ArrayList<TodoList> allLists = new ArrayList<>();
        Cursor cursor = dbUtility.getAllTodoLists();
        while (cursor.moveToNext()) {
            String listId = cursor.getString(cursor.getColumnIndex(DBUtility.KEY_ID));
            String listName = cursor.getString(cursor.getColumnIndex(DBUtility.KEY_LIST_NAME));
            String lastModified = cursor.getString(cursor.getColumnIndex(DBUtility.KEY_MODIFIED));
            Date lastModifiedDate = DateHandler.getDateFromString(lastModified);
            TodoList list = new TodoList(listName, listId, lastModifiedDate);
            allLists.add(list);
        }
        cursor.close();
        return allLists;
    }

    public void updateList(TodoList list) {

    }

    public void deleteList(TodoList list) {

    }

    @Override
    public void openingDatabaseFromAssets() {

    }

    @Override
    public void databaseIsOpen() {

    }
}
