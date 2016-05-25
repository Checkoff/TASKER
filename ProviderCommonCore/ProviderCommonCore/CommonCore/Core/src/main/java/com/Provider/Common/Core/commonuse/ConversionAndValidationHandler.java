package com.Provider.Common.Core.commonuse;

import com.Provider.Common.Core.Log.LogLevel;
import com.Provider.Common.Core.Log.Logger;

/**
 * Created by Eugene on 5/24/16.
 */
public class ConversionAndValidationHandler {

    private ConversionAndValidationHandler() {}
    private static Logger log = new Logger();
    private static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();
    private static final String HEX_DIGITS = "0123456789ABCDEF";
    private static char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    private static ConversionAndValidationHandler conversionAndValidationHandler = null;
    private static final int sizeOfIntInHalfBytes = 8;
    private static final int numberOfBitsInAHalfByte = 4;
    private static final int halfByte = 0x0F;
    private static final char[] hexDigits = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };

    public static final byte VISA = 0;
    public static final byte MASTERCARD = 1;
    public static final byte AMEX = 2;
    public static final byte DINERS_CLUB = 3;
    public static final byte CARTE_BLANCHE = 4;
    public static final byte DISCOVER = 5;
    public static final byte ENROUTE = 6;
    public static final byte JCB = 7;

   public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len/2];
    try {
        for(int i = 0; i < len; i+=2){
            data[i/2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
        }

    } catch (Exception e) {
        log.appendToLibraryLog("\n" + "Exception In hexStringToByteArray : " + e.getMessage() + " Exception Type : " + e.getClass().toString(), LogLevel.Critical_Log);

    }
        return data;
    }

    public static String byteArrayToHexString(byte[] bytes) {
        char[] hexChars = new char[bytes.length*2];
        int v;
        try {

            for (int j = 0; j < bytes.length; j++) {
                v = bytes[j] & 0xFF;
                hexChars[j * 2] = hexArray[v >>> 4];
                hexChars[j * 2 + 1] = hexArray[v & 0x0F];
            }
        } catch (Exception e) {
            log.appendToLibraryLog("\n" + "Exception In byteArrayToHexString : " + e.getMessage() + " Exception Type : " + e.getClass().toString(), LogLevel.Critical_Log);

        }


        return new String(hexChars);
    }

    public static String hexToASCII(String hex){
        StringBuilder sb = new StringBuilder();
        try {

            if (hex.length() % 2 != 0) {
                log.appendToLibraryLog("\n" + "hexToAscii requires EVEN number of char", LogLevel.Critical_Log);
                return null;
            }
            //Convert Hex 0232343536AB into two characters stream.
            for (int i = 0; i < hex.length() - 1; i += 2) {
               /*
                * Grab the hex in pairs
                */
                String output = hex.substring(i, (i + 2));
              /*
               * Convert Hex to Decimal
               */
                int decimal = Integer.parseInt(output, 16);
                sb.append((char) decimal);
            }
        } catch (Exception e)
        {
            log.appendToLibraryLog("\n" + "Exception In hexToASCII : " + e.getMessage() + " Exception Type : " + e.getClass().toString(), LogLevel.Critical_Log);

        }

        return sb.toString();
    }


    public static String asciiToHex(String ascii){
        StringBuilder hex = new StringBuilder();

        try {

            for (int i = 0; i < ascii.length(); i++) {
                hex.append(Integer.toHexString(ascii.charAt(i)));
            }

        } catch (Exception e)
        {
            log.appendToLibraryLog("\n" + "Exception In asciiToHex : " + e.getMessage() + " Exception Type : " + e.getClass().toString(), LogLevel.Critical_Log);

        }

        return hex.toString();
    }

    public static String stringToHex(String input)
    {
         return asHex(input.getBytes());
    }

    public static String hexToString(String txtInHex)
    {
        byte [] txtInByte = new byte [txtInHex.length() / 2];
        int j = 0;
        try {
            for (int i = 0; i < txtInHex.length(); i += 2) {
                txtInByte[j++] = Byte.parseByte(txtInHex.substring(i, i + 2), 16);
            }
        } catch (Exception e)
        {
            log.appendToLibraryLog("\n" + "Exception In hexToString : " + e.getMessage() + " Exception Type : " + e.getClass().toString(), LogLevel.Critical_Log);

        }

        return new String(txtInByte);
    }

    private static String asHex(byte[] buf)
    {
        char[] chars = new char[2 * buf.length];
        try {
            for (int i = 0; i < buf.length; ++i) {
                chars[2 * i] = HEX_CHARS[(buf[i] & 0xF0) >>> 4];
                chars[2 * i + 1] = HEX_CHARS[buf[i] & 0x0F];
            }
        } catch (Exception e) {
            log.appendToLibraryLog("\n" + "Exception In asHex : " + e.getMessage() + " Exception Type : " + e.getClass().toString(), LogLevel.Critical_Log);

        }

    return new String(chars);
    }

    public static String decToHex(int dec) {
        StringBuilder hexBuilder = new StringBuilder(sizeOfIntInHalfBytes);
        hexBuilder.setLength(sizeOfIntInHalfBytes);

        try {
            for (int i = sizeOfIntInHalfBytes - 1; i >= 0; --i) {
                int j = dec & halfByte;
                hexBuilder.setCharAt(i, hexDigits[j]);
                dec >>= numberOfBitsInAHalfByte;
            }
        } catch (Exception e) {
            log.appendToLibraryLog("\n" + "Exception In asHex : " + e.getMessage() + " Exception Type : " + e.getClass().toString(), LogLevel.Critical_Log);

        }
        return hexBuilder.toString();
    }

    public static String hexToDec(String hex) {
        char[] sources = hex.toCharArray();
        int dec = 0;
        for (int i = 0; i < sources.length; i++) {
            int digit = HEX_DIGITS.indexOf(Character.toUpperCase(sources[i]));
            dec += digit * Math.pow(16, (sources.length - (i + 1)));
        }
        return String.valueOf(dec);
    }

    public static char[] byteToChar(byte[] bytes) {

        char[] convertedChar = new char[bytes.length];

        for(int i=0;i < bytes.length;i++) {
            convertedChar[i] = (char) bytes[i];
        }
        return convertedChar;
    }

    public static byte[] charToByte(char[] chars) {

        byte[] convertedByte = new byte[chars.length];

        for(int i=0;i < chars.length;i++) {
            convertedByte[i] = (byte) chars[i];
        }
        return convertedByte;
    }

    public static boolean Mod10Check(String creditCardNumber)
    {
        //// check whether input string is null or empty
        if (IsNullOrEmpty.isNotNull(creditCardNumber)) {
            int sum = 0;
            int length = creditCardNumber.length();
            for (int i = 0; i < creditCardNumber.length(); i++) {
                if (0 == (i % 2)) {
                    sum += creditCardNumber.charAt(length - i - 1) - '0';
                } else {
                    sum += sumDigits((creditCardNumber.charAt(length - i - 1) - '0') * 2);
                }
            }
            return 0 == (sum % 10);
        } else
        {
            return false;
        }

    }

    private static int sumDigits(int i) {
        return (i % 10) + (i / 10);
    }


    public static boolean validateCreditCardType(final String credCardNumber, final byte type) {

        String creditCard = credCardNumber.trim();

        boolean isValidType = false;

        try {
            switch (type) {
                case VISA:
                    // VISA credit cards has length 13 - 15
                    // VISA credit cards starts with prefix 4
                    if (creditCard.length() >= 13 && creditCard.length() <= 16
                            && creditCard.startsWith("4")) {
                        isValidType = true;
                    }
                    break;
                case MASTERCARD:
                    // MASTERCARD has length 16
                    // MASTER card starts with 51, 52, 53, 54 or 55
                    if (creditCard.length() == 16) {
                        int prefix = Integer.parseInt(creditCard.substring(0, 2));
                        if (prefix >= 51 && prefix <= 55) {
                            isValidType = true;
                        }
                    }
                    break;
                case AMEX:
                    // AMEX has length 15
                    // AMEX has prefix 34 or 37
                    if (creditCard.length() == 15
                            && (creditCard.startsWith("34") || creditCard
                            .startsWith("37"))) {
                        isValidType = true;
                    }
                    break;
                case DINERS_CLUB:
                case CARTE_BLANCHE:
                    // DINERSCLUB or CARTEBLANCHE has length 14
                    // DINERSCLUB or CARTEBLANCHE has prefix 300, 301, 302, 303, 304,
                    // 305 36 or 38
                    if (creditCard.length() == 14) {
                        int prefix = Integer.parseInt(creditCard.substring(0, 3));
                        if ((prefix >= 300 && prefix <= 305)
                                || creditCard.startsWith("36")
                                || creditCard.startsWith("38")) {
                            isValidType = true;
                        }
                    }
                    break;
                case DISCOVER:
                    // DISCOVER card has length of 16
                    // DISCOVER card starts with 6011
                    if (creditCard.length() == 16 && creditCard.startsWith("6011")) {
                        isValidType = true;
                    }
                    break;
                case ENROUTE:
                    // ENROUTE card has length of 16
                    // ENROUTE card starts with 2014 or 2149
                    if (creditCard.length() == 16
                            && (creditCard.startsWith("2014") || creditCard
                            .startsWith("2149"))) {
                        isValidType = true;
                    }
                    break;
                case JCB:
                    // JCB card has length of 16 or 15
                    // JCB card with length 16 starts with 3
                    // JCB card with length 15 starts with 2131 or 1800
                    if ((creditCard.length() == 16 && creditCard.startsWith("3"))
                            || (creditCard.length() == 15 && (creditCard
                            .startsWith("2131") || creditCard
                            .startsWith("1800")))) {
                        isValidType = true;
                    }
                    break;

            }

        } catch (Exception e)
        {
            log.appendToLibraryLog("\n" + "Exception In validateCreditCardType : " + e.getMessage() + " Exception Type : " + e.getClass().toString(), LogLevel.Critical_Log);

        }
        return isValidType;
    }

}
