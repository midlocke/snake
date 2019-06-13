package com.whhxz.snake.lapm.agent;

import com.whhxz.snake.lapm.agent.boostrap.BootstrapStart;
import com.whhxz.snake.lapm.agent.conf.ProfilerConfigLoader;

import java.lang.instrument.Instrumentation;
import java.util.logging.Logger;

/**
 * SnakeAgent
 * Created by xuzhuo on 2019/6/7.
 */
public class SnakeAgent {
    private static final Logger logger = Logger.getLogger(SnakeAgent.class.getName());

    public static void premain(String args, Instrumentation instrumentation) {
        logger.info("启动agent");
        ProfilerConfigLoader.init();
        if (new BootstrapStart(instrumentation).start()) {
            logger.info("启动成功");
        }
    }
}
