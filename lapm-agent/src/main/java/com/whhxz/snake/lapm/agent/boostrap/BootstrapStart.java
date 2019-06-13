package com.whhxz.snake.lapm.agent.boostrap;


import com.whhxz.snake.lapm.agent.conf.ProfilerConfigLoader;
import com.whhxz.snake.lapm.core.agent.Agent;
import com.whhxz.snake.lapm.core.conf.ProfilerConfig;
import com.whhxz.snake.lapm.core.constant.SnakeConstant;
import com.whhxz.snake.lapm.core.exception.SnakeException;
import com.whhxz.snake.lapm.core.util.ThreadClassLoaderSwitchUtils;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Constructor;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * BootstrapStart
 * Created by xuzhuo on 2019/6/11.
 */
public class BootstrapStart {
    private ClassLoader classLoader;
    private Instrumentation instrumentation;

    public BootstrapStart(Instrumentation instrumentation) {
        this.instrumentation = instrumentation;
        ProfilerConfig profilerConfig = ProfilerConfigLoader.profilerConfig();
        String agentJarFullPath = profilerConfig.getAgentJarPath() + File.separator + profilerConfig.getAgentJarName();
        ClassLoader thisClassLoader = BootstrapStart.class.getClassLoader();
        if (System.getSecurityManager() != null) {
            this.classLoader = AccessController.doPrivileged((PrivilegedAction<ClassLoader>) () -> new JarLibClassLoader(agentJarFullPath, thisClassLoader));
        } else {
            this.classLoader = new JarLibClassLoader(agentJarFullPath, thisClassLoader);
        }
    }

    public boolean start() {
        try {
            Class<?> clazz = classLoader.loadClass(SnakeConstant.DEFAULT_AGENT);
            ProfilerConfig profilerConfig = ProfilerConfigLoader.profilerConfig();
            Object agent = ThreadClassLoaderSwitchUtils.excute(() -> {
                Constructor<?> constructor = clazz.getConstructor(ProfilerConfig.class, Instrumentation.class);
                return constructor.newInstance(profilerConfig, instrumentation);
            }, classLoader);
            if (agent instanceof Agent) {
                ((Agent) agent).start();
            } else {
                throw new SnakeException("Can't found agent");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }
}
