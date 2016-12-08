package me.laudoak;

import java.sql.*;
import java.util.*;

/**
 * Created by laudoak on 16/12/8.
 */
public class SQL2O
{
    private static final String TAG = SQL2O.class.getSimpleName();

    private static PreparedStatement pst;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd;
    private static Connection con;

    static
    {
        init();
    }

    private static void init()
    {
        Property.load();
        TypeMapper.load();
        try
        {
            Class.forName(Property.driverclassname);
            con = DriverManager.getConnection(Property.url);

        } catch (Exception e)
        {
            Log.error(TAG, "init error,cause:%s", e.getMessage());
            e.printStackTrace();
        }
    }

    public static String getBeanString()
            throws SQLException, IllegalArgumentException, SecurityException, IllegalAccessException, InstantiationException
    {
        pst = con.prepareStatement(SysSQL.sql_schema);
        rs = pst.executeQuery();
        pst = con.prepareStatement(SysSQL.sql_tables);
        rs = pst.executeQuery();

        Map<String, String> tables = new HashMap<>();
        while (rs.next())
        {
            rsmd = rs.getMetaData();
//            for (int i = 1; i <= rsmd.getColumnCount(); i++)
//            {
//                Log.info(TAG, "column name:%s value:%s", rsmd.getColumnName(i), rs.getObject(i) == null ? "" : rs.getObject(i).toString());
//                tables.put(rsmd.getColumnName(i), rs.getObject(i) == null ? "" : rs.getObject(i).toString());
//            }
            tables.put((String) rs.getObject(SysSQL.TABLE_NAME), (String) rs.getObject(SysSQL.TABLE_COMMENT) == null ? "" : (String) rs.getObject(SysSQL.TABLE_COMMENT));
        }

        StringBuilder sb = new StringBuilder();
        Iterator iterator = tables.keySet().iterator();
        while (iterator.hasNext())
        {
            String tableName = (String) iterator.next();
            String tableComment = tables.get(tableName);
            sb.append(tableProcessor(tableName, tableComment));
        }

        return sb.toString();
    }

    private static String tableProcessor(String tableName, String tableComment)
            throws SQLException, IllegalArgumentException, SecurityException, IllegalAccessException, InstantiationException
    {
        StringBuilder sb = new StringBuilder();
        sb.append("public class " + tableName + "{\t//" + tableComment + "\n");
        pst = con.prepareStatement(SysSQL.sql_schema);
        rs = pst.executeQuery();
        String sql_columns = String.format(SysSQL.sql_colums_format, Property.dbname, tableName);
        pst = con.prepareStatement(sql_columns);
        rs = pst.executeQuery();
        while (rs.next())
        {
            String meta = "\tprivate ";
            rsmd = rs.getMetaData();
            String name = (String) rs.getObject(SysSQL.COLUMN_NAME);
            String type = TypeMapper.JTYPE((String) rs.getObject(SysSQL.DATA_TYPE));
            String comment = (String) rs.getObject(SysSQL.COLUMN_COMMENT);
            meta = meta + type + " " + name + "; " + " //" + comment;
            sb.append(meta + "\n");
//            for (int i = 1; i <= rsmd.getColumnCount(); i++)
//            {
//                Log.info(TAG, "column name:%s value:%s", rsmd.getColumnName(i), rs.getObject(i) == null ? "" : rs.getObject(i).toString());
//
//            }
        }

        return sb.append("\n}\n").toString();
    }

    public static Iterator getBeans(String condition, Object bean, String table)
            throws SQLException, IllegalArgumentException, SecurityException, IllegalAccessException, InstantiationException
    {
        String sql = "SELECT * FROM " + table + " WHERE " + condition;
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        rsmd = rs.getMetaData();
        ArrayList link = new ArrayList();
        Object element;
        element = bean.getClass().newInstance();

        while (rs.next())
        {
            rsmd = rs.getMetaData();
            int lenclz = element.getClass().getDeclaredFields().length;
            int lensql = rsmd.getColumnCount();
            for (int i = 0; i < lensql; i++)
            {
                for (int j = 0; j < lenclz; j++)
                {
                    if (element.getClass().getDeclaredFields()[j].getName().equals(rsmd.getColumnName(i + 1)))
                    {
                        element.getClass().getDeclaredFields()[j].set(element, rs.getObject(i + 1));
                    }
                }
            }
            link.add(element);
            element = bean.getClass().newInstance();
        }
        return link.iterator();
    }

    public static Object getBean(String condition, Object bean, String table)
            throws SQLException, IllegalArgumentException, SecurityException, IllegalAccessException, InstantiationException
    {
        String sql = "SELECT * FROM " + table + " WHERE " + condition;
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        rsmd = rs.getMetaData();
        rs.next();
        int length = bean.getClass().getDeclaredFields().length;
        int length1 = rsmd.getColumnCount();
        if (rs.getRow() == 1)
        {
            for (int i = 0; i < length1; i++)
            {
                for (int j = 0; j < length; j++)
                {
                    if (bean.getClass().getDeclaredFields()[j].getName()
                            .equals(rsmd.getColumnName(i + 1)))
                    {
                        bean.getClass().getDeclaredFields()[j].set(bean,
                                rs.getObject(i + 1));
                    }
                }
            }
        } else
            return null;
        return bean;
    }

}
