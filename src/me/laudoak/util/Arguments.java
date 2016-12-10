package me.laudoak.util;

import java.util.Collection;

/**
 * Created by laudoak on 16/12/10.
 */
public class Arguments
{
    public static <T extends Collection> boolean isNullOrEmpty(T t)
    {
        return isNull(t) || isEmpty(t);
    }

    public static boolean isNull(Object o)
    {
        return o == null;
    }

    public static <T extends Collection> boolean isEmpty(T t)
    {
        return t.isEmpty();
    }

    public static boolean isNullOrEmpty(String s)
    {
        return s == null || s.equals("");
    }

    public static boolean isPositive(Number n)
    {
        return n != null && n.doubleValue() > 0.0D;
    }

    public static boolean isNegative(Number n)
    {
        return n != null && n.doubleValue() < 0.0D;
    }

    public static <T> boolean equalWith(T source, T target)
    {
        return source.equals(target);
    }

    public static boolean isDecimal(String str)
    {
        char[] var1 = str.toCharArray();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3)
        {
            char c = var1[var3];
            if (c < 48 || c > 57)
            {
                return false;
            }
        }

        return true;
    }

    public static boolean isNumberic(String str)
    {
        boolean dot = false;
        char[] var2 = str.toCharArray();
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4)
        {
            char c = var2[var4];
            if (c == 46 && !dot)
            {
                dot = true;
            } else
            {
                if (c == 46)
                {
                    return false;
                }

                if (c < 48 || c > 57)
                {
                    return false;
                }
            }
        }

        return true;
    }
}
