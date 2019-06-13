package com.whhxz.snake.lapm.agent.conf;

import com.whhxz.snake.lapm.core.conf.ProfilerConfig;

import java.io.File;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * ProfilerConfigLoader
 * Created by xuzhuo on 2019/6/12.
 */
public class ProfilerConfigLoader {
    private static final Pattern DEFAULT_AGENT_PATTERN = Pattern.compile("lapm-agent(-[0-9]+(\\.[0-9]+)+((-SNAPSHOT)|(-RC[0-9]+))?)?.jar");
    private static ProfilerConfig profilerConfig = new ProfilerConfig();


    public static void init() {
        String classPath = System.getProperty("java.class.path");
        Matcher matcher = DEFAULT_AGENT_PATTERN.matcher(classPath);
        if (!matcher.find()) {
            return;
        }
        String agentJarName = classPath.substring(matcher.start(), matcher.end());
        profilerConfig.setAgentJarName(agentJarName);
        Optional<String> first = Stream.of(classPath.split(File.pathSeparator))
                .filter(d -> d.contains(agentJarName))
                .findFirst();
        if (first.isPresent()) {
            String path = first.get();
            String agentJarPath = path.substring(0, path.length() - agentJarName.length());
            profilerConfig.setAgentJarPath(agentJarPath);
        }
    }

    public static ProfilerConfig profilerConfig() {
        return profilerConfig;
    }
}
