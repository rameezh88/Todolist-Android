package com.rmz.todolist.util.db;

import com.rmz.todolist.allitems.model.TodoList;
import com.rmz.todolist.listitem.model.TodoListItem;

import java.util.ArrayList;

/**
 * Created by rameez on 29/03/16.
 */
public interface ITodoListDataService {
    ArrayList<TodoList> getAllTodoLists();
    ArrayList<TodoListItem> getTodoListForListId(String listId);
    void updateList(TodoList list);
    void insertList(TodoList list);
    void updateTodoListItem(TodoListItem todoListItem);
    void insertTodoListItems(TodoListItem todoListItem);
    void deleteTodoListItemWithId(String todoListItemId);
    void deleteTodoListWithId(String todoListId);
}
