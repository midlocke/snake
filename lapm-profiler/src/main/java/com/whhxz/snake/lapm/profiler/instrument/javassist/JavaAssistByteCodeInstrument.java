package com.whhxz.snake.lapm.profiler.instrument.javassist;

import com.whhxz.snake.lapm.core.exception.SnakeException;
import com.whhxz.snake.lapm.profiler.instrument.ByteCodeInstrument;
import com.whhxz.snake.lapm.profiler.instrument.InstrumentClass;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

/**
 * JavaAssistByteCodeInstrument
 * JavaAssist封装
 * Created by xuzhuo on 2019/6/13.
 */
public class JavaAssistByteCodeInstrument implements ByteCodeInstrument {
    private ClassPool classPool;

    public JavaAssistByteCodeInstrument() {
        this.classPool = new ClassPool(true);
    }

    @Override
    public InstrumentClass getClass(String className) {
        try {
            CtClass ctClass = classPool.get(className);
            return new JavaAssistInstrumentClass(ctClass);
        } catch (NotFoundException e) {
            throw new SnakeException(e);
        }
    }
}
