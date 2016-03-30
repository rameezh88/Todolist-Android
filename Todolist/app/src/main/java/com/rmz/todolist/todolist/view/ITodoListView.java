package com.rmz.todolist.todolist.view;

import com.rmz.todolist.listitem.model.TodoListItem;

import java.util.ArrayList;

/**
 * Created by rameez on 30/03/16.
 */
public interface ITodoListView {
    void showAllItems(ArrayList<TodoListItem> allListItems);
    void showListEmpty();
    void openNewListItemActivity();
    void openListItemActivityWithItem(TodoListItem listItem);
}
