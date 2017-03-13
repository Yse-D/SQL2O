package io.laudoak.auxiliary;

import java.util.Collection;

/**
 * Created by laudoak on 16/12/10.
 * <p>
 * 参数校验辅助类
 */
public class Arguments {
    public static <T extends Collection> boolean isNullOrEmpty(T t) {
        return isNull(t) || isEmpty(t);
    }

    public static boolean isNull(Object o) {
        return o == null;
    }

    public static <T extends Collection> boolean isEmpty(T t) {
        return null == t || t.isEmpty();
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.equals("");
    }

    public static boolean isPositive(Number n) {
        return n != null && n.doubleValue() > 0.0D;
    }

    public static boolean isNegative(Number n) {
        return n != null && n.doubleValue() < 0.0D;
    }

    public static <T> boolean equalWith(T source, T target) {
        return source.equals(target);
    }

}
