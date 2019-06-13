package com.whhxz.snake.lapm.profiler.instrument;

/**
 * ByteCodeInstrument
 * Created by xuzhuo on 2019/6/13.
 */
public interface ByteCodeInstrument {
    InstrumentClass getClass(String className);
}
