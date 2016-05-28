package com.basdeo.checkoff;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.basdeo.providercorelib.Globals.AllGlobalsToString;
import com.basdeo.providercorelib.Globals.ProviderGlobals;
import com.basdeo.providercorelib.Log.LogLevel;
import com.basdeo.providercorelib.Log.Logger;
import com.basdeo.providercorelib.commonuse.IsNullOrEmpty;
import com.basdeo.providercorelib.commonuse.ScreenRotationHandler;

import java.util.Map;

/**
 * Created by eugen on 5/28/2016.
 */
public class TaskerMain  extends Activity {

    // *********************************************************************************************************************************************************************
    // *********************************************************************************************************************************************************************
    // *********************************************************************************************************************************************************************
    // START Declarations
    // *********************************************************************************************************************************************************************
    // *********************************************************************************************************************************************************************
    // *********************************************************************************************************************************************************************

    private static final String[] titles = {"Dashboard" };
    private static AllGlobalsToString ags = new AllGlobalsToString();

    private ScreenRotationHandler srh = new ScreenRotationHandler();
    private ProviderGlobals g = ProviderGlobals.getInstance();

    private ProgressDialog progressBarLoad;
    private int progressBarStatusLoad = 0;
    private Handler progressBarHandlerLoad = new Handler();
    private static Logger log = new Logger();

