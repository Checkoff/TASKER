package com.basdeo.providercorelib.broadcast;

import java.lang.String; /**
 * Created by Eugene on 5/24/16
 */
public class ProviderEventTypes {
    public static final String LOG_UPDATE_WRITE =                               "com.basdeo.providercorelib.broadcast.WriteToDeviceLogReceiver.UPDATE_WRITE_LOG";
    public static final String LOG_UPDATE_READ =                                "com.basdeo.providercorelib.broadcast.ReadFromDeviceLogReceiver.UPDATE_READ_LOG";
    public static final String LOG_UPDATE_COMBINED_READANDWRITE =               "com.basdeo.providercorelib.broadcast.CombinedReadFromAndWriteToDeviceLogReceiver.UPDATE_COMBINED_LOG";
    public static final String PROGRESS_BAR_SET_FIXED_VALUE =                   "com.basdeo.providercorelib.broadcast.SetProgressReceiver.SET_PROGRESS";
    public static final String PROGRESS_BAR_SET_MAX =                           "com.basdeo.providercorelib.broadcast.SetProgressBarMaxReceiver.PROGRESS_BAR_MAX";
    public static final String PROGRESS_BAR_INCREMENT =                         "com.basdeo.providercorelib.broadcast.IncrementProgressReceiver.INCREMENT_PROGRESS";
    public static final String HID_ATTACHED =                                   "com.basdeo.providercorelib.broadcast.HIDAttachedReceiver.HUMAN_INTERFACE_DEVICE_ATTACHED";
 }
