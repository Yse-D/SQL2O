package me.laudoak.gen;

import me.laudoak.db.DBStarter;
import me.laudoak.db.TypeMapper;
import me.laudoak.gen.ctrl.Generator;
import me.laudoak.model.Table;
import me.laudoak.name.Naming;
import me.laudoak.tpl.TplEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by laudoak on 17/1/4.
 */
public class MyBatisConfigGenerator extends Generator
{

    public MyBatisConfigGenerator(Naming naming, TypeMapper typeMapper, String projectName, String packageName, List<Table> tables, DBStarter dbStarter)
    {
        super(naming, typeMapper, projectName, packageName, tables, dbStarter);
    }

    @Override
    public void export()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("beans", beans);
        map.put("driver", "com.mysql.jdbc.Driver");
        map.put("url", "jdbc:mysql://" + dbStarter.getInfo().getHost() + "/" + dbStarter.getInfo().getDbname());
        map.put("username", dbStarter.getInfo().getUsername());
        map.put("password", dbStarter.getInfo().getPassword());
        String value = TplEngine.instance().process(map, "MyBatisConfig.vm");
        write(structure.getResourcePath(), "mybatis-config.xml", value);
    }
}
