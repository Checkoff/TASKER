package com.basdeo.checkoff;

/**
 * Created by eugen on 5/25/2016.
 */
import android.app.Application;
import android.content.Context;

public class ApplicationContextHandler  extends Application {

    /**
     * Keeps a reference of the application context
     */
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = getApplicationContext();

    }

    /**
     * Returns the application context
     *
     * @return application context
     */
    public static Context getContext() {
        return sContext;
    }
}
