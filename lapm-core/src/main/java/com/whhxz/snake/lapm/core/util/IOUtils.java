package com.whhxz.snake.lapm.core.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * IOUtils
 * Created by xuzhuo on 2019/6/13.
 */
public class IOUtils {
    /**
     * inputStream to byteso
     *
     * @param in
     * @return
     */
    public static byte[] inputStreamToByteArrays(InputStream in) throws IOException {
        try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream()) {
            int len;
            byte[] buffer = new byte[10 * 2048];
            while ((len = in.read(buffer)) != -1) {
                byteOut.write(buffer, 0, len);
            }
            return byteOut.toByteArray();
        }
    }
}

