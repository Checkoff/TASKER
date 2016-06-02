package com.basdeo.AndroidBasicTemplateFullFunctionality.Handlers;

import android.app.Application;
import android.content.Context;

/**
 * Created by Eugene on 4/5/14.
 */
public class ApplicationContextHandler extends Application {

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
