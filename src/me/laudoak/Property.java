package me.laudoak;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by laudoak on 16/12/8.
 */
public class Property
{
    private static final String TAG = Property.class.getSimpleName();

    private static String host;
    private static String port;
    public static String dbname;
    public static String driverclassname;
    private static String username;
    private static String password;

    public static String url;

    public static void load()
    {
        try
        {
            Properties properties = new Properties();
            InputStream is = Property.class.getResourceAsStream("config.properties");
            properties.load(is);
            host = properties.getProperty("host");
            port = properties.getProperty("port").trim();
            dbname = properties.getProperty("dbname").trim();
            driverclassname = properties.getProperty("driver-class-name").trim();
            username = properties.getProperty("username").trim();
            password = properties.getProperty("password").trim();

            url = "jdbc:mysql://" + host + ":" + port + "/" + dbname + "?user=" + username + "&password=" + password + "&autoReconnect=true&useUnicode=true&characterEncoding=UTF8&useSSL=false";

            Log.info(TAG, "url:%s", url);

        } catch (IOException e)
        {
            Log.error(TAG, "load error,cause:%s", e.getMessage());
            e.printStackTrace();
        }
    }
}
