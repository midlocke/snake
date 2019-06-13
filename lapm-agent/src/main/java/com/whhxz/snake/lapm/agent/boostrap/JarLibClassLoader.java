package com.whhxz.snake.lapm.agent.boostrap;


import com.whhxz.snake.lapm.core.constant.SnakeConstant;
import com.whhxz.snake.lapm.core.exception.SnakeException;
import com.whhxz.snake.lapm.core.util.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * JarLibClassLoader
 * Created by xuzhuo on 2019/6/12.
 */
public class JarLibClassLoader extends ClassLoader {
    private ClassLoader parent;
    private JarFile jarFile;

    public JarLibClassLoader(String jarPath, ClassLoader parent) {
        try {
            this.jarFile = new JarFile(jarPath);
        } catch (IOException e) {
            throw new SnakeException("agent jar not found");
        }
        this.parent = parent;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> clazz = findLoadedClass(name);
        if (clazz == null) {
            JarEntry jarEntry = jarFile.getJarEntry(SnakeConstant.LIBS_NAME + "/" + name.replace(".", "/") + ".class");
            if (jarEntry != null) {
                try {
                    InputStream inputStream = jarFile.getInputStream(jarEntry);
                    byte[] classByte = IOUtils.inputStreamToByteArrays(inputStream);
                    clazz = this.defineClass(name, classByte, 0, classByte.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (clazz == null) {
            clazz = parent.loadClass(name);
        }
        if (clazz == null) {
            clazz = super.loadClass(name, resolve);
        }
        return clazz;
    }
}
