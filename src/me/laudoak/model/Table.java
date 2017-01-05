package me.laudoak.model;

import me.laudoak.db.TypeMapper;
import me.laudoak.name.Naming;
import me.laudoak.util.Arguments;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by laudoak on 16/12/18.
 */
public class Table extends BaseModel
{
    private List<TableAtt> atts;

    public Table(String name, String comment)
    {
        super(name, comment);
        this.atts = new ArrayList<>();
    }

    public Bean convertToBean(Naming naming, TypeMapper mapper)
    {
        String beanName = naming.className(getName());
        String beanComment = getComment();
        Bean bean = new Bean(beanName, beanComment);
        List<TableAtt> tableAtts = getAtts();
        List<BeanAtt> beanAtts = new ArrayList<>();
        if (!Arguments.isEmpty(tableAtts))
        {
            for (TableAtt att : tableAtts)
            {
                beanAtts.add(new BeanAtt(naming.attributeName(att.getName()), mapper.JTYPE(att.getType()), att.getComment()));
            }
        }
        bean.setAtts(beanAtts);
        return bean;
    }

    public void appendTableAtt(TableAtt att)
    {
        if (this.atts != null)
        {
            atts.add(att);
        }
    }

    public List<TableAtt> getAtts()
    {
        return atts;
    }

    public void setAtts(List<TableAtt> atts)
    {
        this.atts = atts;
    }
}
