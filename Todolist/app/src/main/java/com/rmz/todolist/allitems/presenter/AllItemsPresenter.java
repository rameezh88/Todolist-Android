package com.rmz.todolist.allitems.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.rmz.todolist.R;
import com.rmz.todolist.allitems.interactor.AllItemsInteractor;
import com.rmz.todolist.allitems.interactor.IAllItemsInteractor;
import com.rmz.todolist.allitems.model.TodoList;
import com.rmz.todolist.allitems.router.AllItemsRouter;
import com.rmz.todolist.allitems.router.IAllItemsRouter;
import com.rmz.todolist.allitems.view.IAllItemsView;

import java.util.ArrayList;

/**
 * Created by rameez on 29/03/16.
 */
public class AllItemsPresenter extends BroadcastReceiver implements IAllItemsPresenter, IAllItemsInteractor.AllItemsListener {
    private IAllItemsInteractor interactor;
    private IAllItemsView allItemsView;
    private IAllItemsRouter router;
    private Context context;

    public AllItemsPresenter(Context context, IAllItemsView allItemsView) {
        this.context = context;
        interactor = new AllItemsInteractor(context, this);
        router = new AllItemsRouter();
        this.allItemsView = allItemsView;
    }

    @Override
    public void onResume() {
        interactor.loadAllItems();
    }

    @Override
    public void onNewListButtonPressed() {
        router.openNewListActivity(context);
    }

    @Override
    public void onTodoListSelected(TodoList list) {
        router.openActivityForSelectedList(context, list);
    }

    @Override
    public void onAllItemsLoaded(ArrayList<TodoList> allItems) {
        Log.d(getClass().getSimpleName(), "All items loaded - "+allItems.toString());
        allItemsView.showAllItems(allItems);
    }

    @Override
    public void onNoItemsFound() {
        Log.d(getClass().getSimpleName(), "No lists found");
        allItemsView.showListEmpty();
    }

    @Override
    public void onTryingDatabaseOpen() {
        allItemsView.showLoader();
    }

    @Override
    public void onDatabaseOpened() {
        allItemsView.hideLoader();
        interactor.loadAllItems();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(getClass().getSimpleName(), intent.getAction());
        if (intent.getAction().equals(context.getString(R.string.database_being_opened))) {
            onTryingDatabaseOpen();
        } else if (intent.getAction().equals(context.getString(R.string.database_opened))) {
            onDatabaseOpened();
        }
    }
}
