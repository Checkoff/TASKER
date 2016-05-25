package com.Provider.Common.Core.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.Provider.Common.Core.Globals.ProviderGlobals;
import com.Provider.Common.Core.Log.Logger;

/**
 * Created by Eugene on 5/24/16
 */
public class HIDAttachedReceiver extends BroadcastReceiver {

        private ProviderGlobals g = ProviderGlobals.getInstance();
        private static Logger log = new Logger();

        private String ACTION_HUMAN_INTERFACE_DEVICE_ATTACHED = "HIDAttachedReceiver.HUMAN_INTERFACE_DEVICE_ATTACHED";


        @Override
        public void onReceive(Context c, Intent i) {

            try {
                String action = i.getAction();

                if (ACTION_HUMAN_INTERFACE_DEVICE_ATTACHED.equals(action)) {
                    synchronized (this) {

                    }
                }
        } catch (Exception e) {

        }
    }

}
