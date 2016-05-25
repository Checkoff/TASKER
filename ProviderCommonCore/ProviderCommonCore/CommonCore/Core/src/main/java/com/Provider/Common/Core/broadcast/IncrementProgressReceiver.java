package com.Provider.Common.Core.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.Provider.Common.Core.Globals.ProviderGlobals;
import com.Provider.Common.Core.Log.Logger;

/**
 * Created by Eugene on 5/24/16
 */
public class IncrementProgressReceiver extends BroadcastReceiver {

        private ProviderGlobals g = ProviderGlobals.getInstance();
        private static Logger log = new Logger();

        private String ACTION_INCREMENT_PROGRESS = "IncrementProgressReceiver.INCREMENT_PROGRESS";


        @Override
        public void onReceive(Context c, Intent i) {

            try {
                String action = i.getAction();

                if (ACTION_INCREMENT_PROGRESS.equals(action)) {
                    synchronized (this) {
                        int newValue = g.getprogressCurrentValue();
                        int valueMultiplier = g.getprogressMultiplier();
                        newValue = newValue * valueMultiplier;

                        g.setprogressCurrentValue(newValue);

                    }
                }
        } catch (Exception e) {

        }
    }

}
