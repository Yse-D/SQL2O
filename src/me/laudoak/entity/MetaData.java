package me.laudoak.entity;

/**
 * Created by laudoak on 16/12/18.
 */
public class MetaData
{
    private String name;
    private String type;
    private String comment;

    public MetaData()
    {

    }

    public MetaData(String name, String type, String comment)
    {
        this.name = name;
        this.type = type;
        this.comment = comment;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }
}
