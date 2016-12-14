package me.laudoak.util;

/**
 * Created by laudoak on 16/12/10.
 */
public class StringFormat
{


    public static String formatClzHead(String clzAccessModifier, String clzName, String clzComment)
    {
        return String.format("%s class %-32s //%-10s\n{\n", clzAccessModifier, clzName, clzComment);
    }

    public static String formatAttributeRow(String attAccessModifier, String attType, String att, String attComment)
    {
        return String.format("\t%-8s %-9s %-22s //%-10s\n", attAccessModifier, attType, att + ";", attComment);
    }
}
