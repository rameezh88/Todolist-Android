package com.rmz.todolist.allitems.presenter;

import com.rmz.todolist.allitems.model.TodoList;

/**
 * Created by rameez on 29/03/16.
 */
public interface IAllItemsPresenter {
    void onResume();
    void onNewListButtonPressed();
    void onTodoListSelected(TodoList list);
}
