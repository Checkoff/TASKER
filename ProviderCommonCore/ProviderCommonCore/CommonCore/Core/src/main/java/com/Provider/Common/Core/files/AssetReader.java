package com.Provider.Common.Core.files;

/**
 * Created by Eugene on 5/24/16.
 */

import android.content.res.AssetManager;

import com.Provider.Common.Core.Globals.ProviderGlobals;
import com.Provider.Common.Core.Log.LogLevel;
import com.Provider.Common.Core.Log.Logger;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class AssetReader {

    private static Logger log = new Logger();
    private ProviderGlobals g = ProviderGlobals.getInstance();

    public AssetReader() {}

    public String readTextAssetFile(String filename, AssetManager assetManager) {

        String outString = "";

        try {
            InputStream inputStream = assetManager.open(filename);
            ByteArrayOutputStream  outputStream = new ByteArrayOutputStream();
            byte buf[] = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outString = outputStream.toString();
            outputStream.close();
            inputStream.close();

        } catch (Exception e) {
            log.appendToLibraryLog("\n" + "Exception In readTextAssetFile : " + e.getMessage() + " Exception Type : " + e.getClass().toString(), LogLevel.Critical_Log);
            outString = "Error Reading File " + e.getMessage();
        }

        return outString;
    }

    public String[] readTextAssetFileLinesNoLineBreaks(String filename, AssetManager assetManager) {

        try {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(assetManager.open(filename)));
            ArrayList<String> values = new ArrayList<String>();
            String line = bReader.readLine();
            while (line != null) {
                values.add(line);
                line = bReader.readLine();
            }
            bReader.close();

            return values.toArray(new String[values.size()]);
        } catch (Exception e) {
            log.appendToLibraryLog("\n" + "Exception In readTextAssetFileLines : " + e.getMessage() + " Exception Type : " + e.getClass().toString(), LogLevel.Critical_Log);
            return new String[1];
        } finally {
        }

    }

    public String[] readTextAssetFileLines(String filename, AssetManager assetManager) {

        try {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(assetManager.open(filename)));
            ArrayList<String> values = new ArrayList<String>();
            String line = bReader.readLine();
            while (line != null) {
                line.replaceAll("(\\r|\\n)", "");
                line = line + "\r";
                values.add(line);
                line = bReader.readLine();
            }
            bReader.close();

            return values.toArray(new String[values.size()]);
        } catch (Exception e) {
            log.appendToLibraryLog("\n" + "Exception In readTextAssetFileLines : " + e.getMessage() + " Exception Type : " + e.getClass().toString(), LogLevel.Critical_Log);
            return new String[1];
        } finally {
        }

    }

    public String[] getAssetFileList(AssetManager assetManager)
    {
        String[] files = new String[1];
        try {
            // using "" in the name pulls all assets in current API version level
            files = assetManager.list("");
        } catch (Exception e) {
            log.appendToLibraryLog("\n" + "Exception In getAssetFileList : " + e.getMessage() + " Exception Type : " + e.getClass().toString(), LogLevel.Critical_Log);
        }

        return files;

    }

    public String[] getInitializationFilesFromAssetRepository() {
        String[] fileList = new String[1];
        List<String> result = new ArrayList<String>();

        try {
            final ProviderGlobals g = ProviderGlobals.getInstance();
            AssetManager aMan = g.getAssetManager();
            String[] tempList = aMan.list("");

            for (int x=0; x<tempList.length; x++) {
                if (tempList[x].toString().contains(".inz"))
                    result.add(tempList[x].toString());
            }
            fileList = result.toArray(new String[result.size()]);

        } catch(Exception e)
        {
            log.appendToLibraryLog("\n" + "Exception Generated in method getInitializationFilesFromAssetRepository " + e.getMessage() + " Exception Type : " + e.getClass().toString(), LogLevel.Critical_Log);
        } finally {

        }

        return fileList;
    }

}
