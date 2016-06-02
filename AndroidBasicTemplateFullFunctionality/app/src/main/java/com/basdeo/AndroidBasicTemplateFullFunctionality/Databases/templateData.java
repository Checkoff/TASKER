package com.basdeo.AndroidBasicTemplateFullFunctionality.Databases;

/**
 * Created by eugen on 6/2/2016.
 */

import android.database.sqlite.SQLiteDatabase;

import com.basdeo.providercorelib.Log.LogLevel;
import com.basdeo.providercorelib.Log.Logger;

public class templateData {

    // Database table
    public static final String TABLE_TASKS = "tasks";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_ISCOMPLETE = "iscomplete";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_SHOW_IN_WIDGET = "forwidget";

    private static Logger log = new Logger();

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_TASKS
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_CATEGORY + " text not null, "
            + COLUMN_ISCOMPLETE + " boolean not null,"
            + COLUMN_DESCRIPTION + " text not null"
            + COLUMN_SHOW_IN_WIDGET + " boolean not null,"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {

        log.appendToLibraryLog("\n" + "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data", LogLevel.Debug_Log);



        database.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        onCreate(database);
    }
}

