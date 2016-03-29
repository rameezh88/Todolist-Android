package com.rmz.todolist.allitems.presenter;

import android.content.Context;

import com.rmz.todolist.allitems.interactor.AllItemsInteractor;
import com.rmz.todolist.allitems.interactor.IAllItemsInteractor;
import com.rmz.todolist.allitems.model.TodoList;
import com.rmz.todolist.allitems.view.IAllItemsView;

import java.util.ArrayList;

/**
 * Created by rameez on 29/03/16.
 */
public class AllItemsPresenter implements IAllItemsPresenter, IAllItemsInteractor.AllItemsListener {
    private AllItemsInteractor interactor;
    private IAllItemsView allItemsView;


    public AllItemsPresenter(Context context, IAllItemsView allItemsView) {
        interactor = new AllItemsInteractor(context);
        this.allItemsView = allItemsView;
    }

    @Override
    public void onResume() {
        interactor.loadAllItems(this);
    }

    @Override
    public void onNewListButtonPressed() {
        allItemsView.openNewListActivity();
    }

    @Override
    public void onAllItemsLoaded(ArrayList<TodoList> allItems) {
        allItemsView.showAllItems(allItems);
    }

    @Override
    public void onNoItemsFound() {
        allItemsView.showListEmpty();
    }
}
