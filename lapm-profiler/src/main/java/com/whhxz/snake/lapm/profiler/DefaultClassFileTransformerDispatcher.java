package com.whhxz.snake.lapm.profiler;

import com.whhxz.snake.lapm.core.util.StringUtils;
import com.whhxz.snake.lapm.profiler.instrument.ByteCodeInstrument;
import com.whhxz.snake.lapm.profiler.instrument.javassist.JavaAssistByteCodeInstrument;
import com.whhxz.snake.lapm.profiler.intercept.ClassIntercept;
import com.whhxz.snake.lapm.profiler.intercept.spring.SpringFrameworkServletIntercept;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.HashSet;
import java.util.Set;

/**
 * DefaultClassFileTransformerDispatcher
 * Created by xuzhuo on 2019/6/13.
 */
public class DefaultClassFileTransformerDispatcher implements ClassFileTransformer {
    private ByteCodeInstrument byteCodeInstrument;
    private Set<ClassIntercept> intercepts;

    public DefaultClassFileTransformerDispatcher() {
        byteCodeInstrument = new JavaAssistByteCodeInstrument();
        intercepts = new HashSet<>();
        this.registryClassIntercept();
    }

    @Override
    public byte[] transform(ClassLoader loader, String jvmClassName, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classFileBuffer) {
        String javaClassName = StringUtils.jvmName2JavaName(jvmClassName);
        return intercepts.stream().filter(d -> d.accept(javaClassName))
                .findFirst()
                .map(d -> d.handler(loader, javaClassName, classBeingRedefined, protectionDomain, classFileBuffer))
                .orElse(new byte[0]);
    }

    private void registryClassIntercept() {
        intercepts.add(new SpringFrameworkServletIntercept(this.byteCodeInstrument));
    }
}
