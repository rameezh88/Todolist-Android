package com.rmz.todolist.allitems.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rameezh88 on 20/11/15.
 */
public class TodoList implements Parcelable {
    private String listName, listId;
    private String lastModified;

    public TodoList(String listName, String listId, String lastModified) {
        this.listName = listName;
        this.listId = listId;
        this.lastModified = lastModified;
    }

    protected TodoList(Parcel in) {
        listName = in.readString();
        listId = in.readString();
        lastModified = in.readString();
    }

    public static final Creator<TodoList> CREATOR = new Creator<TodoList>() {
        @Override
        public TodoList createFromParcel(Parcel in) {
            return new TodoList(in);
        }

        @Override
        public TodoList[] newArray(int size) {
            return new TodoList[size];
        }
    };

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

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(listName);
        dest.writeString(listId);
        dest.writeString(lastModified);
    }
}
