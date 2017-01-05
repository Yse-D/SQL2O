package me.laudoak.db;

import me.laudoak.gen.ctrl.GeneratorCtrl;
import me.laudoak.model.Table;
import me.laudoak.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;

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
    private GeneratorCtrl generatorCtrl;

    SQLExecutor(DBStarter DBStarter)
    {
        this.dbStarter = DBStarter;
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
        generatorCtrl = new GeneratorCtrl(dbStarter, getTableList());
        generatorCtrl.export();
    }

    public abstract List<Table> getTableList();

}
