package io.laudoak.config;

import io.laudoak.auxiliary.Arguments;
import io.laudoak.auxiliary.StringAuxiliary;

import static io.laudoak.auxiliary.StringAuxiliary.initialLetterLower;
import static io.laudoak.auxiliary.StringAuxiliary.initialLetterUpper;

/**
 * Created by laudoak on 16/12/10.
 * <p>
 * 命名规则类
 */
public class Naming {
    public static final String UNKNOWN = "unknown";

    /**
     * 从下划线式的表名转为驼峰式Java类名
     * eg.family_member => FamilyMember
     *
     * @param src 下划线式表名
     * @return 标准Java类名
     */
    public static String className(String src) {
        if (Arguments.isNullOrEmpty(src)) {
            return UNKNOWN;
        }
        String splits[] = StringAuxiliary.splitByUnderline(src);
        StringBuilder sb = new StringBuilder();
        for (String unit : splits) {
            if (!Arguments.isNullOrEmpty(unit)) {
                sb.append(initialLetterUpper(unit));
            }
        }

        return sb.toString();
    }

    /**
     * 从下划线式的表字段名转为驼峰式Java类属性名
     * eg.family_member => familyMember
     *
     * @param raw 表字段名
     * @return Java类属性名
     */
    public static String attributeName(String raw) {
        if (Arguments.isNullOrEmpty(raw)) {
            return UNKNOWN;
        }
        StringBuilder sb = new StringBuilder();
        String splits[] = StringAuxiliary.splitByUnderline(raw);
        boolean first = true;
        for (String s : splits) {
            if (first && !Arguments.isNullOrEmpty(s)) {
                sb.append(s);
                first = false;
            } else if (!Arguments.isNullOrEmpty(s)) {
                sb.append(initialLetterUpper(s));
            }
        }
        return sb.toString();
    }

    /**
     * 类名首字母小写,作为参数名
     * eg.Family => family
     *
     * @param raw 字符
     * @return model对应参数名
     */
    public static String modelAsParam(String raw) {
        if (Arguments.isNullOrEmpty(raw)) {
            return UNKNOWN;
        }
        return initialLetterLower(raw);
    }


}
