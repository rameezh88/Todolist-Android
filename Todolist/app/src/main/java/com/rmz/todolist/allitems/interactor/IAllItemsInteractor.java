package com.rmz.todolist.allitems.interactor;

import com.rmz.todolist.allitems.model.TodoList;

import java.util.ArrayList;

/**
 * Created by rameez on 29/03/16.
 */
public interface IAllItemsInteractor {
    interface AllItemsListener {
        void onAllItemsLoaded(ArrayList<TodoList> allItems);
        void onNoItemsFound();
        void onTryingDatabaseOpen();
        void onDatabaseOpened();
    }
    void loadAllItems();
}
