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
                .dbType(DB.MYSQL)
                .sourceType(SOURCE.DBSERVER)
                .info(new DBStarter.Info("127.0.0.1", "3306", "test", "root", ""))
                .naming(NAMING.HUMP)
                .typeMapper(new TypeMapper().defaultMapper())
                .outputPath("bean")
                .build()
                .start();

//        new SQL2O.Builder()
//                .build()
//                .start();
    }
}
