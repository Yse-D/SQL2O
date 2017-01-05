package me.laudoak.gen;

import me.laudoak.db.DBStarter;
import me.laudoak.db.TypeMapper;
import me.laudoak.gen.ctrl.Generator;
import me.laudoak.model.Bean;
import me.laudoak.model.BeanAtt;
import me.laudoak.model.Table;
import me.laudoak.model.TableAtt;
import me.laudoak.name.Naming;
import me.laudoak.tpl.TplEngine;
import me.laudoak.util.Arguments;
import me.laudoak.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by laudoak on 17/1/1.
 */
public class MapperGenerator extends Generator
{

    private static final String TAG = MapperGenerator.class.getSimpleName();

    public MapperGenerator(Naming naming, TypeMapper typeMapper, String projectName, String packageName, List<Table> tables, DBStarter dbStarter)
    {
        super(naming, typeMapper, projectName, packageName, tables, dbStarter);
    }

    @Override
    public void export()
    {
        if (Arguments.isEmpty(tables))
        {
            return;
        }
        for (Table table : tables)
        {
            Bean bean = table.convertToBean(naming, typeMapper);
            Map<String, Object> map = new HashMap<>();
            map.put("beanName", bean.getName());
            map.put("packageName", packageName);
            map.put("propertyColumnMap", getPropertyColumnMap(table));
            map.put("tableName", table.getName());
            map.put("values", getValues(table));
            map.put("columns", getColumns(table));
            map.put("conditionMap", getConditionMap(table));
            map.put("updateMap", getUpdateMap(table));
            String value = TplEngine.instance().process(map, "Mapper.vm");
            write(structure.getMapperPath(), bean.getName() + "Mapper.xml", value);
        }
    }

    private Map<String, String> getPropertyColumnMap(Table table)
    {
        List<TableAtt> atts = table.getAtts();
        List<BeanAtt> atts1 = table.convertToBean(naming, typeMapper).getAtts();
        Map<String, String> result = new HashMap<>();
        for (int i = 0; i < atts.size(); i++)
        {
            TableAtt att = atts.get(i);
            BeanAtt att1 = atts1.get(i);
            if (att.getName().equals("id"))
            {
                continue;
            }
            result.put(att.getName(), att1.getName());
        }
        return result;
    }

    private List<String> getValues(Table table)
    {
        List<String> values = new ArrayList<>();
        Bean bean = table.convertToBean(naming, typeMapper);
        List<BeanAtt> atts = bean.getAtts();
        for (int i = 0; i < atts.size(); i++)
        {
            BeanAtt att = atts.get(i);
            if (att.getName().equals("id"))
            {
                continue;
            }
            if (att.getType().equals("Date"))
            {
                values.add("NOW()");
            }
            values.add(att.getName());
        }
        return values;
    }

    private List<String> getColumns(Table table)
    {
        List<TableAtt> atts = table.getAtts();
        List<String> results = new ArrayList<>();
        for (int i = 0; i < atts.size(); i++)
        {
            results.add(atts.get(i).getName());
        }
        return results;
    }

    private Map<String, String> getConditionMap(Table table)
    {
        List<TableAtt> atts = table.getAtts();
        List<BeanAtt> atts1 = table.convertToBean(naming, typeMapper).getAtts();
        Map<String, String> result = new HashMap<>();
        for (int i = 0; i < atts.size(); i++)
        {
            TableAtt att = atts.get(i);
            BeanAtt att1 = atts1.get(i);
            result.put(att.getName(), att1.getName());
        }
        //Log.info(TAG, "condition map:%s", result);
        return result;
    }

    private Map<String, String> getUpdateMap(Table table)
    {
        List<TableAtt> atts = table.getAtts();
        List<BeanAtt> atts1 = table.convertToBean(naming, typeMapper).getAtts();
        Map<String, String> result = new HashMap<>();
        for (int i = 0; i < atts.size(); i++)
        {
            TableAtt att = atts.get(i);
            BeanAtt att1 = atts1.get(i);
            if (att.getName().equals("id"))
            {
                continue;
            }
            if (att1.getType().equals("Date"))
            {
                continue;
            }
            result.put(att.getName(), att1.getName());
        }
        return result;
    }


}
