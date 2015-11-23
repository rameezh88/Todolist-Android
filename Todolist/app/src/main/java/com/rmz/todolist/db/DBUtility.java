package com.rmz.todolist.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.os.AsyncTask;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.File;
import java.sql.SQLException;

/**
 * Created by rameezh88 on 20/11/15.
 */
public class DBUtility {
    public static String BUNDLED_DB_NAME = "todolist.sqlite";
    private static String TODO_LIST_TABLE = "todo_lists";
    private static String LIST_ITEMS_TABLE = "list_items";

    public static String KEY_ID = "id";
    public static String KEY_LIST_NAME = "list_name";
    public static String KEY_MODIFIED = "modified";
    public static String KEY_TODO_LIST_ID = "todo_list_id";
    public static String KEY_TEXT = "text";
    public static String KEY_CHECKED = "checked";
    public static String KEY_CREATED = "created";
//    private static String[] TODO_LIST_TABLE_COLUMNS = {KEY_ID,KEY_LIST_NAME,KEY_MODIFIED};
//    private static String[] LIST_ITEMS_TABLE_COLUMNS = {KEY_ID,KEY_TODO_LIST_ID,KEY_TEXT,KEY_CHECKED,KEY_CREATED};

    static int DB_VERSION = 1;
    private SQLiteDatabase myDataBase;
    private Context myContext = null;
    private DatabaseOpenListener databaseOpenListener;

    public interface DatabaseOpenListener {
        public void openingDatabaseFromAssets();
        public void databaseIsOpen();
    }

    public DatabaseOpenListener getDatabaseOpenListener() {
        return databaseOpenListener;
    }

    public void setDatabaseOpenListener(DatabaseOpenListener databaseOpenListener) {
        this.databaseOpenListener = databaseOpenListener;
    }

    private class DbHelper extends SQLiteAssetHelper {
        DbHelper(Context context, String dbName) {
            super(context, dbName, null, DB_VERSION);
        }
    }

    private DbHelper mDbHelper;

    public DBUtility(Context ctx) {
        init(ctx, null, BUNDLED_DB_NAME);
    }

    public DBUtility(Context ctx, String dbPath, String dbName) {
        init(ctx, dbPath, dbName);
    }

    private void init (Context ctx, String dbPath, String dbName) {
        this.myContext = ctx;
        mDbHelper = new DbHelper(myContext, dbName);
    }

    public boolean checkDataBase(String dbPath) {
        File db = new File(dbPath);
        if(db.exists()){
            return true;
        }
        return false;
    }

    public void openDataBase() throws SQLException{
//    	Log.i("DB_COMPLETE_PATH", DB_COMPLETE_PATH);
        if(myDataBase != null) {
            myDataBase.close();
        }

        try {
            openDatabaseFromAssets();
        } catch (SQLiteDatabaseLockedException e) {
            // TODO: handle exception
        } catch (SQLiteCantOpenDatabaseException e) {
            e.printStackTrace();
        }

    }

    private void openDatabaseFromAssets () {
        AsyncTask<Void, Void, Void> openDatabase = new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                try {
                    getDatabaseOpenListener().openingDatabaseFromAssets();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected Void doInBackground(Void... params) {
                myDataBase = mDbHelper.getWritableDatabase();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                try {
                    getDatabaseOpenListener().databaseIsOpen();
                } catch (Exception e) {
//                    e.printStackTrace();
                }
            }
        };

        openDatabase.execute();
    }

    public synchronized void closeDataBase() {
        if(myDataBase != null) {
            myDataBase.close();
        }
        mDbHelper.close();
    }

    public Cursor getAllTodoLists () {
        try {
            return myDataBase.rawQuery("SELECT * FROM "+TODO_LIST_TABLE, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Cursor getAllItemsForListId (int listId) {
        try {
            return myDataBase.rawQuery("SELECT * FROM "+LIST_ITEMS_TABLE+" WHERE "+KEY_TODO_LIST_ID+" = "+listId, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateToDoList () {

    }
}
