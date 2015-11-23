package com.rmz.todolist.models;

import java.util.Date;

/**
 * Created by rameezh88 on 20/11/15.
 */
public class TodoListItem {
    private String itemText, itemId, listId;
    private Date created;
    private boolean checked;

    public TodoListItem () {

    }

    public TodoListItem(String itemText, String itemId, String listId, Date created, boolean checked) {
        this.itemText = itemText;
        this.itemId = itemId;
        this.listId = listId;
        this.created = created;
        this.checked = checked;
    }

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "TodoListItem{" +
                "checked=" + checked +
                ", created=" + created +
                ", listId='" + listId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", itemText='" + itemText + '\'' +
                '}';
    }
}
