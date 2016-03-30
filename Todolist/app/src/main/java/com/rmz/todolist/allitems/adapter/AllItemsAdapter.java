package com.rmz.todolist.allitems.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rmz.todolist.R;
import com.rmz.todolist.allitems.model.TodoList;
import com.rmz.todolist.util.DateHandler;

import java.util.ArrayList;

/**
 * Created by rameez on 29/03/16.
 */
public class AllItemsAdapter extends RecyclerView.Adapter<AllItemsAdapter.AllItemsViewHolder>{

    private ArrayList<TodoList> allLists;

    public AllItemsAdapter(ArrayList<TodoList> allLists) {
        this.allLists = allLists;
    }

    @Override
    public AllItemsViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.all_items_list_item, viewGroup, false);
        return new AllItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AllItemsViewHolder holder, int position) {
        TodoList todoList = allLists.get(position);
        try {
            holder.listName.setText(todoList.getListName());
            holder.lastModified.setText(DateHandler.getFormattedDateForList(DateHandler.getDateFromString(todoList.getLastModified())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (allLists != null) {
            return allLists.size();
        }
        return 0;
    }

    public static class AllItemsViewHolder extends RecyclerView.ViewHolder {
        TextView listName;
        TextView lastModified;

        public AllItemsViewHolder(View itemView) {
            super(itemView);
            listName = (TextView) itemView.findViewById(R.id.list_name);
            lastModified = (TextView) itemView.findViewById(R.id.last_modified);
        }
    }
}
