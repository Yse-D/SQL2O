package me.laudoak.gen;

import me.laudoak.db.DBStarter;
import me.laudoak.db.TypeMapper;
import me.laudoak.gen.ctrl.Generator;
import me.laudoak.model.Bean;
import me.laudoak.model.Table;
import me.laudoak.name.Naming;
import me.laudoak.util.Arguments;

import java.util.List;

/**
 * Created by laudoak on 17/1/1.
 */
public class BeanGenerator extends Generator
{
    public BeanGenerator(Naming naming, TypeMapper typeMapper, String projectName, String packageName, List<Table> tables, DBStarter dbStarter)
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
            writeBean(bean);
        }
    }

    private void writeBean(Bean bean)
    {
        write(structure.getModelPath(), bean.getName() + ".java", bean.toString());
    }
}
