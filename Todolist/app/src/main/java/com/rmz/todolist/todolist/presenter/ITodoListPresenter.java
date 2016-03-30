package com.rmz.todolist.todolist.presenter;

import com.rmz.todolist.listitem.model.TodoListItem;

/**
 * Created by rameez on 30/03/16.
 */
public interface ITodoListPresenter {
    void addNewItemSelected();
    void itemSelected(TodoListItem item);
    void onStart();
}
