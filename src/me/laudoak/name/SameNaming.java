package me.laudoak.name;

/**
 * Created by laudoak on 16/12/10.
 */
public class SameNaming extends Naming
{
    @Override
    public String className(String raw)
    {
        return raw;
    }

    @Override
    public String attributeName(String raw)
    {
        return raw;
    }
}
