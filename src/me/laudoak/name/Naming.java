package me.laudoak.name;

import me.laudoak.util.Arguments;

/**
 * Created by laudoak on 16/12/10.
 */
public abstract class Naming
{
    String UNKNOWN = "unknown";

    public abstract String className(String raw);

    public abstract String attributeName(String raw);

    String prefixLetterToUpper(String src)
    {
        if (Arguments.isNull(src))
        {
            return UNKNOWN;
        }

        char srcs[] = src.toCharArray();
        for (int i = 0; i < srcs.length; i++)
        {
            if (isLetter(srcs[i]))
            {
                srcs[i] = toUpper(srcs[i]);
                break;
            }
        }

        return charArrayToString(srcs);
    }

    boolean isLetter(char ch)
    {
        return isUpperLetter(ch) || isLowerLetter(ch);
    }

    boolean isLowerLetter(char ch)
    {
        return ch >= 'a' && ch <= 'z';
    }

    boolean isUpperLetter(char ch)
    {
        return ch >= 'A' && ch <= 'Z';
    }

    char toLower(char ch)
    {
        return isUpperLetter(ch) ? (char) (ch - 'A' + 'a') : ch;
    }

    char toUpper(char ch)
    {
        return isLowerLetter(ch) ? (char) (ch - 'a' + 'A') : ch;
    }

    String charArrayToString(char src[])
    {
        return String.valueOf(src);
    }

    String[] split(String raw)
    {
        return raw.split("[-_]");
    }

}
