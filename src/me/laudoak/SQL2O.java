package me.laudoak;

import me.laudoak.db.DBStarter;
import me.laudoak.db.TypeMapper;
import me.laudoak.type.DB;
import me.laudoak.type.NAMING;
import me.laudoak.type.SOURCE;

/**
 * Created by laudoak on 16/12/8.
 */
public class SQL2O
{
    private static final String TAG = SQL2O.class.getSimpleName();

    private DB dbType;
    private SOURCE sourceType;
    private NAMING naming;
    private String outputPath;
    private DBStarter.Info info;
    private TypeMapper typeMapper;

    private DBStarter dbStarter;

    private SQL2O(Builder builder)
    {
        this.dbType = builder.dbType;
        this.sourceType = builder.sourceType;
        this.naming = builder.naming;
        this.outputPath = builder.outputPath;
        this.info = builder.info;
        this.typeMapper = builder.typeMapper;

        dbStarter = new DBStarter();
    }

    public void start()
    {
        dbStarter.setDbType(dbType);
        dbStarter.setInfo(info);
        dbStarter.setNaming(naming);
        dbStarter.setOutputPath(outputPath);
        dbStarter.setTypeMapper(typeMapper);

        dbStarter.start();
    }

    public static class Builder
    {
        private DB dbType = DB.MYSQL;
        private SOURCE sourceType = SOURCE.DBSERVER;
        private NAMING naming = NAMING.HUMP;
        private String outputPath = "src/";
        private DBStarter.Info info = new DBStarter.Info("127.0.0.1", "3306", "test", "root", "");
        private TypeMapper typeMapper = new TypeMapper().defaultMapper();

        public Builder dbType(DB dbType)
        {
            this.dbType = dbType;
            return this;
        }

        public Builder sourceType(SOURCE sourceType)
        {
            this.sourceType = sourceType;
            return this;
        }

        public Builder naming(NAMING naming)
        {
            this.naming = naming;
            return this;
        }

        public Builder outputPath(String outputPath)
        {
            this.outputPath = outputPath;
            return this;
        }

        public Builder info(DBStarter.Info info)
        {
            this.info = info;
            return this;
        }

        public Builder typeMapper(TypeMapper typeMapper)
        {
            this.typeMapper = typeMapper;
            return this;
        }

        public SQL2O build()
        {
            return new SQL2O(this);
        }
    }
}
