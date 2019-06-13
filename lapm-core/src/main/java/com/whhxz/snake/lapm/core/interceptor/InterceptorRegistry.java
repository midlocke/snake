package com.whhxz.snake.lapm.core.interceptor;

import com.whhxz.snake.lapm.core.exception.SnakeException;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * InterceptorRegistry
 * Created by xuzhuo on 2019/6/13.
 */
public class InterceptorRegistry {
    private static MethodAroundInterceptor[] methodAroundInterceptors = new MethodAroundInterceptor[1024];
    private static final AtomicInteger index = new AtomicInteger(0);

    public static int registry(MethodAroundInterceptor interceptor) {
        int res = index.getAndIncrement();
        if (res >= methodAroundInterceptors.length) {
            throw new SnakeException("max interceptor length is" + methodAroundInterceptors.length);
        }
        methodAroundInterceptors[res] = interceptor;
        return res;
    }

    public static MethodAroundInterceptor findInterceptor(int index) {
        return methodAroundInterceptors[index];
    }
}
