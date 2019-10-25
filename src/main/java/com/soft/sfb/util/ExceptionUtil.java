package com.soft.sfb.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @author: 尚发宝
 * @date: 2018/5/9 12:13
 */

public class ExceptionUtil {

    public static String getMsg(Throwable throwable) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream pout = new PrintStream(out);
        throwable.printStackTrace(pout);
        String ret = new String(out.toByteArray());
        pout.close();
        try {
            out.close();
        } catch (Exception e) {
            return "";
        }
        return ret;
    }
}
