package com.rmz.todolist.util.db;

import android.content.Context;
import android.database.Cursor;

import com.rmz.todolist.allitems.model.TodoList;
import com.rmz.todolist.listitem.model.TodoListItem;
import com.rmz.todolist.util.DateHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by rameezh88 on 22/11/15.
 */
public class TodoListDataService implements DBUtility.DatabaseOpenListener, ITodoListDataService {
    private static TodoListDataService instance = null;
    private IDBUtility dbUtility;
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
        dbUtility.openDatabase();
    }

    @Override
    public ArrayList<TodoList> getAllTodoLists() {
        ArrayList<TodoList> allLists = new ArrayList<>();
        Cursor cursor = dbUtility.getAllTodoLists();
        try {
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
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<TodoListItem> getTodoListForListId(String listId) {
        return null;
    }

    @Override
    public void updateList(TodoList list) {

    }

    @Override
    public void insertList(TodoList list) {

    }

    @Override
    public void updateTodoListItem(TodoListItem todoListItem) {

    }

    @Override
    public void insertTodoListItems(TodoListItem todoListItem) {

    }

    @Override
    public void deleteTodoListItemWithId(String todoListItemId) {

    }

    @Override
    public void deleteTodoListWithId(String todoListId) {

    }

    @Override
    public void openingDatabaseFromAssets() {

    }

    @Override
    public void databaseIsOpen() {
    }
}
