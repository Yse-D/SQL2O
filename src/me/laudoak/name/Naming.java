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

    /**
     * 字符串转标准类名
     *
     * @param src 字符串
     * @return 标准类名
     */
    public String standardClassName(String src)
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

    public String hump(String raw)
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
                sb.append(wordInitialCapital(s));
            }
        }

        return sb.toString();
    }

    public String wordInitialCapital(String raw)
    {
        if (Arguments.isNullOrEmpty(raw))
        {
            return UNKNOWN;
        }

        char chs[] = raw.toCharArray();
        chs[0] = toUpper(chs[0]);
        return charArrayToString(chs);
    }

    public String stringInitialLower(String raw)
    {
        if (Arguments.isNullOrEmpty(raw))
        {
            return UNKNOWN;
        }

        char chas[] = raw.toCharArray();
        if (chas.length > 0)
        {
            chas[0] = toLower(chas[0]);
        }
        return charArrayToString(chas);
    }

    /**
     * @param ch 字符
     * @return 是否是英文字母
     */
    boolean isLetter(char ch)
    {
        return isUpperLetter(ch) || isLowerLetter(ch);
    }

    /**
     * @param ch 字符
     * @return 是否是小写字母
     */
    boolean isLowerLetter(char ch)
    {
        return ch >= 'a' && ch <= 'z';
    }

    /**
     * @param ch 字符
     * @return 是否是大写字母
     */
    boolean isUpperLetter(char ch)
    {
        return ch >= 'A' && ch <= 'Z';
    }

    /**
     * @param ch 字符
     * @return 小写字母
     */
    char toLower(char ch)
    {
        return isUpperLetter(ch) ? (char) (ch - 'A' + 'a') : ch;
    }

    /**
     * @param ch 字符
     * @return 大写字母
     */
    char toUpper(char ch)
    {
        return isLowerLetter(ch) ? (char) (ch - 'a' + 'A') : ch;
    }

    /**
     * @param src 字符数组
     * @return 字符串
     */
    protected String charArrayToString(char src[])
    {
        return String.valueOf(src);
    }

    /**
     * @param raw 带分割字符串
     * @return 字符串数组
     */
    protected String[] split(String raw)
    {
        return raw.split("[-_]");
    }

    /**
     * @param raw 字符串
     * @return 首字母大写字符串
     */

}
