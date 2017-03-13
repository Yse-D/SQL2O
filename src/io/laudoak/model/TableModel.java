package io.laudoak.model;


import io.laudoak.auxiliary.Arguments;
import io.laudoak.config.Naming;
import io.laudoak.sql.TypeMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by laudoak on 16/12/18.
 * <p>
 * 数据库表实体
 */
public class TableModel extends BaseModel {
    private List<TableAtt> atts;

    public TableModel(String name, String comment) {
        super(name, comment);
        this.atts = new ArrayList<>();
    }

    public BeanModel convertToBean(TypeMapper mapper) {
        String beanName = Naming.className(getName());
        String beanComment = getComment();
        BeanModel bean = new BeanModel(beanName, beanComment);
        List<TableAtt> tableAtts = getAtts();
        List<BeanAtt> beanAtts = new ArrayList<>();
        if (!Arguments.isEmpty(tableAtts)) {
            for (TableAtt att : tableAtts) {
                beanAtts.add(new BeanAtt(Naming.attributeName(att.getName()), mapper.JTYPE(att.getType()), att.getComment()));
            }
        }
        bean.setAtts(beanAtts);
        return bean;
    }

    public void appendTableAtt(TableAtt att) {
        if (this.atts != null) {
            atts.add(att);
        }
    }

    public List<TableAtt> getAtts() {
        return atts;
    }

    public void setAtts(List<TableAtt> atts) {
        this.atts = atts;
    }
}
