package me.laudoak;

import me.laudoak.db.DBStarter;
import me.laudoak.db.TypeMapper;
import me.laudoak.type.DB;
import me.laudoak.type.NAMING;
import me.laudoak.type.SOURCE;

import java.sql.SQLException;

/**
 * Created by laudoak on 16/12/8.
 */
public class Test
{
    public static void main(String... args) throws IllegalAccessException, SQLException, InstantiationException
    {
        new SQL2O.Builder()
                .dbType(DB.MYSQL)//database type : default MySQL
                .info(new DBStarter.Info("127.0.0.1", "3306", "blog", "root", ""))//MySQL database information : host,port,database,username,password
                .naming(NAMING.HUMP)//naming rule , default using camel-casing preferences
                .typeMapper(new TypeMapper().defaultMapper())//MySQL to Java data type mapper
                .projectName("blog")//project name : default 'project'
                .packageName("io.laudoak")//package name : default 'io.demo'
                .build()
                .start();
    }
}
