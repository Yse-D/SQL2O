package io.laudoak.auxiliary;

/**
 * Created by laudoak on 16/12/10.
 */
public class StringFormatter {
    public static String formatClzHead(String clzAccessModifier, String clzName, String clzComment) {
        return String.format("%s class %s //%s\n{\n", clzAccessModifier, clzName, clzComment);
    }

    public static String formatAttributeRow(String attAccessModifier, String attType, String att, String attComment) {
        return String.format("\t%s %s %s //%s\n", attAccessModifier, attType, att + ";", attComment);
    }
}
