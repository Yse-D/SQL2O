package me.laudoak.db;

import me.laudoak.util.Log;
import me.laudoak.io.BeanFileWriter;
import me.laudoak.name.Naming;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * Created by laudoak on 16/12/10.
 */
public abstract class SQLExecutor
{
    private static final String TAG = SQLExecutor.class.getSimpleName();

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    ResultSetMetaData rsmd;
    DBStarter dbStarter;
    Naming naming;
    TypeMapper typeMapper;

    private BeanFileWriter fileWriter;

    SQLExecutor(DBStarter DBStarter, Naming naming, BeanFileWriter fileWriter,TypeMapper typeMapper)
    {
        this.dbStarter = DBStarter;
        this.naming = naming;
        this.fileWriter = fileWriter;
        this.typeMapper = typeMapper;

        init();
    }

    private void init()
    {
        try
        {
            Class.forName(dbStarter.getDriver());
            conn = DriverManager.getConnection(dbStarter.getUrl());

        } catch (Exception e)
        {
            Log.error(TAG, "init error,cause:%s", e.getMessage());
            e.printStackTrace();
        }
    }

    public void execute()
    {
        fileWriter.write(getAllJavaBeanStringAll());
    }

    public abstract String getAllJavaBeanStringAll();

}
