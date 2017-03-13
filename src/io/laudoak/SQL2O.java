package io.laudoak;

import io.laudoak.config.Config;
import io.laudoak.sql.AbSqlExecutor;
import io.laudoak.sql.SqlExecutor;
import io.laudoak.sql.TypeMapper;

/**
 * Created by laudoak on 17/3/8.
 */
public class SQL2O {
    public static void run() {
        Config config = Config.from("sql2o.yml");
        assert config != null;
        TypeMapper typeMapper = new TypeMapper(config.getTypeMapper());
        AbSqlExecutor executor = new SqlExecutor(config, typeMapper);
        executor.execute();
    }
}
