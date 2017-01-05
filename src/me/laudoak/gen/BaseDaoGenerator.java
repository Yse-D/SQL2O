package me.laudoak.gen;

import me.laudoak.db.DBStarter;
import me.laudoak.db.TypeMapper;
import me.laudoak.gen.ctrl.Generator;
import me.laudoak.model.Table;
import me.laudoak.name.Naming;
import me.laudoak.tpl.TplEngine;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by laudoak on 17/1/3.
 */
public class BaseDaoGenerator extends Generator
{
    public BaseDaoGenerator(Naming naming, TypeMapper typeMapper, String projectName, String packageName, List<Table> tables, DBStarter dbStarter)
    {
        super(naming, typeMapper, projectName, packageName, tables, dbStarter);
    }

    @Override
    public void export()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("packageName", packageName);
        map.put("date", new Date().toString());
        String mapperValue = TplEngine.instance().process(map, "BaseDao.vm");
        write(structure.getDaoPath(), "Dao.java", mapperValue);
    }
}
