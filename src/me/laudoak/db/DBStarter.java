package me.laudoak.db;

import me.laudoak.type.DB;
import me.laudoak.type.NAMING;

/**
 * Created by laudoak on 16/12/8.
 */
public class DBStarter
{
    private static final String TAG = DBStarter.class.getSimpleName();

    protected String driver;
    protected String url;
    protected Info info;
    private DB dbType;

    protected NAMING naming;
    protected String projectName;
    protected String packageName;
    protected TypeMapper typeMapper;

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

        public String getHost()
        {
            return host;
        }

        public String getPort()
        {
            return port;
        }

        public String getDbname()
        {
            return dbname;
        }

        public String getUsername()
        {
            return username;
        }

        public String getPassword()
        {
            return password;
        }
    }

    public void start()
    {
        this.driver = dbType.getDriver();
        this.url = String.format(dbType.getUrlFormat(), info.host, info.port, info.dbname, info.username, info.password);
        switch (dbType)
        {
            case MYSQL:
            {
                executor = new MySQLExecutor(this);
            }
        }
        executor.execute();
    }

    public String getDriver()
    {
        return driver;
    }

    public String getUrl()
    {
        return url;
    }

    public Info getInfo()
    {
        return info;
    }

    public DB getDbType()
    {
        return dbType;
    }

    public NAMING getNaming()
    {
        return naming;
    }

    public String getProjectName()
    {
        return projectName;
    }

    public String getPackageName()
    {
        return packageName;
    }

    public TypeMapper getTypeMapper()
    {
        return typeMapper;
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

    public void setProjectName(String projectName)
    {
        this.projectName = projectName;
    }

    public void setPackageName(String packageName)
    {
        this.packageName = packageName;
    }

    public void setTypeMapper(TypeMapper typeMapper)
    {
        this.typeMapper = typeMapper;
    }

}
