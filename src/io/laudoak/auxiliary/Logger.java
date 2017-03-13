package io.laudoak.auxiliary;

import java.util.Formatter;

/**
 * Created by laudoak on 16/12/8.
 * <p>
 * 简单日志输出
 */
public class Logger {
    private static final Formatter formatter = new Formatter(System.out);

    public static void info(String tag, String format, Object... args) {
        print("INFO", tag, format, args);
    }

    public static void error(String tag, String format, Object... args) {
        print("ERROR", tag, format, args);
    }

    private static void print(String level, String tag, String format, Object... args) {
        String info = String.format(format, args);
        formatter.format("%-5s [%-10s]: %-6s\n", level, tag, info);
    }
}
