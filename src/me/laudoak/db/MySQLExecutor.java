package me.laudoak.db;

import me.laudoak.entity.MetaData;
import me.laudoak.entity.Table;
import me.laudoak.io.BeanFileWriter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static me.laudoak.db.SystemSQL.sql_tables_format;

/**
 * Created by laudoak on 16/12/10.
 */
public class MySQLExecutor extends SQLExecutor
{
    public MySQLExecutor(DBStarter dbStarter, BeanFileWriter fileWriter)
    {
        super(dbStarter, fileWriter);
    }

    @Override
    public List<Table> getTableList()
    {
        try
        {
            return processor();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private List<Table> processor() throws SQLException, IllegalArgumentException, SecurityException, IllegalAccessException, InstantiationException
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
        List<Table> tables = new ArrayList<>();
        Iterator iterator = tbinfos.keySet().iterator();
        while (iterator.hasNext())
        {
            String tableName = (String) iterator.next();
            String tableComment = tbinfos.get(tableName);
            Table table = new Table(tableName, tableComment);
            tableProcessor(tableName, table);
            tables.add(table);
        }
        return tables;
    }

    private void tableProcessor(String tableName, Table table) throws SQLException, IllegalArgumentException, SecurityException, IllegalAccessException, InstantiationException
    {
        ps = conn.prepareStatement(SystemSQL.sql_schema);
        rs = ps.executeQuery();
        String sql_columns = String.format(SystemSQL.sql_colums_format, dbStarter.getDBName(), tableName);
        ps = conn.prepareStatement(sql_columns);
        rs = ps.executeQuery();

        while (rs.next())
        {
            rsmd = rs.getMetaData();
            String name = (String) rs.getObject(SystemSQL.COLUMN_NAME);
            String type = (String) rs.getObject(SystemSQL.DATA_TYPE);
            String comment = (String) rs.getObject(SystemSQL.COLUMN_COMMENT);

            MetaData metaData = new MetaData();
            metaData.setName(name);
            metaData.setType(type);
            metaData.setComment(comment);
            table.appendAttribute(metaData);
        }
    }

}
