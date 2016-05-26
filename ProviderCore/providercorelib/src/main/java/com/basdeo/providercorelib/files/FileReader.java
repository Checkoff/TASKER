package com.basdeo.providercorelib.files;

/**
 * Created by Eugene on 5/24/16.
 */
import com.basdeo.providercorelib.Log.LogLevel;
import com.basdeo.providercorelib.Log.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public FileReader() {}

    private static Logger log = new Logger();

    String ret = "";

    public String readAFile(InputStream inputStream) {
        try {

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            ret = "File not found: " + e.getMessage().toString();
            log.appendToLibraryLog("\n" + "Exception In readAFile : " + e.getMessage() + " Exception Type : " + e.getClass().toString(), LogLevel.Critical_Log);
        } catch (IOException e) {
            ret = "Can not read file: " + e.getMessage().toString();
            log.appendToLibraryLog("\n" + "Exception In readAFile : " + e.getMessage() + " Exception Type : " + e.getClass().toString(), LogLevel.Critical_Log);
        }

        return ret;
    }


/*
*    Method to read in a text file placed in the res/raw directory of the application. The
*    method reads in all lines of the file sequentially.
*    example of parameter gathering
*    InputStream is = this.getResources().openRawResource(R.raw.textfile);
*/
    private String[] readRaw(InputStream is){
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr, 8192);    // 2nd arg is buffer size
        List<String> result = new ArrayList<String>();
        // More efficient (less readable) implementation of above is the composite expression
        /*BufferedReader br = new BufferedReader(new InputStreamReader(
         this.getResources().openRawResource(R.raw.textfile)), 8192);*/

        try {
            String currentLine;
            while (true){
                currentLine = br.readLine();
                // readLine() returns null if no more lines in the file
                if(currentLine == null) break;
                result.add(currentLine);

            }
            isr.close();
            is.close();
            br.close();
        } catch (IOException e) {
            log.appendToLibraryLog("\n" + "Exception In readRaw : " + e.getMessage() + " Exception Type : " + e.getClass().toString(), LogLevel.Critical_Log);
        }
        return result.toArray(new String[result.size()]);

    }

}
