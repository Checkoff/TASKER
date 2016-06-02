package com.basdeo.providercorelib.dbaccess.DataActions;

import android.content.ContentValues;
import android.database.Cursor;

import com.basdeo.providercorelib.Globals.ProviderGlobals;
import com.basdeo.providercorelib.commonuse.IsNullOrEmpty;
import com.basdeo.providercorelib.Log.LogLevel;
import com.basdeo.providercorelib.Log.Logger;
import com.basdeo.providercorelib.dbaccess.DBController;
import com.basdeo.providercorelib.dbaccess.DataDefinitions.RequestTracking;

/**
 * Created by Eugene on 6/1/16
 */

    // ***********************************************************
    // Note the purpose of the class
    // This is a framework for addressing different DB actions that may need to be taken in a template format

    // The following gives an example of a rawQuery() call.
    //    -----------------------------------------------------


    //    Cursor cursor = getReadableDatabase().
    //    rawQuery("select * from todo where _id = ?", new String[] { id });


    //    The following gives an example of a query() call.
    //    -----------------------------------------------------

    //    return database.query(DATABASE_TABLE,
    //    new String[] { KEY_ROWID, KEY_CATEGORY, KEY_SUMMARY, KEY_DESCRIPTION },
    //    null, null, null, null, null);

    //Parameter		            Comment
    //--------------------	    ------------------------------------------------------------------------
    //String dbName		        The table name to compile the query against.
    //String[] columnNames	    A list of which table columns to return. Passing "null" will return all columns.
    //String whereClause	    Where-clause, i.e. filter for the selection of data, null will select all data.
    //String[] selectionArgs	You may include ?s in the "whereClause"". These placeholders will get replaced by the values from the selectionArgs array.
    //String[] groupBy	        A filter declaring how to group rows, null will cause the rows to not be grouped.
    //String[] having		    Filter for the groups, null means no filter.
    //String[] orderBy	        Table columns which will be used to order the data, null means no ordering.





// ***********************************************************

public class DB_CRUD {
    String result="";
    Cursor cursor = null;
    private ProviderGlobals g = ProviderGlobals.getInstance();
    private static Logger log = new Logger();


    public RequestTracking[] getRecords(String databaseName, String tableCreate, String databaseTable, int databaseVersion, String[] columns)
    {
        RequestTracking[] requestList = new RequestTracking[1];

        try {
            DBController dao = DBController.getInstance(g.getapplicationContext(), databaseName, tableCreate, databaseTable, databaseVersion);

            // if we have a valid access object we can continue
            if (IsNullOrEmpty.isNotNull(dao)) {
                ContentValues values = new ContentValues();

                cursor = dao.get(databaseTable, columns);

                int[] columnPositions = new int[columns.length];

                // get the positions of all the columns we want from the cursor schema
                for (int x =0; x< columns.length; x++) {
                    columnPositions[x] = cursor.getColumnIndex(columns[x]);
                }




            }

            dao.close();
        } catch (Exception e) {
            log.appendToLibraryLog("\n" + "Exception Generated in method RequestTracking getRecords " + e.getMessage() + " Exception Type : " + e.getClass().toString(), LogLevel.Critical_Log);
        } finally {
        }

        return requestList;
    }

    public boolean removeARecord(String databaseName, String tableCreate, String databaseTable, int databaseVersion)
    {
        boolean successful = false;

        try {
            DBController dao = DBController.getInstance(g.getapplicationContext(), databaseName, tableCreate, databaseTable, databaseVersion);

            // if we have a valid access object we can continue
            if (IsNullOrEmpty.isNotNull(dao)) {
            }

            dao.close();
        } catch (Exception e) {
            log.appendToLibraryLog("\n" + "Exception Generated in method RequestTracking removeARecord " + e.getMessage() + " Exception Type : " + e.getClass().toString(), LogLevel.Critical_Log);
        } finally {
        }

        return successful;
    }

    public boolean removeAllRecords(String databaseName, String tableCreate, String databaseTable, int databaseVersion)
    {
        boolean successful = false;

        try {
            DBController dao = DBController.getInstance(g.getapplicationContext(), databaseName, tableCreate, databaseTable, databaseVersion);

            // if we have a valid access object we can continue
            if (IsNullOrEmpty.isNotNull(dao)) {
                // delete all rows
                dao.delete(databaseTable);
            }

            dao.close();
        } catch (Exception e) {
            log.appendToLibraryLog("\n" + "Exception Generated in method RequestTracking removeAllRecords " + e.getMessage() + " Exception Type : " + e.getClass().toString(), LogLevel.Critical_Log);
        } finally {
        }

        return successful;
    }

    public boolean updateARecord(String databaseName, String tableCreate, String databaseTable, int databaseVersion)
    {
        boolean successful = false;

        try {
            DBController dao = DBController.getInstance(g.getapplicationContext(), databaseName, tableCreate, databaseTable, databaseVersion);

            // if we have a valid access object we can continue
            if (IsNullOrEmpty.isNotNull(dao)) {
            }

            dao.close();
        } catch (Exception e) {
            log.appendToLibraryLog("\n" + "Exception Generated in method RequestTracking updateARecord " + e.getMessage() + " Exception Type : " + e.getClass().toString(), LogLevel.Critical_Log);
        } finally {
        }

        return successful;
    }

    public boolean addARecord(String databaseName, String tableCreate, String databaseTable, int databaseVersion)
    {
        boolean successful = false;

        try {
            DBController dao = DBController.getInstance(g.getapplicationContext(), databaseName, tableCreate, databaseTable, databaseVersion);

            // if we have a valid access object we can continue
            if (IsNullOrEmpty.isNotNull(dao)) {
            }

            dao.close();
        } catch (Exception e) {
            log.appendToLibraryLog("\n" + "Exception Generated in method RequestTracking addARecord " + e.getMessage() + " Exception Type : " + e.getClass().toString(), LogLevel.Critical_Log);
        } finally {
        }

        return successful;
    }
}
