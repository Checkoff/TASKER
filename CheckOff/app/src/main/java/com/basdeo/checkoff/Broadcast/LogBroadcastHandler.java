package com.basdeo.checkoff.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.basdeo.providercorelib.Globals.ProviderGlobals;
import com.basdeo.providercorelib.Log.Logger;

/**
 * Created by Eugene on 6/2/16
 */

public class LogBroadcastHandler extends BroadcastReceiver {

    private ProviderGlobals g = ProviderGlobals.getInstance();
    private static Logger log = new Logger();

        private String ACTION_UPDATE_SCREEN_LOG = "com.basdeo.checkoff.Broadcast.LogBroadcastHandler.UPDATE_SCREEN_LOG";


        @Override
        public void onReceive(Context c, Intent i) {

            try {
                Toast.makeText(c, "Intent Detected ", Toast.LENGTH_SHORT).show();

                String action = i.getAction();
                String inboudMessage = i.getStringExtra("Message");
                log.appendToLibraryLog("\n" + "Logging Intent Received In Main Application" + inboudMessage);

                 if (ACTION_UPDATE_SCREEN_LOG.equals(action)) {
                    synchronized (this) {


                    }
                }
        } catch (Exception e) {

        }
    }

}
