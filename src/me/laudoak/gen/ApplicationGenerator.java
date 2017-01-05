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
public class ApplicationGenerator extends Generator
{
    public ApplicationGenerator(Naming naming, TypeMapper typeMapper, String projectName, String packageName, List<Table> tables, DBStarter dbStarter)
    {
        super(naming, typeMapper, projectName, packageName, tables, dbStarter);
    }

    @Override
    public void export()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("packageName", packageName);
        map.put("projectName", projectName);
        String value = TplEngine.instance().process(map, "Application.vm");
        write(structure.getJavaPackagePath(), projectName + "Application.java", value);
    }
}
