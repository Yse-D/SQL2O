package me.laudoak.name;

import me.laudoak.util.Arguments;

/**
 * Created by laudoak on 16/12/10.
 */
public class HumpNaming extends Naming
{

    @Override
    public String className(String raw)
    {
        if (Arguments.isNullOrEmpty(raw))
        {
            return UNKNOWN;
        }
        return prefixLetterToUpper(raw);
    }

    @Override
    public String attributeName(String raw)
    {
        return hump(raw);
    }

    private String hump(String raw)
    {
        if (Arguments.isNullOrEmpty(raw))
        {
            return UNKNOWN;
        }

        StringBuilder sb = new StringBuilder();
        String splits[] = split(raw);
        boolean first = true;
        for (String s : splits)
        {
            if (first && !Arguments.isNullOrEmpty(s))
            {
                sb.append(s);
                first = false;
            } else if (!Arguments.isNullOrEmpty(s))
            {
                sb.append(prefixLetterToUpper(s));
            }
        }

        return sb.toString();

    }

}
