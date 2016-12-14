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

    String standardClassName(String src)
    {
        if (Arguments.isNullOrEmpty(src))
        {
            return UNKNOWN;
        }

        String splits[] = split(src);
        StringBuilder sb = new StringBuilder();
        for (String unit : splits)
        {
            if (!Arguments.isNullOrEmpty(unit))
            {
                sb.append(wordInitialCapital(unit));
            }
        }

        return sb.toString();
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

    String wordInitialCapital(String raw)
    {
        if (Arguments.isNullOrEmpty(raw))
        {
            return UNKNOWN;
        }

        char chs[] = raw.toCharArray();
        chs[0] = toUpper(chs[0]);
        return charArrayToString(chs);
    }

}
