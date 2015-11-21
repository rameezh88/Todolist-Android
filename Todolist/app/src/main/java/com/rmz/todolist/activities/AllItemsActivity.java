package com.rmz.todolist.activities;

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
import com.rmz.todolist.models.TodoList;

import java.util.ArrayList;

/**
 * Created by rameezh88 on 20/11/15.
 */
public class AllItemsActivity extends AppCompatActivity {

    private TextView emptyMessageView;
    private ListView allItemsList;
    private ArrayList<TodoList> allLists = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_items);
        setTitle(getResources().getString(R.string.all_items));
        initViews();
    }

    private void initViews () {
        emptyMessageView = (TextView) findViewById(R.id.all_items_empty_message);
        allItemsList = (ListView) findViewById(R.id.all_items_list);
    }

    @Override
    protected void onStart() {
        super.onStart();
        boolean noItemsAdded = allLists.size() == 0;
        if (noItemsAdded) {
            emptyMessageView.setVisibility(View.VISIBLE);
            allItemsList.setVisibility(View.INVISIBLE);
        } else {
            allItemsList.setVisibility(View.VISIBLE);
            emptyMessageView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.all_items_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
