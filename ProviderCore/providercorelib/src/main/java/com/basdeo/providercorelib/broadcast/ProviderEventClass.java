package com.basdeo.providercorelib.broadcast;

/**
 * Created by Eugene on 5/24/16.
 */
public class ProviderEventClass {
    public static final String LOG_UPDATE_WRITE = "WriteToDeviceLogReceiver";
    public static final String LOG_UPDATE_READ = "ReadFromDeviceLogReceiver";
    public static final String LOG_UPDATE_COMBINED_READANDWRITE = "CombinedReadFromAndWriteToDeviceLogReceiver";
    public static final String PROGRESS_BAR_SET_FIXED_VALUE = "SetProgressReceiver";
    public static final String PROGRESS_BAR_SET_MAX = "SetProgressBarMaxReceiver";
    public static final String PROGRESS_BAR_INCREMENT = "IncrementProgressReceiver";
    public static final String HID_ATTACHED = "HIDAttachedReceiver";
 }
