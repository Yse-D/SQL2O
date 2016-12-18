package me.laudoak.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by laudoak on 16/12/18.
 */

public class BaseData
{
    private String name;
    private String comment;
    private List<MetaData> attributes;

    public BaseData(String name, String comment)
    {
        this.name = name;
        this.comment = comment;
        initAttributes();
    }

    public void appendAttribute(MetaData metaData)
    {
        this.attributes.add(metaData);
    }

    private void initAttributes()
    {
        if (this.attributes==null)
        {
            attributes = new ArrayList<>();
        }
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public List<MetaData> getAttributes()
    {
        return attributes;
    }

    public void setAttributes(List<MetaData> attributes)
    {
        this.attributes = attributes;
    }
}
