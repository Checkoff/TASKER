package com.Provider.Common.Core.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.Provider.Common.Core.Globals.ProviderGlobals;
import com.Provider.Common.Core.Log.Logger;

/**
 * Created by Eugene on 5/24/16.
 */
public class SetProgressReceiver extends BroadcastReceiver {

        private ProviderGlobals g = ProviderGlobals.getInstance();
        private static Logger log = new Logger();

        @Override
        public void onReceive(Context c, Intent i) {

            try {
                String action = i.getAction();

                if (ProviderEventTypes.PROGRESS_BAR_SET_FIXED_VALUE.equals(action)) {
                    synchronized (this) {
                        int value = Integer.parseInt(i.getStringExtra("NEWVALUE"));
                        g.setprogressCurrentValue(value);

                    }
                }
        } catch (Exception e) {

        }
    }

}
