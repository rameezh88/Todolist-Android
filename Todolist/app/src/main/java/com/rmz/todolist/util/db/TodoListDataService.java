package com.rmz.todolist.util.db;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import com.rmz.todolist.R;
import com.rmz.todolist.allitems.model.TodoList;
import com.rmz.todolist.application.TodoListApp;
import com.rmz.todolist.listitem.model.TodoListItem;
import com.rmz.todolist.util.DateHandler;

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
        ArrayList<TodoListItem> allLists = new ArrayList<>();
        Cursor cursor = dbUtility.getTodoListForListId(listId);
        try {
            while (cursor.moveToNext()) {
                String listItemId = cursor.getString(cursor.getColumnIndex(DBUtility.KEY_ID));
                String itemText = cursor.getString(cursor.getColumnIndex(DBUtility.KEY_ITEM_TEXT));
                String created = cursor.getString(cursor.getColumnIndex(DBUtility.KEY_CREATED));
                int checked = cursor.getInt(cursor.getColumnIndex(DBUtility.KEY_CHECKED));
                Date dateCreated = DateHandler.getDateFromString(created);
                TodoListItem list = new TodoListItem(itemText, listItemId, listId, dateCreated, checked == 1);
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
    public void updateList(TodoList list) {
        try {
            dbUtility.updateList(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertList(TodoList list) {
        try {
            dbUtility.insertList(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTodoListItem(TodoListItem todoListItem) {
        try {
            dbUtility.updateTodoListItem(todoListItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertTodoListItems(TodoListItem todoListItem) {
        try {
            dbUtility.insertTodoListItems(todoListItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTodoListItemWithId(String todoListItemId) {
        try {
            dbUtility.deleteTodoListItemWithId(todoListItemId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTodoListWithId(String todoListId) {
        try {
            dbUtility.deleteTodoListWithId(todoListId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void openingDatabaseFromAssets() {
        notifyDatabaseBeingOpened();
    }

    @Override
    public void databaseIsOpen() {
        notifyDatabaseOpened();
    }

    private void notifyDatabaseBeingOpened() {
        Context context = TodoListApp.getContext();
        sendBroadcastWithAction(context, context.getString(R.string.database_being_opened));
    }

    private void notifyDatabaseOpened() {
        Context context = TodoListApp.getContext();
        sendBroadcastWithAction(context, context.getString(R.string.database_opened));
    }

    private void sendBroadcastWithAction(Context context, String action) {
        Intent databaseBeingOpened = new Intent();
        databaseBeingOpened.setAction(action);
        context.sendBroadcast(databaseBeingOpened);
    }
}
