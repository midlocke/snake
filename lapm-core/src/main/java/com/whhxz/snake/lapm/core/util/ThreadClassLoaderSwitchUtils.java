package com.whhxz.snake.lapm.core.util;

import com.whhxz.snake.lapm.core.exception.SnakeException;

import java.util.concurrent.Callable;

/**
 * ThreadClassLoaderSwitchUtils
 * Created by xuzhuo on 2019/6/12.
 */
public class ThreadClassLoaderSwitchUtils {
    /**
     * 切换当前线程ClassLoader
     *
     * @param callable
     * @param classLoader
     * @param <V>
     * @return
     */
    public static <V> V excute(Callable<V> callable, ClassLoader classLoader) {
        Thread currentThread = Thread.currentThread();
        ClassLoader currentClassCloader = currentThread.getContextClassLoader();
        try {
            currentThread.setContextClassLoader(classLoader);
            return callable.call();
        } catch (Exception e) {
            throw new SnakeException(e);
        } finally {
            currentThread.setContextClassLoader(currentClassCloader);
        }
    }

}
