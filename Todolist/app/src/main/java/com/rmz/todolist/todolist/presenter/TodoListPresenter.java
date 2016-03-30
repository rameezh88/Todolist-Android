package com.rmz.todolist.todolist.presenter;

import android.content.Context;

import com.rmz.todolist.listitem.model.TodoListItem;
import com.rmz.todolist.todolist.view.ITodoListView;

/**
 * Created by rameez on 29/03/16.
 */
public class TodoListPresenter implements ITodoListPresenter {

    private Context context;
    private ITodoListView view;

    public TodoListPresenter(Context context, ITodoListView view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void addNewItemSelected() {
        try {
            view.openNewListItemActivity();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void itemSelected(TodoListItem item) {
        try {
            view.openListItemActivityWithItem(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
