package com.whhxz.snake.lapm.profiler.intercept;

import java.security.ProtectionDomain;

/**
 * ClassIntercept
 * Created by xuzhuo on 2019/6/13.
 */
public interface ClassIntercept {
    /**
     * 判断是否处理
     *
     * @param className
     * @return
     */
    boolean accept(String className);

    /**
     * 处理逻辑
     *
     * @param loader
     * @param className
     * @param classBeingRedefined
     * @param protectionDomain
     * @param classFileBuffer
     * @return
     */
    byte[] handler(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classFileBuffer);
}