    // *********************************************************************************************************************************************************************
    // *********************************************************************************************************************************************************************
    // *********************************************************************************************************************************************************************
    // START Create / Main Menu / Setup Portion / CONTROL
    // *********************************************************************************************************************************************************************
    // *********************************************************************************************************************************************************************
    // *********************************************************************************************************************************************************************


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_view);
        loadUsUp("Loading .....", 1000);
    }

    protected CharSequence getTabTitle(int position) {
        return titles[position];
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_dashboard, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.showlog:
                showLogSoFar();
                return true;
            case R.id.clear_application_log_option:
                clearApplicationLog();
               return true;
            case R.id.showSharedProperties:
                showSavedConfiguration();
                return true;
            case R.id.showGlobals:
                showGlobals();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);
        lockScreenRotation();


    }

    private void loadUsUp(String messsage, final int timeToShow)
    {
        try {

            final ProgressDialog dialog = ProgressDialog.show(TaskerMain.this, "Please Wait", messsage, true);
            Thread t = new Thread(new Runnable(){
                public void run() {
                    try{
                        Thread.sleep(timeToShow);
                    } catch (InterruptedException e) {
                        // no-op
                        log.appendToLibraryLog("\n" + "Interrupted Thread Exception In loadUsUp " + e.getMessage() + " Exception Type : " + e.getClass().toString());
                    }
                    dialog.dismiss();
                }
            });
            t.start();


        } catch (Exception e) {
            log.appendToLibraryLog("\n" + "Exception Generated in method loadUsUp " + e.getMessage() + " Exception Type : " + e.getClass().toString());
        } finally {

        }
    }

    private void lockScreenRotation()
    {
        try {
            log.appendToLibraryLog("\n" + "Entering allowScreenRotation", LogLevel.Debug_Log);
            // get current rotation
            int lockValue = srh.getValueToLockScreenOrientationToCurrent(getWindowManager().getDefaultDisplay());
            // get description of the rotation
            String currentOrientation = srh.getDescriptionOfCurrentScreenRotation(getWindowManager().getDefaultDisplay());
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } catch (Exception e) {
            log.appendToLibraryLog("\n" + "Exception Generated in method lockScreenRotation " + e.getMessage() + " Exception Type : " + e.getClass().toString());
        } finally {

        }

    }
    private void allowScreenRotation()
    {
        try {
            log.appendToLibraryLog("\n" + "Entering allowScreenRotation", LogLevel.Debug_Log);
            // allow rotation to occur at user request
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        } catch (Exception e) {
            log.appendToLibraryLog("\n" + "Exception Generated in method allowScreenRotation " + e.getMessage() + " Exception Type : " + e.getClass().toString());
        } finally {

        }
    }

    private void showSharedResourceFile() {
        log.appendToLibraryLog("\n" + "Entering showsharedresourcefile", LogLevel.Debug_Log);

        try {
            LayoutInflater layoutInflater
                    = (LayoutInflater) getBaseContext()
                    .getSystemService(LAYOUT_INFLATER_SERVICE);
            View popupView = layoutInflater.inflate(R.layout.showsharedresourcefile, null);

            final PopupWindow popupWindowSharedResourceXML = new PopupWindow(
                    popupView,
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    true);

            EditText sharedResourceXMLFileDisplay = (EditText) popupWindowSharedResourceXML.getContentView().findViewById(R.id.sharedResourceXMLFileDisplay);

            // get context values
            final ImageView mailButton = (ImageView) popupWindowSharedResourceXML.getContentView().findViewById(R.id.mailButton);
            mailButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MailThisForMe(g.getapplicationLog().toString(), "Groovv Diagnostic Shared Properties XML Data");
                }
            });

            final ImageView hideSharedResourceXMLView = (ImageView) popupWindowSharedResourceXML.getContentView().findViewById(R.id.hideSharedResourceXMLView);
            hideSharedResourceXMLView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (popupWindowSharedResourceXML.isShowing()) {
                        popupWindowSharedResourceXML.dismiss();
                    }
                }
            });

            // backkey handling for popup
            final View popUpWindowLaout = popupWindowSharedResourceXML.getContentView();
            popUpWindowLaout.setFocusableInTouchMode(true);

            popUpWindowLaout.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (KeyEvent.KEYCODE_BACK == keyCode) { // match BACK key            {
                        return true; // indicate that we handled event, won't propagate it
                    }
                    return false; // when we don't handle other keys, propagate event further
                }
            });

            popupWindowSharedResourceXML.showAtLocation(popupView, Gravity.CENTER, 40, 40);

        } catch (Exception e) {
            log.appendToLibraryLog("\n" + "Exception Generated in method showsharedresourcefile " + e.getMessage() + " Exception Type : " + e.getClass().toString());
        } finally {

        }
    }

    private void showLogSoFar() {
        log.appendToLibraryLog("\n" + "Entering showLogSoFar", LogLevel.Debug_Log);

        try {
            LayoutInflater layoutInflater
                    = (LayoutInflater) getBaseContext()
                    .getSystemService(LAYOUT_INFLATER_SERVICE);
            View popupView = layoutInflater.inflate(R.layout.showcurrentlog, null);

            final PopupWindow popupWindowLog = new PopupWindow(
                    popupView,
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    true);

            EditText setupScreenLog = (EditText) popupWindowLog.getContentView().findViewById(R.id.setupScreenLog);

            // get context values
            final ImageView mailButton = (ImageView) popupWindowLog.getContentView().findViewById(R.id.mailButton);
            mailButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MailThisForMe(g.getapplicationLog().toString(), "Groovv Diagnostic Tool Log");
                }
            });

            final ImageView hideLogView = (ImageView) popupWindowLog.getContentView().findViewById(R.id.hideLogView);
            hideLogView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (popupWindowLog.isShowing()) {
                        popupWindowLog.dismiss();
                    }
                }
            });

            // backkey handling for popup
            final View popUpWindowLaout = popupWindowLog.getContentView();
            popUpWindowLaout.setFocusableInTouchMode(true);

            popUpWindowLaout.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (KeyEvent.KEYCODE_BACK == keyCode) { // match BACK key            {
                        return true; // indicate that we handled event, won't propagate it
                    }
                    return false; // when we don't handle other keys, propagate event further
                }
            });


            setupScreenLog.setText(g.getapplicationLog().toString(), EditText.BufferType.NORMAL);
            popupWindowLog.showAtLocation(popupView, Gravity.CENTER, 40, 40);

        } catch (Exception e) {
            log.appendToLibraryLog("\n" + "Exception Generated in method showLogSoFar " + e.getMessage() + " Exception Type : " + e.getClass().toString());
        } finally {

        }
    }
        public void MailThisForMe(String messageToEmail, String titleToSend) {

            log.appendToLibraryLog("\n" + "Entering MailMyGlobals", LogLevel.Debug_Log);

            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail"); //sending email via gmail
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"elarkin@tmsoffice.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, titleToSend);
                i.putExtra(Intent.EXTRA_TEXT, messageToEmail);
                startActivity(i);
            } catch (Exception e) {
                log.appendToLibraryLog("\n" + "Exception Generated in method MailMyGlobals " + e.getMessage() + " Exception Type : " + e.getClass().toString());
            } finally {

            }

        }


    private void showGlobals() {
        log.appendToLibraryLog("\n" + "Entering showglobals", LogLevel.Debug_Log);

        try {

            StringBuilder valuesOfGlobals = new StringBuilder();

            LayoutInflater layoutInflater
                    = (LayoutInflater) getBaseContext()
                    .getSystemService(LAYOUT_INFLATER_SERVICE);
            View popupView = layoutInflater.inflate(R.layout.showglobals, null);

            final PopupWindow popupWindowGlobals = new PopupWindow(
                    popupView,
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    true);

            valuesOfGlobals.append("\n" + "Global Values Currently found");
            valuesOfGlobals.append(ags.loopThroughGlobalParams());


            log.appendToLibraryLog("\n" + "Got popup.  Checking for values", LogLevel.Debug_Log);
            final EditText globalsOnTheScreen = (EditText) popupWindowGlobals.getContentView().findViewById(R.id.globalsOnTheScreen);

            log.appendToLibraryLog("\n" + "Got context of the listing field", LogLevel.Debug_Log);
            // get context values
            ImageView hideGlobalsView = (ImageView) popupWindowGlobals.getContentView().findViewById(R.id.hideGlobalsView);
            hideGlobalsView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (popupWindowGlobals.isShowing()) {
                        popupWindowGlobals.dismiss();
                    }
                }
            });

            ImageView shareGlobals = (ImageView) popupWindowGlobals.getContentView().findViewById(R.id.shareGlobals);
            shareGlobals.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MailThisForMe(globalsOnTheScreen.getText().toString(), "Global Values In Running Groovv Application");

                }
            });

            // backkey handling for popup
            final View popUpWindowLaout = popupWindowGlobals.getContentView();
            popUpWindowLaout.setFocusableInTouchMode(true);

            popUpWindowLaout.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (KeyEvent.KEYCODE_BACK == keyCode) { // match BACK key            {
                        return true; // indicate that we handled event, won't propagate it
                    }
                    return false; // when we don't handle other keys, propagate event further
                }
            });

            globalsOnTheScreen.setText(valuesOfGlobals);


            // popup configuration screen
            popupWindowGlobals.showAtLocation(popupView, Gravity.CENTER, 40, 40);


        } catch (Exception e) {
            log.appendToLibraryLog("\n" + "Exception Generated in method showKnownDevices " + e.getMessage() + " Exception Type : " + e.getClass().toString());
        } finally {

        }
    }

    private void clearApplicationLog() {
        log.appendToLibraryLog("\n" + "Entering clearApplicationLog", LogLevel.Debug_Log);

        try {

            g.setapplicationLog(new StringBuilder());

            Toast.makeText(getApplicationContext(), "Application Log Cleared", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            log.appendToLibraryLog("\n" + "Exception Generated in method clearApplicationLog " + e.getMessage() + " Exception Type : " + e.getClass().toString());
        } finally {

        }
    }
    private void showSavedConfiguration() {
        log.appendToLibraryLog("\n" + "Entering showSavedConfiguration", LogLevel.Debug_Log);

        try {
            LayoutInflater layoutInflater
                    = (LayoutInflater) getBaseContext()
                    .getSystemService(LAYOUT_INFLATER_SERVICE);
            View popupView = layoutInflater.inflate(R.layout.showsavedconfiguration, null);

            final PopupWindow popupWindowConfig = new PopupWindow(
                    popupView,
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    true);

            log.appendToLibraryLog("\n" + "Got popup.  Checking for values", LogLevel.Debug_Log);
            final EditText configOnTheScreen = (EditText) popupWindowConfig.getContentView().findViewById(R.id.configOnTheScreen);

            log.appendToLibraryLog("\n" + "Got context of the listing field", LogLevel.Debug_Log);
            // get context values
            ImageView hideConfigView = (ImageView) popupWindowConfig.getContentView().findViewById(R.id.hideConfigView);
            hideConfigView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (popupWindowConfig.isShowing()) {
                        popupWindowConfig.dismiss();
                    }
                }
            });

            ImageView shareConfig = (ImageView) popupWindowConfig.getContentView().findViewById(R.id.shareConfig);
            shareConfig.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    String message = configOnTheScreen.getText().toString();
                    if (IsNullOrEmpty.isNotNull(message))
                        MailThisForMe(message, "Configuration Values In Groovv");
                    if (popupWindowConfig.isShowing()) {
                        popupWindowConfig.dismiss();
                    }

                }
            });

            ImageView saveConfig = (ImageView) popupWindowConfig.getContentView().findViewById(R.id.saveConfig);
            saveConfig.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (popupWindowConfig.isShowing()) {
                        popupWindowConfig.dismiss();
                    }
                }
            });

            log.appendToLibraryLog("\n" + "get values again in case they have changed from load time", LogLevel.Debug_Log);

            Map<String, ?> keys = g.getsettings().getAll();
            StringBuilder sb = new StringBuilder();
            sb.append("Known configurations Shared Preferences" +
                    "\n" + "----------------------------------------------------------------------------------");

            for (Map.Entry<String, ?> entry : keys.entrySet()) {
                sb.append("\n" + "Configuration Key : " + entry.getKey() +
                        "\n" + "Configuration Value : " + entry.getValue().toString() +
                        "\n" + "----------------------------------------------------------------------------------");
            }

            // backkey handling for popup
            final View popUpWindowLaout = popupWindowConfig.getContentView();
            popUpWindowLaout.setFocusableInTouchMode(true);

            popUpWindowLaout.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (KeyEvent.KEYCODE_BACK == keyCode) { // match BACK key            {
                        return true; // indicate that we handled event, won't propagate it
                    }
                    return false; // when we don't handle other keys, propagate event further
                }
            });


            configOnTheScreen.setText(sb.toString());
            // popup configuration screen
            popupWindowConfig.showAtLocation(popupView, Gravity.CENTER, 40, 40);

        } catch (Exception e) {
            log.appendToLibraryLog("\n" + "Exception Generated in method showSavedConfiguration " + e.getMessage() + " Exception Type : " + e.getClass().toString());
        } finally {

        }
    }
}
