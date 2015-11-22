package com.rmz.todolist.db;

import android.content.Context;
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

        /**
         * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
         * @param context
         */
        DbHelper(Context context, String dbName) {
            super(context, dbName, null, DB_VERSION);
        }
    }


    private DbHelper mDbHelper;

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     *
     * @param ctx the Context within which to work
     */

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

    /**
     * Checks if the database is present. This only checks the existence of the file.
     * To validate the data sanity, a checksum should be done on the file.
     *
     * @param dbPath
     * @return
     */
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
//                    e.printStackTrace();
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
}
