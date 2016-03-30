package com.rmz.todolist.allitems.router;

import android.content.Context;

import com.rmz.todolist.allitems.model.TodoList;

/**
 * Created by rameez on 30/03/16.
 */
public interface IAllItemsRouter {
    String TODO_LIST = "todo_list";
    void openNewListActivity(Context context);
    void openActivityForSelectedList(Context context, TodoList list);
}
