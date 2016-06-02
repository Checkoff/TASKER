package com.basdeo.AndroidBasicTemplateFullFunctionality;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.basdeo.AndroidBasicTemplateFullFunctionality.Handlers.PagerSlidingTabStrip;
import com.basdeo.AndroidBasicTemplateFullFunctionality.Handlers.SectionsPagerAdapter;
import com.basdeo.providercorelib.Globals.AllGlobalsToString;
import com.basdeo.providercorelib.Globals.ProviderGlobals;
import com.basdeo.providercorelib.Log.LogLevel;
import com.basdeo.providercorelib.Log.Logger;
import com.basdeo.providercorelib.commonuse.ConfigProvider;
import com.basdeo.providercorelib.commonuse.ImageHandler;
import com.basdeo.providercorelib.commonuse.IsNullOrEmpty;
import com.basdeo.providercorelib.commonuse.ScreenRotationHandler;

import java.util.Map;

/**
 * Created by eugen on 5/28/2016.
 */
public class TemplateMain extends AppCompatActivity {

    // *********************************************************************************************************************************************************************
    // *********************************************************************************************************************************************************************
    // *********************************************************************************************************************************************************************
    // START Declarations
    // *********************************************************************************************************************************************************************
    // *********************************************************************************************************************************************************************
    // *********************************************************************************************************************************************************************

    private static AllGlobalsToString ags = new AllGlobalsToString();
    private int backButtonCount = 0;
    private ScreenRotationHandler srh = new ScreenRotationHandler();
    private ProviderGlobals g = ProviderGlobals.getInstance();
    private boolean onMainScreen = true;
    private PopupWindow popWindowActive = new PopupWindow();
    private ProgressDialog progressBarLoad;
    private int progressBarStatusLoad = 0;
    private Handler progressBarHandlerLoad = new Handler();
    private static Logger log = new Logger();
    private AlertDialog alertActive;
    private ViewPager mViewPager;
    private ConfigProvider cp = new ConfigProvider();
    private int currentPage = 0;
    private SharedPreferences sp = null;
    private ImageHandler ih = new ImageHandler();


    // *********************************************************************************************************************************************************************
    // *********************************************************************************************************************************************************************
    // *********************************************************************************************************************************************************************
    // START Create / Main Menu / Setup Portion / CONTROL
    // *********************************************************************************************************************************************************************
    // *********************************************************************************************************************************************************************
    // *********************************************************************************************************************************************************************


    private PagerSlidingTabStrip tabs;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // this little guy is the controlling view.   It doesn't have program content in the layout
        setContentView(R.layout.activity_main);
        // setup our toolbar
        Toolbar mainToolbar = (Toolbar) findViewById(R.id.toolbar);
        // make sure we support actions
        setSupportActionBar(mainToolbar);

