package com.rmz.todolist.allitems.interactor;

import android.content.Context;

import com.rmz.todolist.allitems.model.TodoList;
import com.rmz.todolist.util.db.TodoListDataService;

import java.util.ArrayList;

/**
 * Created by rameez on 29/03/16.
 */
public class AllItemsInteractor implements IAllItemsInteractor {
    private TodoListDataService manager;

    public AllItemsInteractor(Context context) {
        manager = TodoListDataService.getInstance(context);
    }

    @Override
    public void loadAllItems(AllItemsListener allItemsListener) {
        ArrayList<TodoList> allLists = manager.getAllTodoLists();
        if (allLists == null || allLists.isEmpty()) {
            allItemsListener.onNoItemsFound();
        } else {
            allItemsListener.onAllItemsLoaded(allLists);
        }
    }
}
