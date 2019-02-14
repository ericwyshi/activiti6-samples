package com.lwc.activiti.bpmn20;

import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author eddie.lee
 * @ProjectName activiti6-samples
 * @description 用户任务
 * @date created in 2019-02-12 15:25
 * @modified by
 */
public class UserTaskTest {

    private static final Logger logger = LoggerFactory.getLogger(UserTaskTest.class);


    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    @Test
    @Deployment(resources = {"my-process-usertask.bpmn20.xml"})
    public void testUserTask() {

        ProcessInstance processInstance = activitiRule.getRuntimeService()
                .startProcessInstanceByKey("my-process");
        TaskService taskService = activitiRule.getTaskService();
        Task task = taskService.createTaskQuery().taskCandidateUser("user1").singleResult();
        logger.info("find by user1 task = {}", task);

        task = taskService.createTaskQuery().taskCandidateUser("user2").singleResult();
        logger.info("find by user2 task = {}", task);

        task = taskService.createTaskQuery().taskCandidateGroup("group1").singleResult();
        logger.info("find by group1 task = {}", task);

        // 代理人
        taskService.claim(task.getId(), "user2");
        logger.info("claim task.id = {} by user2", task.getId());

        // 不推荐, 这个方法不会校验
        // taskService.setAssignee(task.getId(),"user2");

        //推荐  使用候选人或者指定委托人
        task = taskService.createTaskQuery().taskCandidateOrAssigned("user1").singleResult();
        logger.info("find by user1 task ={}", task);
        task = taskService.createTaskQuery().taskCandidateOrAssigned("user2").singleResult();
        logger.info("find by user2 task ={}", task);
    }
    @Test
    @Deployment(resources = {"my-process-usertask2.bpmn20.xml"})
    public void testUserTask2() {
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("my-process");

        TaskService taskService = activitiRule.getTaskService();

        // 查询候选人或者指定委托人
        Task task = taskService.createTaskQuery().taskCandidateUser("user1").singleResult();
        logger.info("find by user1 task ={}", task);
        taskService.complete(task.getId());
    }
}
