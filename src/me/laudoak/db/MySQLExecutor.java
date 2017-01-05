package me.laudoak.db;

import me.laudoak.model.Table;
import me.laudoak.model.TableAtt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static me.laudoak.db.Schema.COLUMN_TYPE;
import static me.laudoak.db.Schema.SQL_SELECT_TABLES;

/**
 * Created by laudoak on 16/12/10.
 */
public class MySQLExecutor extends SQLExecutor
{
    public MySQLExecutor(DBStarter dbStarter)
    {
        super(dbStarter);
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
        ps = conn.prepareStatement(Schema.SQL_USE_INFORMATION_SCHEMA);
        rs = ps.executeQuery();
        String sql_tables = String.format(SQL_SELECT_TABLES, dbStarter.getInfo().getDbname());
        ps = conn.prepareStatement(sql_tables);
        rs = ps.executeQuery();
        Map<String, String> tableMap = new HashMap<>();
        while (rs.next())
        {
            tableMap.put((String) rs.getObject(Schema.TABLE_NAME), rs.getObject(Schema.TABLE_COMMENT) == null ? "" : (String) rs.getObject(Schema.TABLE_COMMENT));
        }
        List<Table> tables = new ArrayList<>();
        Iterator iterator = tableMap.keySet().iterator();
        while (iterator.hasNext())
        {
            String tableName = (String) iterator.next();
            String tableComment = tableMap.get(tableName);
            Table table = new Table(tableName, tableComment);
            tableProcessor(tableName, table);
            tables.add(table);
        }
        return tables;
    }

    private void tableProcessor(String tableName, Table table) throws SQLException, IllegalArgumentException, SecurityException, IllegalAccessException, InstantiationException
    {
        ps = conn.prepareStatement(Schema.SQL_USE_INFORMATION_SCHEMA);
        rs = ps.executeQuery();
        String sql_columns = String.format(Schema.SQL_SELECT_COLUMNS, dbStarter.getInfo().getDbname(), tableName);
        ps = conn.prepareStatement(sql_columns);
        rs = ps.executeQuery();

        while (rs.next())
        {
            rsmd = rs.getMetaData();
            String name = correct((String) rs.getObject(Schema.COLUMN_NAME));
            String type = correct((String) rs.getObject(Schema.DATA_TYPE));
            String comment = correct((String) rs.getObject(Schema.COLUMN_COMMENT));
            Integer ordinalPosition = correct(rs.getInt(Schema.ORDINAL_POSITION));
            String columnDefault = correct((String) rs.getObject(Schema.COLUMN_DEFAULT));
            String isNullable = correct((String) rs.getObject(Schema.IS_NULLABLE));
            String columnKey = correct((String) rs.getObject(Schema.COLUMN_KEY));
            String columnType = correct((String) rs.getObject(COLUMN_TYPE));
            String extra = correct((String) rs.getObject(Schema.EXTRA));

            TableAtt att = new TableAtt();
            att.setName(name);
            att.setType(type);
            att.setComment(comment);
            att.setOrdinalPosition(ordinalPosition);
            att.setColumnDefault(columnDefault);
            att.setIsNullable(isNullable);
            att.setColumnKey(columnKey);
            att.setColumnType(columnType);
            att.setExtra(extra);

            table.appendTableAtt(att);
        }

    }

    private String correct(String raw)
    {
        return raw == null ? "" : raw;
    }

    private Integer correct(Integer raw)
    {
        return raw == null ? 0 : raw;
    }

}
