package com.whhxz.snake.lapm.profiler.instrument;

import com.whhxz.snake.lapm.core.interceptor.MethodAroundInterceptor;

/**
 * InstrumentClass
 * Created by xuzhuo on 2019/6/13.
 */
public interface InstrumentClass {
    void addInterceptor(String method, String[] methodArgs, MethodAroundInterceptor interceptor);

    byte[] toByteCode();
}
