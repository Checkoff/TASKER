package com.Provider.Common.Core.commonuse;

import com.Provider.Common.Core.Log.LogLevel;
import com.Provider.Common.Core.Log.Logger;

import java.util.HashMap;

/**
 * Created by Eugene on 5/24/16
 *
 * Just a simple way to create a hash without having to do this outside of a call all the time
 *
 * Often times a developer needs a new hash that has a small number of entries.   The class would
 * be most useful during those situations.  The larger the number of entries in the hash this class
 * becomes less valuable and less helpful in making the code readable
 *
 * Example Usage :
 * GroovvHashCreator.GetAMap(new String[]{"Key1", "Key2"}, new String[]{"Value1", "Value2"})
 */
public class HashCreator {

    private static Logger log = new Logger();

    public static HashMap<String, String> GetAMap(String[] key, String[] value)
    {
        HashMap<String, String> mapToReturn = new HashMap<String, String>();

        try {
            if (key.length != value.length) {
            }

            for (int x = 0; x < key.length; x++) {
               mapToReturn.put(key[x], value[x]);
            }

        } catch (Exception e) {
            log.appendToLibraryLog("\n" + "Exception In GroovvHashCreator : " + e.getMessage() + " Exception Type : " + e.getClass().toString(), LogLevel.Critical_Log);
        } finally {
            //
        }

        return mapToReturn;
    }

}
