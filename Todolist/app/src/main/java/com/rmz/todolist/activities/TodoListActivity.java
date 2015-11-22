package com.rmz.todolist.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.rmz.todolist.R;

import java.util.List;

/**
 * Created by rameezh88 on 22/11/15.
 */
public class TodoListActivity extends AppCompatActivity {

    private ListView todoListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_list);
        setTitle(getResources().getString(R.string.new_list));

    }

    private void initViews() {
        todoListView = (ListView) findViewById(R.id.todo_list_list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.todo_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_new_item) {
            addNewItem();
        }
        return super.onOptionsItemSelected(item);
    }

    private void addNewItem() {

    }
}
