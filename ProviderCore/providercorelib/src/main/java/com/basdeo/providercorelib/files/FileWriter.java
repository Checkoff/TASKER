package com.basdeo.providercorelib.files;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import android.os.Environment;

import com.basdeo.providercorelib.Log.LogLevel;
import com.basdeo.providercorelib.Log.Logger;


/**
 * Created by Eugene on 5/24/16.
 */
public class FileWriter {
    public FileWriter() {}

    private static Logger log = new Logger();

    String ret = "";

    public Boolean writeAFile(OutputStreamWriter outputStreamWriter, String data) {
        try {
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            log.appendToLog("File write failed: " + e.toString(), LogLevel.Critical_Log);
            return false;
        }

        return true;
    }


//    Method to write ascii text characters to file on SD card. Note that you must add a
//    WRITE_EXTERNAL_STORAGE permission to the manifest file or this method will throw
//    a FileNotFound Exception because you won't have write permission.
//    examples for passing values
//    File dir = new File (root.getAbsolutePath() + "/download");
//    File file = new File(dir, "myData.txt");

    private boolean writeToSDFile(File file, File dir, String[] fileLines){

        // Find the root of the external storage.
        // See http://developer.android.com/guide/topics/data/data-storage.html#filesExternal

        File root = Environment.getExternalStorageDirectory();

        // See http://stackoverflow.com/questions/3551821/android-write-to-sd-card-folder

        dir.mkdirs();

        try {
            FileOutputStream f = new FileOutputStream(file);
            PrintWriter outFileWriter = new PrintWriter(f);
            for (int lineCounter = 0; lineCounter<fileLines.length; lineCounter++) {
                outFileWriter.println("Howdy do to you.");
                outFileWriter.println("Here is a second line.");
            }
            outFileWriter.flush();
            outFileWriter.close();
            f.close();
        } catch (FileNotFoundException e) {
            log.appendToLog("\n" + "File write failed: " + e.toString(), LogLevel.Critical_Log);
            log.appendToLibraryLog("\n" + "File not found. Did you" +
                    " add a WRITE_EXTERNAL_STORAGE permission to the manifest?");
            return false;
        } catch (IOException e) {
            log.appendToLog("\n"+ "File write failed: " + e.toString(), LogLevel.Critical_Log);
            return false;
        }
        log.appendToLibraryLog("\n\nFile written to:\n"+file);
        return true;
    }

//    Method to check whether external media writable.

    private boolean checkExternalMediaWriteable(){
        boolean mExternalStorageWriteable = false;
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // Can read and write the media
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // Can only read the media
            mExternalStorageWriteable = false;
        } else {
            // Can't read or write
            mExternalStorageWriteable = true;
        }
        return mExternalStorageWriteable;
    }

    //    Method to check whether external media writable.

    private boolean checkExternalMediaAvailable(){
        boolean mExternalStorageAvailable = false;
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // Can only read the media
            mExternalStorageAvailable = true;
        } else {
            // Can't read or write
            mExternalStorageAvailable = false;
        }
        return mExternalStorageAvailable;
    }

}
