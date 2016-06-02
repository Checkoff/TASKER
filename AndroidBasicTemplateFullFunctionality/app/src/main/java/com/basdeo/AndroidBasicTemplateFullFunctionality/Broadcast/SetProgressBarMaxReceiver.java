package com.basdeo.AndroidBasicTemplateFullFunctionality.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.basdeo.providercorelib.Globals.ProviderGlobals;
import com.basdeo.providercorelib.Log.Logger;

/**
 * Created by Eugene on 6/2/16
 */

public class SetProgressBarMaxReceiver extends BroadcastReceiver {

    private ProviderGlobals g = ProviderGlobals.getInstance();
    private static Logger log = new Logger();

    @Override
        public void onReceive(Context c, Intent i) {

            try {
                String action = i.getAction();

                if (templateEventTypes.PROGRESS_BAR_SET_MAX.equals(action)) {
                    synchronized (this) {
                        int valueMax = Integer.parseInt(i.getStringExtra("MAXVALUE"));
                        g.setprogressMaxValue(valueMax);

                    }
                }
        } catch (Exception e) {

        }
    }

}
