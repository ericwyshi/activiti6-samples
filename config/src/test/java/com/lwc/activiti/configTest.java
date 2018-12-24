package com.lwc.activiti;

import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

/**
 * @author liweicheng
 * @ProjectName activiti6-samples
 * @Package com.lwc.activiti
 * @ClassName configTest
 * @description
 * @date created in 2018-12-24 16:10
 * @modified by
 */
public class configTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(configTest.class);

    @Test
    public void testConfig1() {
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResourceDefault();
        LOGGER.info("configuration = {}", configuration);
    }

    @Test
    public void testConfig2() {
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration();
        //流程引擎对象
        LOGGER.info("configuration = {}", configuration);
    }

}
