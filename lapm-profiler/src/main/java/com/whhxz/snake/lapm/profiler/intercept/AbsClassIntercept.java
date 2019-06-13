package com.whhxz.snake.lapm.profiler.intercept;

import com.whhxz.snake.lapm.profiler.instrument.ByteCodeInstrument;

import java.util.HashSet;
import java.util.Set;

/**
 * AbsClassIntercept
 * Created by xuzhuo on 2019/6/13.
 */
public abstract class AbsClassIntercept implements ClassIntercept {
    protected Set<String> classNames = new HashSet<>();
    protected ByteCodeInstrument byteCodeInstrument;

    public AbsClassIntercept(ByteCodeInstrument byteCodeInstrument) {
        this.byteCodeInstrument = byteCodeInstrument;
    }

    @Override
    public boolean accept(String className) {
        return classNames.contains(className);
    }
}
