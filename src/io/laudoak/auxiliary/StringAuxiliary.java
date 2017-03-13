package io.laudoak.auxiliary;

/**
 * Created by laudoak on 17/3/8.
 * <p>
 * 字符和字符串处理
 */
public class StringAuxiliary {
    public static final String UNKNOWN = "unknown";

    /**
     * @param ch 字符
     * @return 是否是英文字母
     */
    public static boolean isLetter(char ch) {
        return isUpperLetter(ch) || isLowerLetter(ch);
    }

    /**
     * @param ch 字符
     * @return 是否是小写字母
     */
    public static boolean isLowerLetter(char ch) {
        return ch >= 'a' && ch <= 'z';
    }

    /**
     * @param ch 字符
     * @return 是否是大写字母
     */
    public static boolean isUpperLetter(char ch) {
        return ch >= 'A' && ch <= 'Z';
    }

    /**
     * @param ch 字符
     * @return 小写字母
     */
    public static char toLower(char ch) {
        return isUpperLetter(ch) ? (char) (ch - 'A' + 'a') : ch;
    }

    /**
     * @param ch 字符
     * @return 大写字母
     */
    public static char toUpper(char ch) {
        return isLowerLetter(ch) ? (char) (ch - 'a' + 'A') : ch;
    }

    /**
     * @param src 字符数组
     * @return 字符串
     */
    public static String charArrayToString(char src[]) {
        return String.valueOf(src);
    }

    /**
     * @param raw 带分割字符串
     * @return 字符串数组
     */
    public static String[] splitByUnderline(String raw) {
        return raw.split("[-_]");
    }

    /**
     * 字符串首字母大写
     *
     * @param raw 字符串
     * @return 首字符是字母时返回首字母大写字符串, 否则原字符串返回
     */
    public static String initialLetterUpper(String raw) {
        if (Arguments.isNullOrEmpty(raw)) {
            return UNKNOWN;
        }
        char chs[] = raw.toCharArray();
        if (!isLetter(chs[0])) {
            return raw;
        }
        chs[0] = StringAuxiliary.toUpper(chs[0]);
        return StringAuxiliary.charArrayToString(chs);
    }

    /**
     * 字符串首字母小写
     *
     * @param raw 字符串
     * @return 首字符是字母时返回首字母小写字符串, 否则原字符串返回
     */
    public static String initialLetterLower(String raw) {
        if (Arguments.isNullOrEmpty(raw)) {
            return UNKNOWN;
        }
        char chs[] = raw.toCharArray();
        if (!isLetter(chs[0])) {
            return raw;
        }
        if (chs.length > 0) {
            chs[0] = StringAuxiliary.toLower(chs[0]);
        }
        return StringAuxiliary.charArrayToString(chs);
    }


}
