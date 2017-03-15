package io.laudoak.config;

import com.esotericsoftware.yamlbeans.YamlReader;
import io.laudoak.auxiliary.Logger;

import java.io.FileReader;
import java.net.URI;
import java.util.Map;

/**
 * Created by laudoak on 17/3/3.
 * <p>
 * 项目运行的配置类,包括数据库,项目和类型映射信息
 */
public class Config {

    private static final String TAG = Config.class.getSimpleName();

    //Database profiles
    private String driverClassName;
    private String url;
    private String username;
    private String password;

    //extra info from url
    private String schema;
    private String host;
    private String port;
    private String db;

    //Project/Module profiles
    private String packageName;
    private String projectName;

    //Java & Database data type mapper
    private Map<String, String> typeMapper;

    private Config(Builder builder) {
        this.driverClassName = builder.driverClassName;
        this.url = builder.url;
        this.password = builder.password;
        this.username = builder.username;
        this.packageName = builder.packageName;
        this.projectName = builder.projectName;
        this.typeMapper = builder.typeMapper;
        this.url = url + String.format("&user=%s&password=%s", username, password);
        extra(url);
        Logger.info(TAG,
                "config build completed:\n\n" +
                        ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n" +
                        "driver: %s\n" +
                        "url: %s\n" +
                        "username: %s\n" +
                        "password: %s\n" +
                        "schema: %s\n" +
                        "host: %s\n" +
                        "port: %s\n" +
                        "db: %s\n" +
                        "packageName: %s\n" +
                        "projectName: %s\n" +
                        "typeMapper: %s\n" +
                        ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n",
                driverClassName, url, username, password, schema, host, port, db, packageName, projectName, typeMapper);
    }

    public static Config from(String yamlFilePath) {
        try {
            YamlReader reader = new YamlReader(new FileReader("sql2o.yml"));
            Map map = (Map) reader.read();
            Logger.info(TAG, "read yaml profiles:%s", map);
            return build(map);
        } catch (Exception e) {
            Logger.error(TAG, "read yaml file error ,cause:%s", e.getMessage());
        }
        return null;
    }

    private static Config build(Map map) {
        Map<String, String> dbMap = (Map<String, String>) map.get("database");
        Map<String, String> pjMap = (Map<String, String>) map.get("project");
        Map<String, String> tpMap = (Map<String, String>) map.get("typeMapper");
        Config config = new Builder()
                .driverClassName(dbMap.get("driverClassName"))
                .url(dbMap.get("url"))
                .username(dbMap.get("username"))
                .password(dbMap.get("password"))
                .packageName(pjMap.get("packageName"))
                .projectName(pjMap.get("projectName"))
                .typeMapper(tpMap)
                .build();
        return config;
    }

    private void extra(String url) {
        String uriString = url.substring(5);
        URI uri = URI.create(uriString);
        this.schema = uri.getScheme();
        this.host = uri.getHost();
        this.port = String.valueOf(uri.getPort());
        this.db = uri.getPath().substring(1);
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSchema() {
        return schema;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getDb() {
        return db;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getProjectName() {
        return projectName;
    }

    public Map<String, String> getTypeMapper() {
        return typeMapper;
    }

    public static class Builder {

        private String driverClassName;
        private String url;
        private String username;
        private String password;

        private String packageName;
        private String projectName;

        private Map<String, String> typeMapper;

        public Builder driverClassName(String driverClassName) {
            this.driverClassName = driverClassName;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder packageName(String packageName) {
            this.packageName = packageName;
            return this;
        }

        public Builder projectName(String projectName) {
            this.projectName = projectName;
            return this;
        }

        public Builder typeMapper(Map<String, String> typeMapper) {
            this.typeMapper = typeMapper;
            return this;
        }

        public Config build() {
            return new Config(this);
        }
    }
}
