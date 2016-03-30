package com.rmz.todolist.allitems.view;

import com.rmz.todolist.allitems.model.TodoList;

import java.util.ArrayList;

/**
 * Created by rameez on 29/03/16.
 */
public interface IAllItemsView {
    void showListEmpty();
    void showAllItems(ArrayList<TodoList> allLists);
    void showLoader();
    void hideLoader();
}
