package com.Provider.Common.Core.Globals;



        import android.content.Context;
        import android.content.SharedPreferences;
        import android.content.res.AssetManager;
        import android.content.res.Resources;
        import android.content.res.XmlResourceParser;
        import android.widget.ProgressBar;
        import android.widget.TextView;
        import android.widget.Button;
        import com.Provider.Common.Core.files.AssetReader;
        import com.Provider.Common.Core.files.FileReader;
        import com.Provider.Common.Core.files.FileWriter;
        import com.Provider.Common.Core.files.XMLResourceReader;

        import java.util.HashMap;

/**
 * Created by Eugene on 5/24/16.
 */
public class ProviderGlobals {
    private static ProviderGlobals instance;

    // Global variable

    // Restrict the constructor from being instantiated
    private ProviderGlobals(){}
    private int progressCurrentValue = 0;
    private int progressMaxValue = 100;
    private int progressMultiplier = 1;
    private ProgressBar progressBarObject;
    private boolean ExtraDetailTrace = false;
    private Resources resources;
    private XmlResourceParser xrp;
    private AssetManager assetManager;
    private AssetReader assetReader;
    private StringBuilder applicationLog = new StringBuilder();
    private boolean isDebugModeActive = false;
    private FileReader fileReaderHelper;
    private FileWriter fileWriterHelper;
    private XMLResourceReader xmlResourceReader;
    private TextView LogListing;
    private SharedPreferences.Editor editor;
    private HashMap<String, String> configurationValues;
    private SharedPreferences settings;
    private Context applicationContext;


    private String userName;
    public void setuserName(String d){
        this.userName=d;
    }
    public String getuserName(){
        return this.userName;
    }

    public void setapplicationContext (Context d) {
        this.applicationContext = d;
    }

    public Context getapplicationContext () {
        return this.applicationContext ;
    }

    public void setsettings(SharedPreferences d) {
        this.settings = d;
    }

    public SharedPreferences getsettings() {
        return this.settings;
    }

    public void setconfigurationValues (HashMap<String, String> d) {
        this.configurationValues = d;
    }

    public HashMap<String, String> getconfigurationValues () {
        return this.configurationValues ;
    }

    public void seteditor (SharedPreferences.Editor d) {
        this.editor = d;
    }

    public SharedPreferences.Editor geteditor () {
        return this.editor ;
    }

    public void setisDebugModeActive (boolean d) {
        this.isDebugModeActive = d;
    }

    public boolean getisDebugModeActive () {
        return this.isDebugModeActive ;
    }

    public void setapplicationLog (StringBuilder d) {
        this.applicationLog = d;
    }

    public StringBuilder getapplicationLog () {
        return this.applicationLog ;
    }

    public void setfileReaderHelper (FileReader d) {
        this.fileReaderHelper = d;
    }

    public FileReader getfileReaderHelper () {
        return this.fileReaderHelper ;
    }

    public void setfileWriterHelper (FileWriter d) {
        this.fileWriterHelper = d;
    }

    public FileWriter getfileWriterHelper () {
        return this.fileWriterHelper ;
    }


    public void setExtraDetailTrace (boolean d) {
        this.ExtraDetailTrace = d;
    }

    public boolean getExtraDetailTrace () {
        return this.ExtraDetailTrace ;
    }

    public void setprogressBarObject (ProgressBar d) {
        this.progressBarObject = d;
    }

    public ProgressBar getprogressBarObject () {
        return this.progressBarObject ;
    }

    public void setprogressMultiplier (int d) {
        this.progressMultiplier = d;
    }

    public int getprogressMultiplier () {
        return this.progressMultiplier ;
    }

    public void setprogressMaxValue (int d) {
        this.progressMaxValue = d;
    }

    public int getprogressMaxValue () {
        return this.progressMaxValue ;
    }


    public void setprogressCurrentValue (int d) {
        this.progressCurrentValue = d;
    }

    public int getprogressCurrentValue () {
        return this.progressCurrentValue ;
    }

    public void setresources (Resources d) {
        this.resources = d;
    }

    public void setAssetManager(AssetManager d) {
        this.assetManager = d;
    }

    public AssetManager getAssetManager() {
        return this.assetManager;
    }

    public void setLogListing(TextView d) {
        this.LogListing = d;
    }

    public TextView getLogListing() {
        return this.LogListing;
    }


    public static synchronized ProviderGlobals getInstance(){
        if(instance==null){
            instance=new ProviderGlobals();
        }
        return instance;
    }
}
