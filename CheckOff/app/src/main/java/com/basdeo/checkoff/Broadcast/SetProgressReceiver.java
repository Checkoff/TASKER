package com.basdeo.checkoff.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.basdeo.providercorelib.Globals.ProviderGlobals;
import com.basdeo.providercorelib.Log.Logger;

/**
 * Created by Eugene on 6/2/16
 */

public class SetProgressReceiver extends BroadcastReceiver {

    private ProviderGlobals g = ProviderGlobals.getInstance();
    private static Logger log = new Logger();

        @Override
        public void onReceive(Context c, Intent i) {

            try {
                String action = i.getAction();

                if (GroovvEventTypes.PROGRESS_BAR_SET_FIXED_VALUE.equals(action)) {
                    synchronized (this) {
                        int value = Integer.parseInt(i.getStringExtra("NEWVALUE"));
                        g.setprogressCurrentValue(value);

                    }
                }
        } catch (Exception e) {

        }
    }

}
