package com.Provider.Common.Core.dbaccess.DataDefinitions;

import com.Provider.Common.Core.Globals.ProviderGlobals;
import com.Provider.Common.Core.Log.Logger;

/**
 * Created by Eugene on 5/25/2014.
 *
 * Table Definition for RequestTracking table in RequestHistory DB
 */
public class RequestTracking {

    public static final String DATABASE_NAME = "RequestHistory";
    public static final String DATABASE_TABLE = "RequestTracking";
    public static final int DATABASE_VERSION = 1;
    private ProviderGlobals g = ProviderGlobals.getInstance();
    private static Logger log = new Logger();

    public static final String TABLE_CREATE =
            "create table notes (_id integer primary key autoincrement, "
                    + "request_date text not null);";

    public static final String COL_REQUESTDATE = "request_date";

    private int id;
    private String request_date;

    public int getId() {
        return id;
    }
    public String getRequest_Date() {
        return request_date;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setRequest_Date(String request_date) {
        this.request_date = request_date;
    }


}
