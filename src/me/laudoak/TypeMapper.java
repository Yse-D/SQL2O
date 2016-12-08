package me.laudoak;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by laudoak on 16/12/8.
 */
public class TypeMapper
{

    private static Map<String, String> types = new HashMap<>();

    public static void load()
    {
        try
        {
            Properties properties = new Properties();
            InputStream is = Property.class.getResourceAsStream("type.properties");
            properties.load(is);

            String sql_bigint = properties.getProperty("bigint") == null ? "unknown" : properties.getProperty("bigint").trim();
            String sql_int = properties.getProperty("int") == null ? "unknown" : properties.getProperty("int").trim();
            String sql_smallint = properties.getProperty("smallint") == null ? "unknown" : properties.getProperty("smallint").trim();
            String sql_tinyint = properties.getProperty("tinyint") == null ? "unknown" : properties.getProperty("tinyint").trim();
            String sql_varchar = properties.getProperty("varchar") == null ? "unknown" : properties.getProperty("varchar").trim();
            String sql_datetime = properties.getProperty("datetime") == null ? "unknown" : properties.getProperty("datetime").trim();

            types.put("bigint", sql_bigint);
            types.put("int", sql_int);
            types.put("smallint", sql_smallint);
            types.put("tinyint", sql_tinyint);
            types.put("varchar", sql_varchar);
            types.put("datetime", sql_datetime);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static String JTYPE(String sqlType)
    {
        if (sqlType == null || sqlType.equals(""))
        {
            return "unknown";
        }
        return types.get(sqlType.toLowerCase()) == null ? "unknown" : types.get(sqlType.toLowerCase());
    }
}
