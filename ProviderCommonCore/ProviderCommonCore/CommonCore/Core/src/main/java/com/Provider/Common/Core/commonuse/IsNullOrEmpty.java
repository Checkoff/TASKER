package com.Provider.Common.Core.commonuse;

import java.util.Collection;
import java.util.Map;

 // * Created by Eugene on 5/24/16
 // *********************************************************************************************************************************************************************
 // *********************************************************************************************************************************************************************
 // *********************************************************************************************************************************************************************

public class IsNullOrEmpty {

    public static <T> T replaceIfNull(T objectToCheck, T defaultValue) {
        return objectToCheck == null ? defaultValue : objectToCheck;
    }

    public static boolean isAnObjectNull(Object object) {
        try
        {
            if (object.equals(null)) {
                return true;
            }
        } catch ( NullPointerException e )
        {
            return true;
        } catch ( Exception e )
        {
            return true;
        }
        return false;
    }

    public static boolean isNotNull( Collection<?> collection ){
        try {
            if( collection == null || collection.isEmpty() ){
                return false;
            }
        } catch (NullPointerException e)
        {
            return false;
        }
        return true;
    }

    public static boolean isNotNull( Map<?, ?> map ){
        try {
            if (null == map || map.isEmpty()) {
                return false;
            }
        } catch (NullPointerException e)
        {
            return false;
        }
        return true;
    }

    public static boolean isNotNull( Object object ){
        try {
            if( object == null ){
                return false;
            }
        } catch (NullPointerException e)
        {
            return false;
        }
        return true;
    }

    public static boolean isNotNull( Object[] array ){
        try {
            if( array == null || array.length == 0 ){
                return false;
            }
        } catch (NullPointerException e)
        {
            return false;
        }
        return true;
    }

    public static boolean isNotNull( String string ){
        try {
            if( string == null || string.trim().length() == 0 ){
                return false;
            }
        } catch (NullPointerException e)
        {
            return false;
        }
        return true;
    }

}
