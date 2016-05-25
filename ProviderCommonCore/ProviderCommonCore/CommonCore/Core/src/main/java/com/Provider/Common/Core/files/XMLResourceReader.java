package com.Provider.Common.Core.files;

import android.content.SharedPreferences;

import com.Provider.Common.Core.Globals.ProviderGlobals;
import com.Provider.Common.Core.Log.LogLevel;
import com.Provider.Common.Core.Log.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Eugene on 5/24/16.
 */
public class XMLResourceReader {

    private static Logger log = new Logger();
    private AssetReader ar = new AssetReader();
    private ProviderGlobals g = ProviderGlobals.getInstance();


    public void XMLResourceReader() {}

    public String getKnownDevices(InputStream inputStreamKnownDevices) {

        StringBuilder sb = new StringBuilder();

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputStreamKnownDevices);

            NodeList nList = doc.getElementsByTagName("knowndevices");

            if (nList.getLength() > 0)
                sb.append("Known Device File Located.  Contents Below");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                sb.append("\n" + "Current Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    sb.append("\n" + "Name :" + eElement.getAttribute("name"));
                    sb.append("\n" + "Hex Product ID :" + eElement.getElementsByTagName("hex").item(temp).getTextContent());
                    sb.append("\n" + "Decimal Product ID :" + eElement.getElementsByTagName("decimal").item(temp).getTextContent());
                    sb.append("\n" + "Product Initialization File In Assets :" + eElement.getElementsByTagName("initializationFile").item(temp).getTextContent());
                }
            }

        } catch (Exception e) {
            log.appendToLibraryLog("\n" + "Exception In getKnownDevices : " + e.getMessage() + " Exception Type : " + e.getClass().toString(), LogLevel.Critical_Log);
        } finally {
            // close the file.
        }

        return sb.toString();
    }

    public HashMap<String, String> readSetupDataFromSharedData(String deviceToGetValuesFor, SharedPreferences settings)
    {
        final HashMap<String, String> sharedResourceData = new HashMap<String, String>();

        try {
            String[] resourceKeyNames = ar.readTextAssetFileLines("shared_resource_value_keys.txt", g.getAssetManager());

            int keyCounter = 0;
            for (keyCounter=0; keyCounter<resourceKeyNames.length; keyCounter++)
            {
                String keyName = resourceKeyNames[keyCounter].toString().replace("<device>", deviceToGetValuesFor);
                String value = settings.getString(keyName,"");

                if (keyName.length()>0 && value.length()>0)
                    sharedResourceData.put(keyName,value);
            }


        } catch(Exception e)
        {
            log.appendToLibraryLog("\n" + "Exception Generated in method readSetupDataFromSharedData " + e.getMessage() + " Exception Type : " + e.getClass().toString(), LogLevel.Critical_Log);
        } finally {

        }

        return sharedResourceData;
    }

    public String getConfigValueFromXMLString(String XMLString, String key)
    {
         String requestedValue = "";
         log.appendToLibraryLog("\n" + "Entering getConfigValueFromXMLString ", LogLevel.Debug_Log);

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();

            parser.setInput(new StringReader(XMLString));

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    // at start of a tag: START_TAG  - not really great usage since we are only using one event type :-)
                    case XmlPullParser.START_TAG:
                        // get tag name
                        String keyName = parser.getName();
                        if ("string".equals(keyName.toLowerCase()) || "int".equals(keyName.toLowerCase()) || "boolean".equals(keyName.toLowerCase())) {
                            log.appendToLibraryLog("\n" + "Got Start Tag Name : " + keyName, LogLevel.Debug_Log);
                            // if <>, get attribute: 'id'
                            if (parser.getAttributeCount() > 0) {
                                String attributeName = parser.getAttributeValue(null, "name");
                                log.appendToLibraryLog("\n" + "Got Attribute Name : " + attributeName, LogLevel.Debug_Log);
                                if (attributeName.equals(key)) {
                                    requestedValue = parser.nextText();
                                    log.appendToLibraryLog("\n" + "Key value found key : " + attributeName + " value : " + requestedValue, LogLevel.Debug_Log);
                                } else
                                {
                                    log.appendToLibraryLog("\n" + "Failed Compare Of : " + attributeName + " To : " + key, LogLevel.Debug_Log);
                                }
                            }
                        } else
                        if (keyName.equals(key))
                        {
                            requestedValue = parser.nextText();
                        }
                }
                eventType = parser.next();
            }
        }catch(Exception e)
        {
            log.appendToLibraryLog("\n" + "Exception Generated in method getConfigValueFromXMLString " + e.getMessage() + " Exception Type : " + e.getClass().toString(), LogLevel.Critical_Log);
        } finally {

        }

        return requestedValue;
    }

    private int indexOfAttributeWithName(XmlPullParser parser, String name) {
        for (int i = 0; i < parser.getAttributeCount(); i++) {
            if (parser.getAttributeName(i).equals(name)) {
                return i;
            }
        }
        return -1;
    }

}
