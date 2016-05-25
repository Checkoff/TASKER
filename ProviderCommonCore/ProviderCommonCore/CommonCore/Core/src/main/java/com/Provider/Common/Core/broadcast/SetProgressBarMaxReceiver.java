package com.Provider.Common.Core.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.Provider.Common.Core.Globals.ProviderGlobals;
import com.Provider.Common.Core.Log.Logger;

/**
 * Created by Eugene on 5/24/16
 */
public class SetProgressBarMaxReceiver extends BroadcastReceiver {

        private ProviderGlobals g = ProviderGlobals.getInstance();
        private static Logger log = new Logger();

        @Override
        public void onReceive(Context c, Intent i) {

            try {
                String action = i.getAction();

                if (ProviderEventTypes.PROGRESS_BAR_SET_MAX.equals(action)) {
                    synchronized (this) {
                        int valueMax = Integer.parseInt(i.getStringExtra("MAXVALUE"));
                        g.setprogressMaxValue(valueMax);

                    }
                }
        } catch (Exception e) {

        }
    }

}
