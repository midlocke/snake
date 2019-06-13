package com.whhxz.snake.lapm.profiler.instrument.javassist;

import com.whhxz.snake.lapm.core.interceptor.InterceptorRegistry;
import com.whhxz.snake.lapm.core.interceptor.MethodAroundInterceptor;
import com.whhxz.snake.lapm.profiler.instrument.InstrumentClass;
import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

import java.io.IOException;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * JavaAssistInstrumentClass
 * Created by xuzhuo on 2019/6/13.
 */
public class JavaAssistInstrumentClass implements InstrumentClass {
    private CtClass ctClass;

    public JavaAssistInstrumentClass(CtClass ctClass) {
        this.ctClass = ctClass;
    }

    @Override
    public void addInterceptor(String method, String[] methodArgs, MethodAroundInterceptor interceptor) {
        try {
            CtMethod[] methods = ctClass.getDeclaredMethods(method);
            Stream.of(methods).filter(m -> {
                try {
                    //判断参数是否一致
                    CtClass[] parameterTypes = m.getParameterTypes();
                    if (parameterTypes.length == methodArgs.length) {
                        if (IntStream.range(0, parameterTypes.length)
                                .allMatch(i -> parameterTypes[i].getName().equals(methodArgs[i]))) {
                            return true;
                        }
                    }
                } catch (NotFoundException e) {
                    e.printStackTrace();
                }
                return false;
            }).findFirst().ifPresent(m -> {
                try {
                    int index = InterceptorRegistry.registry(interceptor);
                    m.insertBefore(String.format("%s.findInterceptor(%d).before($0, $args);", InterceptorRegistry.class.getName(), index));
                    m.insertAfter(String.format("%s.findInterceptor(%d).after($0, $_, $args);", InterceptorRegistry.class.getName(), index));
                } catch (CannotCompileException e) {
                    e.printStackTrace();
                }
            });

        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] toByteCode() {
        try {
            byte[] bytes = ctClass.toBytecode();
            ctClass.detach();
            return bytes;
        } catch (IOException | CannotCompileException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
