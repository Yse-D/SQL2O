package me.laudoak.db;

/**
 * Created by laudoak on 16/12/8.
 */
public class Schema
{
    //information schema sql
    public static String SQL_USE_INFORMATION_SCHEMA = "use information_schema;";
    public static String SQL_SELECT_TABLES = "select * from TABLES where TABLE_SCHEMA='%s';";
    public static String SQL_SELECT_COLUMNS = "select * from COLUMNS where table_schema ='%s' and table_name = '%s';";

    //information_schema.tables column fields
    public static String TABLE_SCHEMA = "TABLE_SCHEMA";
    public static String TABLE_NAME = "TABLE_NAME";
    public static String ENGINE = "ENGINE";
    public static String TABLES_ROWS = "TABLES_ROWS";
    public static String TABLE_COMMENT = "TABLE_COMMENT";

    //information_schema.columns fields
    public static String COLUMN_NAME = "COLUMN_NAME";
    public static String ORDINAL_POSITION = "ORDINAL_POSITION";
    public static String IS_NULLABLE = "IS_NULLABLE";
    public static String COLUMN_DEFAULT = "COLUMN_DEFAULT";
    public static String DATA_TYPE = "DATA_TYPE";
    public static String COLUMN_TYPE = "COLUMN_TYPE";
    public static String COLUMN_KEY = "COLUMN_KEY";
    public static String EXTRA = "EXTRA";
    public static String COLUMN_COMMENT = "COLUMN_COMMENT";
}
