package com.rmz.todolist.todolist.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.rmz.todolist.R;
import com.rmz.todolist.allitems.model.TodoList;
import com.rmz.todolist.allitems.router.IAllItemsRouter;
import com.rmz.todolist.listitem.model.TodoListItem;
import com.rmz.todolist.todolist.presenter.ITodoListPresenter;
import com.rmz.todolist.todolist.presenter.TodoListPresenter;

import java.util.ArrayList;

/**
 * Created by rameezh88 on 22/11/15.
 */
public class TodoListActivity extends AppCompatActivity implements ITodoListView {

    private RecyclerView todoList;
    private EditText listTitle;
    private TextView listEmpty;
    private ITodoListPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_list);
        setTitle(getResources().getString(R.string.new_list));
        TodoList todoList = getIntent().getParcelableExtra(IAllItemsRouter.TODO_LIST);
        presenter = new TodoListPresenter(this, this, todoList);
        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    private void initViews() {
        todoList = (RecyclerView) findViewById(R.id.todo_list_list);
        listEmpty = (TextView) findViewById(R.id.todo_list_empty_message);
        listTitle = (EditText) findViewById(R.id.todo_list_title);
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
            presenter.addNewItemSelected();
        }
        return super.onOptionsItemSelected(item);
    }

    private void openListItemActivity(TodoListItem todoListItem) {
        // TODO: 30/03/16 : Create activity and set it up to create a new list item.
    }

    @Override
    public void showAllItems(ArrayList<TodoListItem> allListItems) {
        listEmpty.setVisibility(View.GONE);
        todoList.setVisibility(View.VISIBLE);
    }

    @Override
    public void showListEmpty() {
        listEmpty.setVisibility(View.VISIBLE);
        todoList.setVisibility(View.GONE);
    }

    @Override
    public void openNewListItemActivity() {
        openListItemActivity(null);
    }

    @Override
    public void openListItemActivityWithItem(TodoListItem listItem) {
        openListItemActivity(listItem);
    }

    @Override
    public void setListTitle(String title) {
        try {
            listTitle.setText(title);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
