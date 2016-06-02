package com.basdeo.checkoff.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ProgressBar;

import com.basdeo.providercorelib.Globals.ProviderGlobals;
import com.basdeo.providercorelib.Log.LogLevel;
import com.basdeo.providercorelib.Log.Logger;
import com.basdeo.providercorelib.commonuse.IsNullOrEmpty;


/**
 * Created by Eugene on 6/2/16
 */

public class ApplicationIncrementProgressReceiver extends BroadcastReceiver {

    private ProviderGlobals g = ProviderGlobals.getInstance();
    private static Logger log = new Logger();

    private String ACTION_PROGRESS_INCREMENT = "com.basdeo.checkoff.Broadcast.IncrementProgressReceiver.INCREMENT_PROGRESS";



    @Override
    public void onReceive(Context context, Intent intent) {
        log.appendToLibraryLog("\n" + "Got broadcast receiver in ApplicationIncrementProgressReceiver for messaging");

        String action = intent.getAction();

        try {

            // ************************************************************************************************
            // *************Modem Connection *******************************************************
            // ************************************************************************************************
            if (ACTION_PROGRESS_INCREMENT.equals(action)) {
                log.appendToLibraryLog("\n" + "ACTION_PROGRESS_INCREMENT", LogLevel.Debug_Log);

                try {

                    if (IsNullOrEmpty.isNotNull(g.getprogressBarObject())) {


                        int oldValue = g.getprogressCurrentValue();
                        oldValue++;
                        int valueMultiplier = g.getprogressMultiplier();
                        int newValue = oldValue * valueMultiplier;

                        g.setprogressCurrentValue(newValue);

                        ProgressBar progressBar = g.getprogressBarObject();
                        progressBar.setProgress(newValue);
                        g.setprogressBarObject(progressBar);
                    }

                 } catch (Exception e) {}
            }


            } catch (Exception e) {
            log.appendToLibraryLog("\n" + "Exception Generated in broadcast reciver  " + e.getMessage() + " Exception Type : " + e.getClass().toString());
        }

    }
}
