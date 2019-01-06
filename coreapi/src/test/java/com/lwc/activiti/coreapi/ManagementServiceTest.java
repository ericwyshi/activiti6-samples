package com.lwc.activiti.coreapi;

import com.lwc.activiti.mapper.MyCustomMapper;
import org.activiti.engine.ManagementService;
import org.activiti.engine.impl.cmd.AbstractCustomSqlExecution;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.management.TablePage;
import org.activiti.engine.runtime.DeadLetterJobQuery;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.JobQuery;
import org.activiti.engine.runtime.SuspendedJobQuery;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @author eddie.lee
 * @ProjectName activiti6-samples
 * @Package com.lwc.activiti.coreapi
 * @ClassName ManagementServiceTest
 * @description
 * @date created in 2019-01-06 15:52
 * @modified by
 */
    public class ManagementServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(ManagementServiceTest.class);
    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    @Test
    @Deployment(resources = {"my-process-form.bpmn20.xml"})
    public void testJobQuery() {
        ManagementService managementService = activitiRule.getManagementService();

        List<Job> timerJobList = managementService.createTimerJobQuery().listPage(0, 100);
        for (Job timerJob : timerJobList) {
            logger.info("timerJob = {}", timerJob);
        }

        JobQuery jobQuery = managementService.createJobQuery();

        SuspendedJobQuery suspendedJobQuery = managementService.createSuspendedJobQuery();
        DeadLetterJobQuery deadLetterJobQuery = managementService.createDeadLetterJobQuery();
    }


    @Test
    @Deployment(resources = {"my-process-job.bpmn20.xml"})
    public void testTablePageQuery() {
        ManagementService managementService = activitiRule.getManagementService();
        TablePage tablePage = managementService.createTablePageQuery()
                .tableName(managementService.getTableName(ProcessDefinitionEntity.class))
                .listPage(0, 100);

        List<Map<String, Object>> rows = tablePage.getRows();
        for (Map<String, Object> row : rows) {
            logger.info("row = {}", row);
        }

    }

    // 自定义 sql
    @Test
    @Deployment(resources = {"my-process.bpmn20.xml"})
    public void testCustomSql() {
        activitiRule.getRuntimeService().startProcessInstanceByKey("my-process");
        ManagementService managementService = activitiRule.getManagementService();
        List<Map<String, Object>> mapList = managementService
                .executeCustomSql(new AbstractCustomSqlExecution<MyCustomMapper, List<Map<String, Object>>>(MyCustomMapper.class) {
                    @Override
                    public List<Map<String, Object>> execute(MyCustomMapper o) {
                        return o.findAll();
                    }
                });

        for (Map<String, Object> map : mapList) {
            logger.info("map = {}", map);
        }
    }

    @Test
    @Deployment(resources = {"my-process.bpmn20.xml"})
    public void testCommand() {
        activitiRule.getRuntimeService().startProcessInstanceByKey("my-process");
        ManagementService managementService = activitiRule.getManagementService();

        ProcessDefinitionEntity processDefinitionEntity = managementService.executeCommand(new Command<ProcessDefinitionEntity>() {

            @Override
            public ProcessDefinitionEntity execute(CommandContext commandContext) {
                ProcessDefinitionEntity processDefinitionEntity = commandContext.getProcessDefinitionEntityManager()
                        .findLatestProcessDefinitionByKey("my-process");
                return processDefinitionEntity;
            }
        });

        logger.info("processDefinitionEntity = {}", processDefinitionEntity);
    }

}
