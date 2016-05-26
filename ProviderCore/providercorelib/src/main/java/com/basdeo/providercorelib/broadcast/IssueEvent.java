package com.basdeo.providercorelib.broadcast;

import android.content.Context;
import android.content.Intent;

import java.util.HashMap;
import java.util.Map;

import com.basdeo.providercorelib.Globals.ProviderGlobals;

/**
 * Created by Eugene on 5/24/16
 *
 * Generic way to issue an event.   Used for ensuring the event broadcast is consistent
 *
 * Relys on the ProviderEventTypes and GroovvHashCreator for simple code usage
 *
 * Example usage :
 *      IssueEvent.IntentSender(
 *          ProviderEventTypes.LOG_UPDATE_COMBINED_READANDWRITE,
 *          GroovvHashCreator.GetAMap(new String[]{"LOG_TYPE"}, new String[]{"BOTH"}),
 *          "--> " + message,
 *          g.getapplicationContext()
 *      );
 *
 */
public class IssueEvent {

    private ProviderGlobals g = ProviderGlobals.getInstance();

    public static void IntentSender(
            String intent,
            HashMap<String, String> extras,
            String ExtraText,
            Context context) {

        Intent intentToIssue = new Intent();
        intentToIssue.setAction(intent);
        intentToIssue.putExtra(Intent.EXTRA_TEXT, ExtraText);
        for (Map.Entry<String, String> entry : extras.entrySet())
        {
            intentToIssue.putExtra(entry.getKey(), entry.getValue());

        }
        context.sendBroadcast(intentToIssue);

    }

}
