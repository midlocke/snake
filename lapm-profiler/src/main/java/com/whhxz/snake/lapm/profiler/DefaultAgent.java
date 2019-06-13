package com.whhxz.snake.lapm.profiler;

import com.whhxz.snake.lapm.core.agent.Agent;
import com.whhxz.snake.lapm.core.conf.ProfilerConfig;

import java.lang.instrument.Instrumentation;

/**
 * DefaultAgent
 * Created by xuzhuo on 2019/6/12.
 */
public class DefaultAgent implements Agent {
    private ProfilerConfig config;

    public DefaultAgent(ProfilerConfig config, Instrumentation instrumentation) {
        this.config = config;
        instrumentation.addTransformer(new DefaultClassFileTransformerDispatcher());
    }

    @Override
    public void start() {
        System.out.println(config.getAgentJarPath());
    }

    @Override
    public void stop() {

    }
}
