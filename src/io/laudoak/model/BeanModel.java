package io.laudoak.model;

import io.laudoak.auxiliary.StringFormatter;

import java.util.List;

/**
 * Created by laudoak on 16/12/18.
 * <p>
 * Java实体类
 */
public class BeanModel extends BaseModel {
    private List<BeanAtt> atts;

    public BeanModel(String name, String comment) {
        super(name, comment);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringFormatter.formatClzHead("public", getName(), getComment()));
        for (BaseAtt att : getAtts()) {
            sb.append(StringFormatter.formatAttributeRow("private", att.getType(), att.getName(), att.getComment()));
        }
        sb.append("}\n");
        return sb.toString();
    }

    public List<BeanAtt> getAtts() {
        return atts;
    }

    public void setAtts(List<BeanAtt> atts) {
        this.atts = atts;
    }
}
