package com.lwc.activiti.config;

import org.activiti.engine.logging.LogMDC;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author eddie.lee
 * @ProjectName activiti6-samples
 * @Package com.lwc.activiti.config
 * @ClassName ConfigInterceptorTest
 * @description
 * @date created in 2018-12-25 15:12
 * @modified by
 */
public class ConfigInterceptorTest {
    private static final Logger logger = LoggerFactory.getLogger(ConfigInterceptorTest.class);
    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti_interceptor.cfg.xml");

    @Test
    @Deployment(resources = {"com/lwc/activiti/my-process.bpmn20.xml"})
    public void test() {
        // 打开 MDC
        LogMDC.setMDCEnabled(true);
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("my-process");
        assertNotNull(processInstance);

        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        assertEquals("Activiti is awesome!", task.getName());
        activitiRule.getTaskService().complete(task.getId());
    }

}