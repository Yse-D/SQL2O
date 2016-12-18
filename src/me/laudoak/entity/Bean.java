package me.laudoak.entity;

import me.laudoak.util.StringFormat;

/**
 * Created by laudoak on 16/12/18.
 */
public class Bean extends BaseData
{

    public Bean(String name, String comment)
    {
        super(name, comment);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(StringFormat.formatClzHead("public", getName(), getComment()));
        for (MetaData meta : getAttributes())
        {
            sb.append(StringFormat.formatAttributeRow("private", meta.getType(), meta.getName(), meta.getComment()));
        }
        sb.append("}\n");
        return sb.toString();
    }
}
