package com.lwc.activiti.interceptor;

import org.activiti.engine.impl.interceptor.AbstractCommandInterceptor;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author eddie.lee
 * @ProjectName activiti6-samples
 * @Package com.lwc.activiti.interceptor
 * @ClassName DurationCommandInterceptor
 * @description 执行时间的拦截器
 * @date created in 2018-12-25 15:14
 * @modified by
 */
public class DurationCommandInterceptor extends AbstractCommandInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(DurationCommandInterceptor.class);

    @Override
    public <T> T execute(CommandConfig config, Command<T> command) {
        long start = System.currentTimeMillis();
        try {
            return this.next.execute(config, command);
        } finally {
            long duration = System.currentTimeMillis() - start;
            logger.info("{},执行时长{} 毫秒", command.getClass().getSimpleName(), duration);
        }
    }
}