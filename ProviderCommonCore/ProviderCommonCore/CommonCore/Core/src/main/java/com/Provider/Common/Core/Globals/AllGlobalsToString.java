package com.Provider.Common.Core.Globals;

import com.Provider.Common.Core.Log.Logger;
import com.Provider.Common.Core.commonuse.ValuesAsString;

/**
 * Created by Eugene on 5/24/16.
 *
 * Returns the string equivalent of all global values
 *
 * Hashmaps, arrays and some types are listed in a more readable fashion
 */
public class AllGlobalsToString {

    private Logger log = new Logger();

    public AllGlobalsToString() {}

    public String loopThroughGlobalParams() {
        StringBuilder sb = new StringBuilder();
        com.Provider.Common.Core.Globals.ProviderGlobals g = com.Provider.Common.Core.Globals.ProviderGlobals.getInstance();

        try {

            log.appendToLibraryLog("\n" + "Entering loopThroughGlobalParams");
            sb.append("\n" + "====================================================================================================");

            sb.append("\n" + "====================================================================================================");

        } catch (Exception e) {
            log.appendToLibraryLog("\n" + "Exception Generated in method loopThroughGlobalParams " + e.getMessage() + " Exception Type : " + e.getClass().toString());
        } finally {

        }

        return sb.toString();
    }

}
