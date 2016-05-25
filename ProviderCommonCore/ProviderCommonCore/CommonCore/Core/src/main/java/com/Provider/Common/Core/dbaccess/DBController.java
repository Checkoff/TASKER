package com.Provider.Common.Core.dbaccess;

/**
 * Created by Eugene on 5/24/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.Provider.Common.Core.Globals.ProviderGlobals;
import com.Provider.Common.Core.Log.Logger;

public class DBController extends SQLiteOpenHelper {

    public static final String KEY_ID = "_id";
    private static SQLiteDatabase db;
    private static String dName;
    private static String tName;
    private static String sql;
    private ProviderGlobals g = ProviderGlobals.getInstance();
    private static Logger log = new Logger();
    private static DBController instance;

    private DBController(Context ctx, String dbName, String sql, String tableName, int ver) {
        super(ctx, dbName, null, ver);
        log.appendToLibraryLog("\n" + "Creating or opening database [ " + dbName + " ].");
        DBController.sql = sql;
        dName = dbName;
        tName = tableName;
    }

    public static DBController getInstance(Context ctx, String dbName, String sql, String tableName, int ver) {
        if (instance == null) {
            instance = new DBController(ctx, dbName, sql, tableName, ver);
            try {
                log.appendToLibraryLog("\n" + "Creating or opening the database [ " + dbName + " ].");
                db = instance.getWritableDatabase();
            } catch (SQLiteException se) {
                log.appendToLibraryLog("\n" + "Cound not create and/or open the database [ " + dbName + " ] that will be used for reading and writing.");
            }
        }
        return instance;
    }

    public void close() {
        if (instance != null) {
            log.appendToLibraryLog("\n" + "Closing the database [ " + dName + " ].");
            db.close();
            instance = null;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        log.appendToLibraryLog("\n" + "Trying to create database table if it does notexist [ " + sql + " ].");
        try {
            db.execSQL(sql);
        } catch (SQLException se) {
            log.appendToLibraryLog("\n" + "Cound not create the database table according to the SQL statement [ " + sql + " ].");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        log.appendToLibraryLog("\n" + "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        try {
            db.execSQL("DROP TABLE IF EXISTS " + tName);
        } catch (SQLException se) {
            log.appendToLibraryLog("\n" + "Cound not drop the database table [ " + tName + " ].");
        }
        onCreate(db);
    }

    public long insert(String table, ContentValues values) {
        return db.insert(table, null, values);
    }

    public Cursor get(String table, String[] columns) {
        return db.query(table, columns, null, null, null, null, null);
    }

    public Cursor get(String table, String[] columns, long id) {
        Cursor cursor = db.query(true, table, columns, KEY_ID + "=" + id, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int delete(String table) {
        return db.delete(table, "1", null);
    }

    public int delete(String table, long id) {
        return db.delete(table, KEY_ID + "=" + id, null);
    }

    public int update(String table, long id, ContentValues values) {
        return db.update(table, values, KEY_ID + "=" + id, null);
    }
}
