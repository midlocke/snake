package com.whhxz.snake.lapm.profiler.intercept.spring;

import com.whhxz.snake.lapm.profiler.instrument.ByteCodeInstrument;
import com.whhxz.snake.lapm.profiler.instrument.InstrumentClass;
import com.whhxz.snake.lapm.profiler.intercept.AbsClassIntercept;
import com.whhxz.snake.lapm.profiler.interceptor.MethodCostAroundInterceptor;

import java.security.ProtectionDomain;

/**
 * SpringFrameworkServletIntercept
 * Created by xuzhuo on 2019/6/13.
 */
public class SpringFrameworkServletIntercept extends AbsClassIntercept {
    public SpringFrameworkServletIntercept(ByteCodeInstrument byteCodeInstrument) {
        super(byteCodeInstrument);
        classNames.add("org.springframework.web.servlet.FrameworkServlet");
    }

    @Override
    public byte[] handler(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classFileBuffer) {
        InstrumentClass instrumentClass = byteCodeInstrument.getClass(className);
        instrumentClass.addInterceptor("processRequest",
                new String[]{"javax.servlet.http.HttpServletRequest", "javax.servlet.http.HttpServletResponse"},
                new MethodCostAroundInterceptor());
        return instrumentClass.toByteCode();
    }
}
