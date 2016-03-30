package com.rmz.todolist.allitems.view;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.rmz.todolist.R;
import com.rmz.todolist.allitems.adapter.AllItemsAdapter;
import com.rmz.todolist.allitems.model.TodoList;
import com.rmz.todolist.allitems.presenter.AllItemsPresenter;
import com.rmz.todolist.allitems.presenter.IAllItemsPresenter;
import com.rmz.todolist.todolist.view.TodoListActivity;

import java.util.ArrayList;

/**
 * Created by rameezh88 on 20/11/15.
 */
public class AllItemsActivity extends AppCompatActivity implements IAllItemsView {

    private TextView emptyMessageView;
    private RecyclerView allItemsList;
    private IAllItemsPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_items);
        presenter = new AllItemsPresenter(this, this);
        registerReceiver((AllItemsPresenter)presenter, new IntentFilter(getString(R.string.database_being_opened)));
        registerReceiver((AllItemsPresenter)presenter, new IntentFilter(getString(R.string.database_opened)));
        setTitle(getResources().getString(R.string.all_items));
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver((AllItemsPresenter)presenter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initViews () {
        emptyMessageView = (TextView) findViewById(R.id.all_items_empty_message);
        allItemsList = (RecyclerView) findViewById(R.id.all_items_list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.all_items_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_new_list) {
            presenter.onNewListButtonPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showListEmpty() {
        emptyMessageView.setVisibility(View.VISIBLE);
        allItemsList.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showAllItems(ArrayList<TodoList> allLists) {
        allItemsList.setVisibility(View.VISIBLE);
        emptyMessageView.setVisibility(View.INVISIBLE);
        showAllItemsInList(allLists);
    }

    @UiThread
    private void showAllItemsInList(ArrayList<TodoList> allLists) {
        AllItemsAdapter adapter = new AllItemsAdapter(allLists);
        allItemsList.setLayoutManager(new LinearLayoutManager(this));
        allItemsList.setAdapter(adapter);
    }

    @Override
    public void openNewListActivity() {
        Intent newListIntent = new Intent(this, TodoListActivity.class);
        startActivity(newListIntent);
    }

    @Override
    public void showLoader() {
        // TODO: 30/03/16: Add loader
    }

    @Override
    public void hideLoader() {
        // TODO: 30/03/16: Add loader
    }
}
