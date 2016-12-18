package me.laudoak.entity;

import me.laudoak.db.TypeMapper;
import me.laudoak.name.Naming;
import me.laudoak.util.Arguments;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by laudoak on 16/12/18.
 */
public class Table extends BaseData
{

    public Table(String name, String comment)
    {
        super(name, comment);
    }

    public Bean convertToBean(Naming naming, TypeMapper mapper)
    {
        String beanName = naming.className(getName());
        String beanComment = getComment();
        Bean bean = new Bean(beanName, beanComment);
        List<MetaData> tableMetas = getAttributes();
        List<MetaData> beanMetas = new ArrayList<>();
        if (!Arguments.isEmpty(tableMetas))
        {
            for (MetaData meta : tableMetas)
            {
                beanMetas.add(new MetaData(naming.attributeName(meta.getName()), mapper.JTYPE(meta.getType()), meta.getComment()));
            }
        }
        bean.setAttributes(beanMetas);
        return bean;
    }

}
