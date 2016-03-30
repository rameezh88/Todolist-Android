package com.rmz.todolist.allitems.router;

import android.content.Context;
import android.content.Intent;

import com.rmz.todolist.allitems.model.TodoList;
import com.rmz.todolist.todolist.view.TodoListActivity;

/**
 * Created by rameez on 30/03/16.
 */
public class AllItemsRouter implements IAllItemsRouter {
    @Override
    public void openNewListActivity(Context context) {
        try {
            Intent newListIntent = new Intent(context, TodoListActivity.class);
            context.startActivity(newListIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void openActivityForSelectedList(Context context, TodoList list) {
        try {
            Intent listIntent = new Intent(context, TodoListActivity.class);
            listIntent.putExtra(TODO_LIST, list);
            context.startActivity(listIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
