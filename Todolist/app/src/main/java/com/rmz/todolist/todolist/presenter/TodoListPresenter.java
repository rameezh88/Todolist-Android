package com.rmz.todolist.todolist.presenter;

import android.content.Context;

import com.rmz.todolist.allitems.model.TodoList;
import com.rmz.todolist.listitem.model.TodoListItem;
import com.rmz.todolist.todolist.view.ITodoListView;

/**
 * Created by rameez on 29/03/16.
 */
public class TodoListPresenter implements ITodoListPresenter {

    private Context context;
    private ITodoListView view;
    private TodoList todoList;

    public TodoListPresenter(Context context, ITodoListView view, TodoList list) {
        this.context = context;
        this.view = view;
        this.todoList = list;
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

    @Override
    public void onStart() {
        try {
            view.setListTitle(todoList.getListName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
