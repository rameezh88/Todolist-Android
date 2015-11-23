package com.rmz.todolist.models;

import java.util.Date;

/**
 * Created by rameezh88 on 20/11/15.
 */
public class TodoList {
    private String listName, listId;
    private Date lastModified;

    public TodoList(String listName, String listId, Date lastModified) {
        this.listName = listName;
        this.listId = listId;
        this.lastModified = lastModified;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        return "TodoList{" +
                "listName='" + listName + '\'' +
                ", listId='" + listId + '\'' +
                ", lastModified=" + lastModified +
                '}';
    }
}
