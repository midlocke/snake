package com.whhxz.snake.lapm.profiler.interceptor;

import com.whhxz.snake.lapm.core.interceptor.MethodAroundInterceptor;

/**
 * MethodCostAroundInterceptor
 * Created by xuzhuo on 2019/6/13.
 */
public class MethodCostAroundInterceptor implements MethodAroundInterceptor {
    private final static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    @Override
    public void before(Object target, Object... args) {
        threadLocal.set(System.currentTimeMillis());
    }

    @Override
    public void after(Object target, Object result, Object... args) {
        System.out.println("~~~耗时：" + (System.currentTimeMillis() - threadLocal.get()));
        threadLocal.remove();
    }
}
