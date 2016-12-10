package me.laudoak.db;

import me.laudoak.io.BeanFileWriter;
import me.laudoak.name.Naming;
import me.laudoak.util.StringFormat;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static me.laudoak.db.SystemSQL.sql_tables_format;

/**
 * Created by laudoak on 16/12/10.
 */
public class MySQLExecutor extends SQLExecutor
{
    public MySQLExecutor(DBStarter dbStarter, Naming naming, BeanFileWriter fileWriter, TypeMapper typeMapper)
    {
        super(dbStarter, naming, fileWriter, typeMapper);
    }

    @Override
    public String getAllJavaBeanStringAll()
    {
        try
        {
            return processor();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return "failed";
    }

    private String processor() throws SQLException, IllegalArgumentException, SecurityException, IllegalAccessException, InstantiationException
    {
        ps = conn.prepareStatement(SystemSQL.sql_schema);
        rs = ps.executeQuery();
        String sql_tables = String.format(sql_tables_format, dbStarter.getDBName());
        ps = conn.prepareStatement(sql_tables);
        rs = ps.executeQuery();
        Map<String, String> tbinfos = new HashMap<>();
        while (rs.next())
        {
            tbinfos.put((String) rs.getObject(SystemSQL.TABLE_NAME), rs.getObject(SystemSQL.TABLE_COMMENT) == null ? "" : (String) rs.getObject(SystemSQL.TABLE_COMMENT));
        }

        StringBuilder sb = new StringBuilder();
        Iterator iterator = tbinfos.keySet().iterator();
        while (iterator.hasNext())
        {
            String tableName = (String) iterator.next();
            String tableComment = tbinfos.get(tableName);
            sb.append(tableProcessor(tableName, tableComment));
        }
        return sb.toString();
    }

    private String tableProcessor(String tableName, String tableComment) throws SQLException, IllegalArgumentException, SecurityException, IllegalAccessException, InstantiationException
    {
        StringBuilder sb = new StringBuilder();
        sb.append(StringFormat.formatClzHead("public", naming.className(tableName), tableComment));
        ps = conn.prepareStatement(SystemSQL.sql_schema);
        rs = ps.executeQuery();
        String sql_columns = String.format(SystemSQL.sql_colums_format, dbStarter.getDBName(), tableName);
        ps = conn.prepareStatement(sql_columns);
        rs = ps.executeQuery();
        while (rs.next())
        {
            rsmd = rs.getMetaData();
            String name = (String) rs.getObject(SystemSQL.COLUMN_NAME);
            String type = typeMapper.JTYPE((String) rs.getObject(SystemSQL.DATA_TYPE));
            String comment = (String) rs.getObject(SystemSQL.COLUMN_COMMENT);
            sb.append(StringFormat.formatAttributeRow("private", type, naming.attributeName(name), comment));
        }

        return sb.append("}\n").toString();
    }

}
