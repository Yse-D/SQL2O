package io.laudoak.sql;

import io.laudoak.auxiliary.Logger;
import io.laudoak.config.Config;
import io.laudoak.model.TableModel;
import io.laudoak.output.RenderCenter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;

/**
 * Created by laudoak on 17/3/8.
 */
public abstract class AbSqlExecutor {
    private static final String TAG = AbSqlExecutor.class.getSimpleName();
    protected Config cnf;
    protected TypeMapper typeMapper;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ResultSetMetaData rsmd;

    public AbSqlExecutor(Config conf, TypeMapper typeMapper) {
        this.cnf = conf;
        this.typeMapper = typeMapper;
        try {
            Logger.info(TAG, "sql driver class name>%s,url>%s", conf.getDriverClassName(), conf.getUrl());
            Class.forName(conf.getDriverClassName());
            con = DriverManager.getConnection(conf.getUrl());
        } catch (Exception e) {
            Logger.error(TAG, "database initialize error,cause:%s", e.getMessage());
            e.printStackTrace();
        }
    }

    public void execute() {
        RenderCenter renderCneter = new RenderCenter(cnf, typeMapper, listTables());
        renderCneter.render();
    }

    public abstract List<TableModel> listTables();

}