        try {
            loadUsUp("Loading.......", 1000);
            // Setup the viewPager
            final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
            SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(pagerAdapter);

            // Setup the Tabs
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
            // By using this method the tabs will be populated according to viewPager's count and
            // with the name from the pagerAdapter getPageTitle()
            tabLayout.addTab(tabLayout.newTab().setText("Dashboard"));
            tabLayout.addTab(tabLayout.newTab().setText("Settings"));
            tabLayout.addTab(tabLayout.newTab().setText("Task Management"));
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

            // This method ensures that tab selection events update the ViewPager and page changes update the selected tab.
            tabLayout.setupWithViewPager(viewPager);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            pager = (ViewPager) findViewById(R.id.view_pager);

            // create an object of adapter class and set it into the viewPager by calling the setAdapter () method.
             final SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(adapter);
            tabs.setViewPager(pager);

            // Call the TabLayout object on page change by adding the the addOnPageChangeListener() on viewPager.
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

            // Set the setOnTabSelectedListener on TabLayout object.when you select a tab onTabSelected() method will call and set the selected tab into the viewPager.
            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });


            final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
            pager.setPageMargin(pageMargin);
            pager.setOffscreenPageLimit(5);
            log.appendToLibraryLog("\n" + "Initial setup completed", LogLevel.Debug_Log);

            loadApplication();
        } catch (Exception ex) {
            log.appendToLibraryLog("\n" + "Exception In Initial setup " + ex.getMessage().toString(), LogLevel.Debug_Log);

            showLogSoFar();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
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

    private void loadApplication() {
        try {
            g.setapplicationContext(getApplicationContext());
            final ActionBar actionBar = getActionBar();
            g.setisDebugModeActive(true);

            if (IsNullOrEmpty.isNotNull(sp)) {
                g.setsettings(sp);
            } else
            {
                sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                g.setsettings(sp);

            }


            // * ###################################################################
            // * This Is In All Applications That Use The Library  (START)
            // comment out as required
            // * ###################################################################

            SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            g.seteditor(editor);
            g.setapplicationContext(getApplicationContext());

            // the following is the most important line and must be called in order to use USB devices
            //uc.getUSBControl(getApplicationContext(), getResources(), getAssets(), getResources().getXml(R.xml.known_devices), (UsbManager) getSystemService(Context.USB_SERVICE), PreferenceManager.getDefaultSharedPreferences(this));
            log.appendToLibraryLog("\n" + "loading app completed", LogLevel.Debug_Log);

            // * ###################################################################
            // * This Is In All Applications That Use The Library  (END)
            // * ###################################################################

            if (getIntent().getBooleanExtra("EXIT", false)) {
                finish();
            }

            /* First, get the Display from the WindowManager */
            g.setdisplay(((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay());
            // hide keyboard on launch
            this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            g.setconfig(getResources().getConfiguration());

        } catch (Exception e) {
            log.appendToLibraryLog("\n" + "Exception Generated in method OnCreate " + e.getMessage() + " Exception Type : " + e.getClass().toString());
        } finally {

        }
    }

    private void evaluateBackPress() {
        //  On Main Screen and backbutton pushed more than once
        if (backButtonCount > 0 && onMainScreen) {
            try {
                log.appendToLibraryLog("\n" + "Back button pressed more than once and on main screen");
                Intent intent = new Intent(this, TemplateMain.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
                finish();
                System.gc();
            } catch (Exception e) {
                log.appendToLibraryLog("\n" + "Exception Generated in method evaluateBackPress " + e.getMessage() + " Exception Type : " + e.getClass().toString());

            }
            // Not on main screen and back button pushed more than once
        } else {
            if (backButtonCount > 0) {
                backButtonCount = 0;
                log.appendToLibraryLog("\n" + "Back button pressed more than once and not on main screen");
                // ignore this event as the user is btton happy
            } else {
                // On main screen and first time to push back button
                if (backButtonCount == 0 && onMainScreen) {
                    log.appendToLibraryLog("\n" + "Back button pressed first time on main screen");
                    Toast.makeText(getApplicationContext(), "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
                    backButtonCount++;
                } else
                // Not on main screen and first time to push back button
                {
                    if (backButtonCount == 0) {
                        try {
                            log.appendToLibraryLog("\n" + "Back button pressed first time not on main screen. Trying to close popup.");
                            popWindowActive.dismiss();
                            backButtonCount = 0;
                            onMainScreen = true;
                        } catch (Exception e) {
                            try {
                                log.appendToLibraryLog("\n" + "Back button pressed first time not on main screen. Trying to close dialog.");
                                alertActive.dismiss();
                                backButtonCount = 0;
                                onMainScreen = true;
                            } catch (Exception e1) {
                                log.appendToLibraryLog("\n" + "Exception Generated in method evaluateBackPress " + e1.getMessage() + " Exception Type : " + e1.getClass().toString());

                            }

                        }
//                            autoDialControlButtonHandler();
                    }
                }
            }
        }
    }

    private void loadUsUp(String messsage, final int timeToShow) {
        try {

            final ProgressDialog dialog = ProgressDialog.show(TemplateMain.this, "Please Wait", messsage, true);
            Thread t = new Thread(new Runnable() {
                public void run() {
                    try {
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

    private void lockScreenRotation() {
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

    private void allowScreenRotation() {
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
