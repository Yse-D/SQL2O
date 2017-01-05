package me.laudoak.model;

import me.laudoak.util.StringFormat;

import java.util.List;

/**
 * Created by laudoak on 16/12/18.
 */
public class Bean extends BaseModel
{
    private List<BeanAtt> atts;

    public Bean(String name, String comment)
    {
        super(name, comment);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(StringFormat.formatClzHead("public", getName(), getComment()));
        for (BaseAtt att : getAtts())
        {
            sb.append(StringFormat.formatAttributeRow("private", att.getType(), att.getName(), att.getComment()));
        }
        sb.append("}\n");
        return sb.toString();
    }

    public List<BeanAtt> getAtts()
    {
        return atts;
    }

    public void setAtts(List<BeanAtt> atts)
    {
        this.atts = atts;
    }
}
