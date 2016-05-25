package com.Provider.Common.Core.broadcast;

import java.lang.String; /**
 * Created by Eugene on 5/24/16
 */
public class ProviderEventTypes {
    public static final String LOG_UPDATE_WRITE =                               "com.Provider.Common.Core.broadcast.WriteToDeviceLogReceiver.UPDATE_WRITE_LOG";
    public static final String LOG_UPDATE_READ =                                "com.Provider.Common.Core.broadcast.ReadFromDeviceLogReceiver.UPDATE_READ_LOG";
    public static final String LOG_UPDATE_COMBINED_READANDWRITE =               "com.Provider.Common.Core.broadcast.CombinedReadFromAndWriteToDeviceLogReceiver.UPDATE_COMBINED_LOG";
    public static final String PROGRESS_BAR_SET_FIXED_VALUE =                   "com.Provider.Common.Core.broadcast.SetProgressReceiver.SET_PROGRESS";
    public static final String PROGRESS_BAR_SET_MAX =                           "com.Provider.Common.Core.broadcast.SetProgressBarMaxReceiver.PROGRESS_BAR_MAX";
    public static final String PROGRESS_BAR_INCREMENT =                         "com.Provider.Common.Core.broadcast.IncrementProgressReceiver.INCREMENT_PROGRESS";
    public static final String HID_ATTACHED =                                   "com.Provider.Common.Core.broadcast.HIDAttachedReceiver.HUMAN_INTERFACE_DEVICE_ATTACHED";
 }
