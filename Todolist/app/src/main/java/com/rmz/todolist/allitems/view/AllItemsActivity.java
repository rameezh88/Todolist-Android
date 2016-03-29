package com.rmz.todolist.allitems.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.rmz.todolist.R;
import com.rmz.todolist.allitems.model.TodoList;
import com.rmz.todolist.allitems.presenter.AllItemsPresenter;
import com.rmz.todolist.todolist.view.TodoListActivity;

import java.util.ArrayList;

/**
 * Created by rameezh88 on 20/11/15.
 */
public class AllItemsActivity extends AppCompatActivity implements IAllItemsView {

    private TextView emptyMessageView;
    private ListView allItemsList;
    private AllItemsPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_items);
        presenter = new AllItemsPresenter(this, this);
        setTitle(getResources().getString(R.string.all_items));
        initViews();
    }

    private void initViews () {
        emptyMessageView = (TextView) findViewById(R.id.all_items_empty_message);
        allItemsList = (ListView) findViewById(R.id.all_items_list);
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
    }

    @Override
    public void openNewListActivity() {
        Intent newListIntent = new Intent(this, TodoListActivity.class);
        startActivity(newListIntent);
    }
}
