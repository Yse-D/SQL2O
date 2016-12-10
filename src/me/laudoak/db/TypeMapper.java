package me.laudoak.db;

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

    private Map<String, String> types = new HashMap<>();

    private Properties properties;
    private InputStream is;

    public TypeMapper defaultMapper()
    {
        try
        {
            properties = new Properties();
            is = DBStarter.class.getResourceAsStream("../type.properties");
            properties.load(is);
            load();

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return this;
    }

    public TypeMapper getPropertiesAsStream(InputStream is)
    {
        try
        {
            properties = new Properties();
            properties.load(is);
            load();

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return this;
    }

    private void load()
    {
        fillMap("INT");
        fillMap("TINYINT");
        fillMap("SMALLINT");
        fillMap("MEDIUMINT");
        fillMap("BIGINT");
        fillMap("FLOAT");
        fillMap("DOUBLE");
        fillMap("DECIMAL");

        fillMap("DATE");
        fillMap("DATETIME");
        fillMap("TIMESTAMP");
        fillMap("TIMESTAMP");
        fillMap("TIME");
        fillMap("YEAR");

        fillMap("CHAR");
        fillMap("VARCHAR");
        fillMap("TEXT");
        fillMap("BLOB");
        fillMap("TINYBLOB");
        fillMap("TINYTEXT");
        fillMap("MEDIUMBLOB");
        fillMap("MEDIUMBLOB");
        fillMap("MEDIUMTEXT");
        fillMap("LONGBLOB");
        fillMap("LONGTEXT");
        fillMap("ENUM");

    }

    private void fillMap(String sqlType)
    {
        types.put(sqlType, properties.getProperty(sqlType) == null ? "unknown" : properties.getProperty(sqlType).trim());
    }

    public String JTYPE(String sqlType)
    {
        if (sqlType == null || sqlType.equals(""))
        {
            return "unknown";
        }
        return types.get(sqlType.toUpperCase()) == null ? "unknown" : types.get(sqlType.toUpperCase());
    }
}
