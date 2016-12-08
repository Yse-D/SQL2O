package me.laudoak;

/**
 * Created by laudoak on 16/12/8.
 */
public class SysSQL
{
    public static String sql_schema = "use information_schema;";
    public static String sql_tables_format = "select * from TABLES where TABLE_SCHEMA='%s';";
    public static String sql_colums_format = "select * from information_schema.columns where table_schema ='%s' and table_name = '%s';";

    public static String sql_tables = String.format(sql_tables_format,Property.dbname);

    public static String TABLE_SCHEMA = "TABLE_SCHEMA";
    public static String TABLE_NAME = "TABLE_NAME";
    public static String ENGINE = "ENGINE";
    public static String TABLES_ROWS = "TABLES_ROWS";
    public static String TABLE_COMMENT = "TABLE_COMMENT";

    public static String COLUMN_NAME = "COLUMN_NAME";
    public static String ORDINAL_POSITION = "ORDINAL_POSITION";
    public static String IS_NULLABLE = "IS_NULLABLE";
    public static String DATA_TYPE = "DATA_TYPE";
    public static String COLUMN_TYPE = "COLUMN_TYPE";
    public static String COLUMN_KEY = "COLUMN_KEY";
    public static String EXTRA = "EXTRA";
    public static String COLUMN_COMMENT = "COLUMN_COMMENT";
}
