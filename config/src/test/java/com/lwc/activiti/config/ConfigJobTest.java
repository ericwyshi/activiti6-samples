package com.lwc.activiti.config;

import org.activiti.engine.runtime.Job;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author eddie.lee
 * @ProjectName activiti6-samples
 * @Package com.lwc.activiti.config
 * @ClassName ConfigJobTest
 * @description
 * @date created in 2018-12-25 15:30
 * @modified by
 */
public class ConfigJobTest {
    private static final Logger logger = LoggerFactory.getLogger(ConfigJobTest.class);
    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti_job.cfg.xml");

    @Test
    @Deployment(resources = {"com/lwc/activiti/my-process_job.bpmn20.xml"})
    public void test() throws InterruptedException {
        logger.info("start");
        List<Job> jobs = activitiRule
                .getManagementService()
                .createTimerJobQuery()
                .listPage(0, 100);


        for (Job job : jobs) {
            logger.info("定时任务 = {}，默认重试次数 = {}",job,job.getRetries());
        }
        logger.info("jobs size = {}",jobs.size());
        Thread.sleep(1000 * 100);
        logger.info("end");
    }

}
