package me.laudoak.db;

import me.laudoak.io.BeanFileWriter;
import me.laudoak.name.Naming;
import me.laudoak.type.DB;
import me.laudoak.type.NAMING;

/**
 * Created by laudoak on 16/12/8.
 */
public class DBStarter
{
    private static final String TAG = DBStarter.class.getSimpleName();

    private String driver;
    private String url;
    private Info info;
    private DB dbType;
    private NAMING naming;
    private String outputPath;
    private TypeMapper typeMapper;

    private SQLExecutor executor;

    public static class Info
    {
        private String host;
        private String port;
        private String dbname;
        private String username;
        private String password;

        public Info()
        {
        }

        public Info(String host, String port, String dbname, String username, String password)
        {
            this.host = host;
            this.port = port;
            this.dbname = dbname;
            this.username = username;
            this.password = password;
        }

        public Info host(String host)
        {
            this.host = host;
            return this;
        }

        public Info port(String port)
        {
            this.port = port;
            return this;
        }

        public Info dbname(String dbname)
        {
            this.dbname = dbname;
            return this;
        }

        public Info username(String username)
        {
            this.username = username;
            return this;
        }

        public Info password(String password)
        {
            this.password = password;
            return this;
        }

    }

    public void start()
    {
        this.driver = dbType.getDriver();
        this.url = String.format(dbType.getUrlFormat(), info.host, info.port, info.dbname, info.username, info.password);
        Naming name = naming.getNaming();
        BeanFileWriter fileWriter = new BeanFileWriter(outputPath, name, typeMapper);
        switch (dbType)
        {
            case MYSQL:
            {
                executor = new MySQLExecutor(this, fileWriter);
            }
        }
        executor.execute();
    }

    public String getDriver()
    {
        return this.driver;
    }

    public String getUrl()
    {
        return this.url;
    }

    public String getDBName()
    {
        return info.dbname;
    }

    public void setDbType(DB dbType)
    {
        this.dbType = dbType;
    }

    public void setInfo(Info info)
    {
        this.info = info;
    }

    public void setNaming(NAMING naming)
    {
        this.naming = naming;
    }

    public void setOutputPath(String outputPath)
    {
        this.outputPath = outputPath;
    }

    public void setTypeMapper(TypeMapper typeMapper)
    {
        this.typeMapper = typeMapper;
    }

}
