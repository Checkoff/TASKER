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

public class ApplicationSetProgressBarMaxReceiver extends BroadcastReceiver {

    private ProviderGlobals g = ProviderGlobals.getInstance();
    private static Logger log = new Logger();

    @Override
    public void onReceive(Context context, Intent intent) {
        log.appendToLibraryLog("\n" + "Got broadcast receiver in ApplicationSetProgressBarMaxReceiver for messaging");

        String action = intent.getAction();

        try {

            // ************************************************************************************************
            // *************Modem Connection *******************************************************
            // ************************************************************************************************
            if (checkoffEventTypes.PROGRESS_BAR_SET_MAX.equals(action)) {
                log.appendToLibraryLog("\n" + "ACTION_PROGRESS_BAR_MAX", LogLevel.Debug_Log);

                try {

                    if (IsNullOrEmpty.isNotNull(g.getprogressBarObject())) {
                        log.appendToLibraryLog("\n" + "IN PROGRESS_BAR_MAX", LogLevel.Debug_Log);

                        int valueMax = Integer.parseInt(intent.getStringExtra("MAXVALUE"));
                        g.setprogressMaxValue(valueMax);

                        ProgressBar progressBar = g.getprogressBarObject();
                        progressBar.setMax(valueMax);
                        g.setprogressBarObject(progressBar);

                    }

                } catch (Exception e) {}
            }


            } catch (Exception e) {
            log.appendToLibraryLog("\n" + "Exception Generated in broadcast reciver  " + e.getMessage() + " Exception Type : " + e.getClass().toString());
        }

    }
}
