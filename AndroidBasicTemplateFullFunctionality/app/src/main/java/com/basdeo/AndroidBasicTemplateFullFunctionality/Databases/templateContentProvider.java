package com.basdeo.AndroidBasicTemplateFullFunctionality.Databases;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by eugen on 6/2/2016.
 */

public class templateContentProvider extends ContentProvider  {


        // database
        private templateDataDatabaseHelper database;

        // used for the UriMacher
        private static final int TASKS = 10;
        private static final int TASK_ID = 20;

        private static final String AUTHORITY = "com.basdeo.checkoff.Databases.templateContentProvider";

        private static final String BASE_PATH = "basdeo";
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
                + "/" + BASE_PATH);

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
                + "/todos";
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
                + "/todo";

        private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        static {
            sURIMatcher.addURI(AUTHORITY, BASE_PATH, TASKS);
            sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", TASK_ID);
        }

        @Override
        public boolean onCreate() {
            database = new templateDataDatabaseHelper(getContext());
            return false;
        }

        @Override
        public Cursor query(Uri uri, String[] projection, String selection,
                            String[] selectionArgs, String sortOrder) {

            // Uisng SQLiteQueryBuilder instead of query() method
            SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

            // check if the caller has requested a column which does not exists
            checkColumns(projection);

            // Set the table
            queryBuilder.setTables(templateData.TABLE_TASKS);

            int uriType = sURIMatcher.match(uri);
            switch (uriType) {
                case TASKS:
                    break;
                case TASK_ID:
                    // adding the ID to the original query
                    queryBuilder.appendWhere(templateData.COLUMN_ID + "="
                            + uri.getLastPathSegment());
                    break;
                default:
                    throw new IllegalArgumentException("Unknown URI: " + uri);
            }

            SQLiteDatabase db = database.getWritableDatabase();
            Cursor cursor = queryBuilder.query(db, projection, selection,
                    selectionArgs, null, null, sortOrder);
            // make sure that potential listeners are getting notified
            cursor.setNotificationUri(getContext().getContentResolver(), uri);

            return cursor;
        }

        @Override
        public String getType(Uri uri) {
            return null;
        }

        @Override
        public Uri insert(Uri uri, ContentValues values) {
            int uriType = sURIMatcher.match(uri);
            SQLiteDatabase sqlDB = database.getWritableDatabase();
            long id = 0;
            switch (uriType) {
                case TASKS:
                    id = sqlDB.insert(templateData.TABLE_TASKS, null, values);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown URI: " + uri);
            }
            getContext().getContentResolver().notifyChange(uri, null);
            return Uri.parse(BASE_PATH + "/" + id);
        }

        @Override
        public int delete(Uri uri, String selection, String[] selectionArgs) {
            int uriType = sURIMatcher.match(uri);
            SQLiteDatabase sqlDB = database.getWritableDatabase();
            int rowsDeleted = 0;
            switch (uriType) {
                case TASKS:
                    rowsDeleted = sqlDB.delete(templateData.TABLE_TASKS, selection,
                            selectionArgs);
                    break;
                case TASK_ID:
                    String id = uri.getLastPathSegment();
                    if (TextUtils.isEmpty(selection)) {
                        rowsDeleted = sqlDB.delete(templateData.TABLE_TASKS,
                                templateData.COLUMN_ID + "=" + id,
                                null);
                    } else {
                        rowsDeleted = sqlDB.delete(templateData.TABLE_TASKS,
                                templateData.COLUMN_ID + "=" + id
                                        + " and " + selection,
                                selectionArgs);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unknown URI: " + uri);
            }
            getContext().getContentResolver().notifyChange(uri, null);
            return rowsDeleted;
        }

        @Override
        public int update(Uri uri, ContentValues values, String selection,
                          String[] selectionArgs) {

            int uriType = sURIMatcher.match(uri);
            SQLiteDatabase sqlDB = database.getWritableDatabase();
            int rowsUpdated = 0;
            switch (uriType) {
                case TASKS:
                    rowsUpdated = sqlDB.update(templateData.TABLE_TASKS,
                            values,
                            selection,
                            selectionArgs);
                    break;
                case TASK_ID:
                    String id = uri.getLastPathSegment();
                    if (TextUtils.isEmpty(selection)) {
                        rowsUpdated = sqlDB.update(templateData.TABLE_TASKS,
                                values,
                                templateData.COLUMN_ID + "=" + id,
                                null);
                    } else {
                        rowsUpdated = sqlDB.update(templateData.TABLE_TASKS,
                                values,
                                templateData.COLUMN_ID + "=" + id
                                        + " and "
                                        + selection,
                                selectionArgs);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unknown URI: " + uri);
            }
            getContext().getContentResolver().notifyChange(uri, null);
            return rowsUpdated;
        }

        private void checkColumns(String[] projection) {
            String[] available = { templateData.COLUMN_CATEGORY,
                    templateData.COLUMN_ISCOMPLETE, templateData.COLUMN_DESCRIPTION,
                    templateData.COLUMN_ID, templateData.COLUMN_SHOW_IN_WIDGET };
            if (projection != null) {
                HashSet<String> requestedColumns = new HashSet<String>(Arrays.asList(projection));
                HashSet<String> availableColumns = new HashSet<String>(Arrays.asList(available));
                // check if all columns which are requested are available
                if (!availableColumns.containsAll(requestedColumns)) {
                    throw new IllegalArgumentException("Unknown columns in projection");
                }
            }
        }

    }
