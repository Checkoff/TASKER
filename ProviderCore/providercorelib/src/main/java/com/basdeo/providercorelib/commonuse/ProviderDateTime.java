package com.basdeo.providercorelib.commonuse;

import java.util.Date;

/**
 * Created by Eugene on 5/10/2014.
 */
public class ProviderDateTime {

    private ProviderDateTime() {}

    public static long getMillisecondsElapsed(Date from, Date to)
    {

        long elapsed = to.getTime() - from.getTime();
        return elapsed;
    }

    public static long getSecondsElapsed(Date from, Date to)
    {

        long elapsed = to.getTime() - from.getTime();
        long seconds = elapsed / 1000;
        return seconds;
    }

    public static long getMinutesElapsed(Date from, Date to)
    {

        long elapsed = to.getTime() - from.getTime();
        long seconds = elapsed / 1000;
        long minutes = seconds / 60;
        return minutes;
    }

    public static long getHoursElapsed(Date from, Date to)
    {

        long elapsed = to.getTime() - from.getTime();
        long seconds = elapsed / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        return hours;
    }

    public static long getDaysElapsed(Date from, Date to)
    {

        long elapsed = to.getTime() - from.getTime();
        long seconds = elapsed / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        return days;
    }

    private static int mod(int x, int y)
    {
        int result = x % y;
        return result < 0? result + y : result;
    }
}
