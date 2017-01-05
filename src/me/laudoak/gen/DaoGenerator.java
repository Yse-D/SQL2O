package me.laudoak.gen;

import me.laudoak.db.DBStarter;
import me.laudoak.db.TypeMapper;
import me.laudoak.gen.ctrl.Generator;
import me.laudoak.model.Bean;
import me.laudoak.model.Table;
import me.laudoak.name.Naming;
import me.laudoak.tpl.TplEngine;
import me.laudoak.util.Arguments;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by laudoak on 17/1/2.
 */
public class DaoGenerator extends Generator
{

    public DaoGenerator(Naming naming, TypeMapper typeMapper, String projectName, String packageName, List<Table> tables, DBStarter dbStarter)
    {
        super(naming, typeMapper, projectName, packageName, tables, dbStarter);
    }

    @Override
    public void export()
    {
        if (Arguments.isEmpty(beans))
        {
            return;
        }
        for (Bean bean : beans)
        {
            Map<String, Object> map = new HashMap<>();
            map.put("beanName", bean.getName());
            map.put("packageName", packageName);
            map.put("date", new Date().toString());
            String mapperValue = TplEngine.instance().process(map, "Dao.vm");
            write(structure.getDaoPath(), bean.getName() + "Dao.java", mapperValue);
        }
    }
}
