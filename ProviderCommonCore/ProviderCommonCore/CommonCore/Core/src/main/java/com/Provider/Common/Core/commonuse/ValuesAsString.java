package com.Provider.Common.Core.commonuse;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Eugene on 5/24/16.
 */
public class ValuesAsString {
    
    
    //    * This method returns true if the collection is null or is empty.
    //    * @param collection
    public static String valueToString( Collection<?> collection ){
        try {
            if( collection == null || collection.isEmpty() ){
                return "";
            }
        } catch (NullPointerException e)
        {
            return "";
        }
        return "";
    }

    public static String valueToStringHashmapString( HashMap<String, String> map ){
        try {
            if (null == map || map.isEmpty()) {
                return "";
            }
        } catch (NullPointerException e)
        {
            return "";
        }

        Iterator<String> mapIterator = map.values().iterator();
        mapIterator = map.values().iterator();

        StringBuilder sb = new StringBuilder();
        int y = 0;
        while (mapIterator.hasNext()) {
            String typePassed = mapIterator.next();

            sb.append("\n");
            sb.append("-----------------------------------------------------------");
            sb.append("Entry In Map : " + y);
            sb.append("\n");
            sb.append("String : " + typePassed);

            y++;
        }

        return sb.toString();
    }

    public static String valueToString( Object object ){
        try {
            if( object == null ){
                return "";
            }
        } catch (NullPointerException e)
        {
            return "";
        }
        return object.toString();
    }

    public static String valueToString( Object[] array ){
        try {
            if( array == null || array.length == 0 ){
                return "";
            }
        } catch (NullPointerException e)
        {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (int x = 0; x<array.length; x++)
        {
            sb.append("\n");
            sb.append("-----------------------------------------------------------");
            sb.append("\n");
            sb.append("Entry : " + x + " ***** Value : " + array[x].toString());
        }

        return sb.toString();
    }

    public static String valueToString( String string ){
        try {
            if( string == null || string.trim().length() == 0 ){
                return "";
            }
        } catch (NullPointerException e)
        {
            return "";
        }
        return string;
    }
}
