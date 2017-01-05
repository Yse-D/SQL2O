package me.laudoak.gen;

import me.laudoak.db.DBStarter;
import me.laudoak.db.TypeMapper;
import me.laudoak.gen.ctrl.Generator;
import me.laudoak.model.Table;
import me.laudoak.name.Naming;
import me.laudoak.tpl.TplEngine;

import java.util.List;

/**
 * Created by laudoak on 17/1/5.
 */
public class ApplicationPropertiesGenerator extends Generator
{
    public ApplicationPropertiesGenerator(Naming naming, TypeMapper typeMapper, String projectName, String packageName, List<Table> tables, DBStarter dbStarter)
    {
        super(naming, typeMapper, projectName, packageName, tables, dbStarter);
    }

    @Override
    public void export()
    {
        String mapperValue = TplEngine.instance().process("ApplicationProperties.vm");
        write(structure.getResourcePath(), "application.properties", mapperValue);

    }
}
