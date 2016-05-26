package com.basdeo.providercorelib.Log;

import android.content.Intent;

import com.basdeo.providercorelib.Globals.ProviderGlobals;

// Created by Eugene on 5/24/16.

public class Logger {

    public Logger() {}
    private ProviderGlobals g = ProviderGlobals.getInstance();

    public void appendToLibraryLog(String MessageToAppend) {
        try {
            // put in main log also
            appendToLog(MessageToAppend, LogLevel.Debug_Log);
            g.getapplicationLog().append(MessageToAppend);
        } catch (Exception e) {
            appendToLog("\n" + "Exception In appendToLog : " + e.getMessage() + " Exception Type : " + e.getClass().toString(), LogLevel.Critical_Log);
        }
    }

    public void appendToLibraryLog(String MessageToAppend, int logLevel) {
        try {
            // put in main log also
            if (logLevel == LogLevel.Debug_Log) {
                if (g.getisDebugModeActive()) {
                    appendToLog(MessageToAppend, logLevel);
                    g.getapplicationLog().append(MessageToAppend);
                }
            }
            else
                {
                    appendToLog(MessageToAppend, logLevel);
                    g.getapplicationLog().append(MessageToAppend);
                }
        } catch (Exception e) {
            appendToLog("\n" + "Exception In appendToLog : " + e.getMessage() + " Exception Type : " + e.getClass().toString(), LogLevel.Critical_Log);
        }
    }


    public void appendToLog(String MessageToAppend, int logLevel) {

        try {

            switch (logLevel) {
                case 0:

                case 1:

                case 2:

                case 3:

                case 4:

                case 5:

                default:

            }

        } catch (Exception e) {

        }
    }


    public void appendToLog(String MessageToAppend) {

        try {

        } catch (Exception e) {

        }
    }

    public void appendToSend(String MessageToAppend) {

        try {

        } catch (Exception e) {

        }
    }

    public void appendToResponse(String MessageToAppend) {

        try {

        } catch (Exception e) {

        }
    }

    // logLevel 0 = all logs
    // logLevel 1 = send log only
    // logLevel 2 = send and receive log
    // logLevel 3 = critical logs
    // logLevel 4 = information logs
    // logLevel 5 = debug logs
    public void MailMyLog(int logLevel) {

        String LogListing = "";


        appendToLog("\n" + "Entering sendEmail", 5);

        try {

            switch (logLevel) {
                case 0:

                case 1:

                case 2:

                case 3:

                case 4:

                case 5:

                default:

            }

            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822") ;
            i.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");//sending email via gmail
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{"elarkin@tmsoffice.com"});
            i.putExtra(Intent.EXTRA_SUBJECT,"Google Dialer Log");
            i.putExtra(Intent.EXTRA_TEXT,LogListing);
//            startActivity(i);
        } catch(Exception e)
        {
            appendToLog("\n" + "Exception Generated in method sendEmail " + e.getMessage()+ " Exception Type : " + e.getClass().toString(), 3);
        } finally {

        }
    }

    // clear logs of given level
    public static void ClearLog(int logLevelToClear) {

        try {

        } catch(Exception e)
        {
        } finally {

        }
    }
  }
