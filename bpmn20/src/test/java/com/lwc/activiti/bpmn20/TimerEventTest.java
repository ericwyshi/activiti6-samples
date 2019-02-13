package com.lwc.activiti.bpmn20;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
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
 * @description 定时任务
 * @date created in 2019-02-12 15:25
 * @modified by
 */
public class TimerEventTest {

    private static final Logger logger = LoggerFactory.getLogger(TimerEventTest.class);


    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    @Test
    @Deployment(resources = {"my-process-timer-boundary.bpmn20.xml"})
    public void testTimerBoundary() throws InterruptedException {
        // 启动流程,启动之后会得到流程实例对象,但不需要使用
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("my-process");
        // 分页查询
        List<Task> tasks = activitiRule.getTaskService().createTaskQuery().listPage(0, 100);
        for (Task task : tasks) {
            logger.info("task.name = {}", task.getName());
        }
        logger.info("tasks.size = {}", tasks.size());
        //睡眠 15秒
        Thread.sleep(1000 * 15);

        tasks = activitiRule.getTaskService().createTaskQuery().listPage(0, 100);
        for (Task task : tasks) {
            logger.info("task.name = {}", task.getName());
        }
        logger.info("tasks.size = {}", tasks.size());

    }

}
