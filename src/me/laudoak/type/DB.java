package me.laudoak.type;

/**
 * Created by laudoak on 16/12/10.
 */
public enum  DB
{
    MYSQL("com.mysql.jdbc.Driver","jdbc:mysql://%s:%s/%s?user=%s&password=%s&autoReconnect=true&useUnicode=true&characterEncoding=UTF8&useSSL=false"),
    DB2("",""),
    ORACLE("","");

    private String driver;
    private String urlFormat;

    DB(String driver, String urlFormat)
    {
        this.driver = driver;
        this.urlFormat = urlFormat;
    }

    public String getDriver()
    {
        return driver;
    }

    public String getUrlFormat()
    {
        return urlFormat;
    }
}
