package com.lwc.activiti.dbentity;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author eddie.lee
 * @ProjectName activiti6-samples
 * @Package com.lwc.activiti.dbentity
 * @ClassName DbRepositoryTest
 * @description
 * @date created in 2019-01-06 17:07
 * @modified by
 */
public class DbRepositoryTest {
    private static final Logger logger = LoggerFactory.getLogger(DbRepositoryTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti-mysql.cfg.xml");

    /**
     * ACT_RE_DEPLOYMENT
     */
    @Test
    public void testDeploy() {
        activitiRule.getRepositoryService().createDeployment()
                .name("二次审批流程")
                .addClasspathResource("second_approve.bpmn20.xml")
                .deploy();
    }

    @Test
    public void testSuspend() {
        RepositoryService repositoryService = activitiRule.getRepositoryService();

        // ACT_RE_PROCDEF 的 ID
        repositoryService.suspendProcessDefinitionById("second_approve:1:5004");

        boolean suspended = repositoryService.isProcessDefinitionSuspended("second_approve:1:5004");

        //挂起状态，字段 SUSPENSION_STATE_ 修改为 2
        logger.info("suspended = {}", suspended);

    }


}
