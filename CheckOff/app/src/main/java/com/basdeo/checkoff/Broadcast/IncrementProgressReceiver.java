package com.basdeo.checkoff.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.basdeo.providercorelib.Globals.ProviderGlobals;
import com.basdeo.providercorelib.Log.Logger;

/**
 * Created by Eugene on 6/2/16
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
