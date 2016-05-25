package com.Provider.Common.Core.commonuse;

import android.content.SharedPreferences;

import com.Provider.Common.Core.Globals.ProviderGlobals;
import com.Provider.Common.Core.Log.LogLevel;
import com.Provider.Common.Core.Log.Logger;
import com.Provider.Common.Core.files.AssetReader;
import com.Provider.Common.Core.files.FileWriter;
import com.Provider.Common.Core.files.XMLResourceReader;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eugene on 5/24/16.
 */
public class ConfigProvider {


    private ProviderGlobals g = ProviderGlobals.getInstance();
    private Logger log = new Logger();
    private FileWriter fw = new FileWriter();
    private XMLResourceReader xmlReader = new XMLResourceReader();
    private HashMap<String, String> configurationHash  = new HashMap<String, String>();
    private AssetReader ar = new AssetReader();

    public ConfigProvider() { }

    /*
     *  order of precedence
     *  asset is overwritten by  - shared preferences
     *  shared preferences is overwritten by  - added by application later
     *
     */
    public  HashMap<String, String>  getConfigHashNow(String modemNameFromConfig)
    {
        SharedPreferences.Editor editor = g.geteditor();
        try {

            // configuration defaults for the modem device.  This is the lowest order for configuration
            // values.
            String deviceConfigFromPreferences = ar.readTextAssetFile(modemNameFromConfig.replace(" ", "_") + "_setup.xml", g.getAssetManager());
            String[] keysForHash = ar.readTextAssetFileLinesNoLineBreaks("shared_resource_value_keys.txt", g.getAssetManager());

            for (int keyCounter =0; keyCounter < keysForHash.length; keyCounter++) {
                // remove spaces for the \r vs \n handling of file formatting
                String keyValue = keysForHash[keyCounter].toString().replace(" ", "").replace("<device>", modemNameFromConfig);
                String valueValue = xmlReader.getConfigValueFromXMLString(deviceConfigFromPreferences, keyValue);
                if (configurationHash.containsKey(keyValue)) {
                    if (g.getExtraDetailTrace()) log.appendToLibraryLog("\n" + "Ignoring entry : " + keyValue + " because already in config by high order precedence", LogLevel.Debug_Log);
                }
                else {
                    configurationHash.put(keyValue, valueValue);
                    if (g.getExtraDetailTrace()) log.appendToLibraryLog("\n" + "Entry added to hash : " + keyValue, LogLevel.Debug_Log);
                }

                // catch all to make sure the key won't conflict
                try { editor.remove(keyValue); } catch (Exception e) {}
                editor.putString(keyValue, valueValue);
            }
            editor.commit();

        } catch(Exception e)
        {
            log.appendToLibraryLog("\n" + "Exception Generated in method getappconfigInHash " + e.getMessage() + " Exception Type : " + e.getClass().toString());
        } finally {
            g.setconfigurationValues(configurationHash);
            editor.apply();
        }

        return configurationHash;
    }

    public  HashMap<String, String>  AddSettingsToConfigHash( HashMap<String, String> configHash, String modemNameFromConfig)
    {
        Map<String, ?> keys = g.getsettings().getAll();

        for (Map.Entry<String, ?> entry : keys.entrySet()) {

            if (configHash.containsKey(entry.getKey().toString()))
                configHash.remove(entry.getKey().toString());

            configHash.put(entry.getKey().toString(), entry.getValue().toString());
        }

        return configHash;
    }

    public  HashMap<String, String>  AddApplicationConfigurationToConfigHash( HashMap<String, String> configHash, String modemNameFromConfig)
    {
        // configuration that is application consuming the library specific.  This is the highest order for
        // configuration
        String applicationConfigurationSettings = ar.readTextAssetFile("application_configuration.cfg", g.getAssetManager());


        return configHash;

    }

}
