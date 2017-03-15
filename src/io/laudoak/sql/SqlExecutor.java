package io.laudoak.sql;

import io.laudoak.auxiliary.Logger;
import io.laudoak.config.Config;
import io.laudoak.model.TableAtt;
import io.laudoak.model.TableModel;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by laudoak on 17/3/8.
 */
public class SqlExecutor extends AbSqlExecutor {

    private static final String TAG = SqlExecutor.class.getSimpleName();

    public SqlExecutor(Config conf, TypeMapper typeMapper) {
        super(conf, typeMapper);
    }

    @Override
    public List<TableModel> listTables() {
        try {
            return queryTables();
        } catch (Exception e) {
            Logger.info("execute sql error,cause> %s", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询数据库的表
     *
     * @return
     * @throws Exception
     */
    private List<TableModel> queryTables() throws Exception {
        ps = con.prepareStatement(Statement.SQL_USE_INFORMATION_SCHEMA);
        rs = ps.executeQuery();
        String sql_tables = String.format(Statement.SQL_SELECT_TABLES, cnf.getDb());
        ps = con.prepareStatement(sql_tables);
        rs = ps.executeQuery();
        Map<String, String> tableMap = new HashMap<>();
        while (rs.next()) {
            String tableName = (String) rs.getObject(Statement.TABLE_NAME);
            String tableComment = rs.getObject(Statement.TABLE_COMMENT) == null ? "" : (String) rs.getObject(Statement.TABLE_COMMENT);
            tableMap.put(tableName, tableComment);
        }
        Iterator iterator = tableMap.keySet().iterator();
        List<TableModel> tables = new ArrayList<>();
        while (iterator.hasNext()) {
            String tableName = (String) iterator.next();
            String tableComment = tableMap.get(tableName);
            TableModel table = new TableModel(tableName, tableComment);
            queryTable(tableName, table);
            tables.add(table);
        }
        Logger.info(TAG, "query tables,table size> %s", tables.size());
        return tables;
    }

    /**
     * 查询表的每一个字段
     *
     * @param tableName
     * @param table
     * @throws Exception
     */
    private void queryTable(String tableName, TableModel table) throws Exception {
        Logger.info(TAG, "query table,table name> %s", tableName);
        ps = con.prepareStatement(Statement.SQL_USE_INFORMATION_SCHEMA);
        rs = ps.executeQuery();
        String sql_columns = String.format(Statement.SQL_SELECT_COLUMNS, cnf.getDb(), tableName);
        ps = con.prepareStatement(sql_columns);
        rs = ps.executeQuery();
        while (rs.next()) {
            rsmd = rs.getMetaData();
            String name = verify((String) rs.getObject(Statement.COLUMN_NAME));
            String type = verify((String) rs.getObject(Statement.DATA_TYPE));
            String comment = verify((String) rs.getObject(Statement.COLUMN_COMMENT));
            Integer ordinalPosition = verify(rs.getInt(Statement.ORDINAL_POSITION));
            String columnDefault = verify((String) rs.getObject(Statement.COLUMN_DEFAULT));
            String isNullable = verify((String) rs.getObject(Statement.IS_NULLABLE));
            String columnKey = verify((String) rs.getObject(Statement.COLUMN_KEY));
            String columnType = verify((String) rs.getObject(Statement.COLUMN_TYPE));
            String extra = verify((String) rs.getObject(Statement.EXTRA));

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

    private String verify(String raw) {
        return raw == null ? "" : raw;
    }

    private Integer verify(Integer raw) {
        return raw == null ? 0 : raw;
    }

}
