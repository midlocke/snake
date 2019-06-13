package com.whhxz.snake.lapm.core.interceptor;

/**
 * MethodAroundInterceptor
 * 方法周围拦截
 * Created by xuzhuo on 2019/6/12.
 */
public interface MethodAroundInterceptor extends Interceptor {
    /**
     * 进入方法前
     *
     * @param target 目标类
     * @param args   参数
     */
    void before(Object target, Object... args);

    /**
     * 方法结束后
     *
     * @param target 目标
     * @param result 返回值
     * @param args   参数
     */
    void after(Object target, Object result, Object... args);
}
