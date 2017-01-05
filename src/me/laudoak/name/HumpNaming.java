package me.laudoak.name;

/**
 * Created by laudoak on 16/12/10.
 */
public class HumpNaming extends Naming
{

    @Override
    public String className(String raw)
    {
        return standardClassName(raw);
    }

    @Override
    public String attributeName(String raw)
    {
        return hump(raw);
    }
}
