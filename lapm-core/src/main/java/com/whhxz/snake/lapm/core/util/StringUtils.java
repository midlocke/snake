package com.whhxz.snake.lapm.core.util;

/**
 * StringUtils
 * Created by xuzhuo on 2019/6/13.
 */
public class StringUtils {
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String jvmName2JavaName(String jvmName) {
        if (isNotEmpty(jvmName)) {
            return jvmName.replace("/", ".");
        }
        return jvmName;
    }
}
