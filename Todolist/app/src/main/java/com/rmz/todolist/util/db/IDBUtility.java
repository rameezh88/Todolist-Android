package com.rmz.todolist.util.db;

import android.database.Cursor;

import com.rmz.todolist.allitems.model.TodoList;
import com.rmz.todolist.listitem.model.TodoListItem;

/**
 * Created by rameez on 29/03/16.
 */
public interface IDBUtility {
    interface DatabaseOpenListener {
        void openingDatabaseFromAssets();
        void databaseIsOpen();
    }
    void openDatabase();
    void setDatabaseOpenListener(DatabaseOpenListener databaseOpenListener);
    void closeDatabase();
    Cursor getAllTodoLists();
    Cursor getTodoListForListId(String listId);
    void updateList(TodoList list);
    void insertList(TodoList list);
    void updateTodoListItem(TodoListItem todoListItem);
    void insertTodoListItems(TodoListItem todoListItem);
    void deleteTodoListItemWithId(String todoListItemId);
    void deleteTodoListWithId(String todoListId);
}
