package com.basdeo.providercorelib.commonuse;

import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.view.Display;
import android.view.Surface;

import com.basdeo.providercorelib.Log.Logger;

/**
 * Created by Eugene on 5/24/16.
 */
public class ScreenRotationHandler {

    private static Logger log = new Logger();

    public int getValueToLockScreenOrientationToCurrent(Display display)
    {
        int lock = 0;

        try {
            int rotation = display.getRotation();

            Point size = new Point();
            display.getSize(size);

            lock = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

            if (rotation == Surface.ROTATION_0
                    || rotation == Surface.ROTATION_180) {
                // if rotation is 0 or 180 and width is greater than height, we have
                // a tablet
                if (size.x > size.y) {
                    if (rotation == Surface.ROTATION_0) {
                        lock = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    } else {
                        lock = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
                    }
                } else {
                    // we have a phone
                    if (rotation == Surface.ROTATION_0) {
                        lock = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    } else {
                        lock = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
                    }
                }
            } else {
                // if rotation is 90 or 270 and width is greater than height, we
                // have a phone
                if (size.x > size.y) {
                    if (rotation == Surface.ROTATION_90) {
                        lock = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    } else {
                        lock = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
                    }
                } else {
                    // we have a tablet
                    if (rotation == Surface.ROTATION_90) {
                        lock = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
                    } else {
                        lock = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    }
                }
            }
        } catch (Exception e) {
            log.appendToLibraryLog("\n" + "Exception Generated in method lockScreenOrientationToCurrent " + e.getMessage() + " Exception Type : " + e.getClass().toString());
        } finally {

        }
        return lock;
    }

    public String getDescriptionOfCurrentScreenRotation(Display display)
    {
        String currentRotationDescription = "";

        try {
            int rotation = display.getRotation();

            Point size = new Point();
            display.getSize(size);

            if (rotation == Surface.ROTATION_0
                    || rotation == Surface.ROTATION_180) {
                // if rotation is 0 or 180 and width is greater than height, we have
                // a tablet
                if (size.x > size.y) {
                    if (rotation == Surface.ROTATION_0) {
                        currentRotationDescription = "LANDSCAPE";
                    } else {
                        currentRotationDescription = "REVERSE_LANDSCAPE";
                    }
                } else {
                    // we have a phone
                    if (rotation == Surface.ROTATION_0) {
                        currentRotationDescription = "PORTRAIT";
                    } else {
                        currentRotationDescription = "REVERSE_PORTRAIT";
                    }
                }
            } else {
                // if rotation is 90 or 270 and width is greater than height, we
                // have a phone
                if (size.x > size.y) {
                    if (rotation == Surface.ROTATION_90) {
                        currentRotationDescription = "LANDSCAPE";
                    } else {
                        currentRotationDescription = "REVERSE_LANDSCAPE";
                    }
                } else {
                    // we have a tablet
                    if (rotation == Surface.ROTATION_90) {
                        currentRotationDescription = "REVERSE_PORTRAIT";
                    } else {
                        currentRotationDescription = "PORTRAIT";
                    }
                }
            }
        } catch (Exception e) {
            log.appendToLibraryLog("\n" + "Exception Generated in method lockScreenOrientationToCurrent " + e.getMessage() + " Exception Type : " + e.getClass().toString());
        } finally {

        }
        return currentRotationDescription;
    }
}
