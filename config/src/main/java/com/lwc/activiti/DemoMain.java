package com.lwc.activiti;

import com.google.common.collect.Maps;
import org.activiti.engine.*;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.form.DateFormType;
import org.activiti.engine.impl.form.StringFormType;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author eddie.lee
 * @ProjectName activiti6-helloworld
 * @Package com.lwc.activiti.helloworld
 * @ClassName DemoMain
 * @description
 * @date created in 2018-12-15 19:43
 * @modified by
 */
public class DemoMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoMain.class);

    public static void main(String[] args) throws ParseException {
        LOGGER.info("启动我们的程序");
        // 创建流程引擎
        ProcessEngine processEngine = getProcessEngine();

        // 部署流程定义文件
        ProcessDefinition processDefinition = getProcessDefinition(processEngine);

        // 启动运行流程
        ProcessInstance processInstance = getProcessInstance(processEngine, processDefinition);

        // 处理流程任务
        processTask(processEngine, processInstance);
        LOGGER.info("结束我们的程序");
    }

    private static void processTask(ProcessEngine processEngine, ProcessInstance processInstance) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        while (processInstance != null && !processInstance.isEnded()) {
            TaskService taskService = processEngine.getTaskService();
            List<Task> list = taskService.createTaskQuery().list();
            LOGGER.info("待处理任务数量 [{}] ", list.size());
            for (Task task : list) {
                LOGGER.info("待处理任务 [{}]", task.getName());
                Map<String, Object> variables = getMap(processEngine, scanner, task);
                taskService.complete(task.getId(), variables);
                processInstance = processEngine.getRuntimeService()
                        .createProcessInstanceQuery()
                        .processInstanceId(processInstance.getId())
                        .singleResult();
            }
        }
        scanner.close();
    }

    private static Map<String, Object> getMap(ProcessEngine processEngine, Scanner scanner, Task task) throws ParseException {
        FormService formService = processEngine.getFormService();
        TaskFormData taskFormData = formService.getTaskFormData(task.getId());
        List<FormProperty> formProperties = taskFormData.getFormProperties();
        Map<String, Object> variables = Maps.newHashMap();
        for (FormProperty property : formProperties) {
            String line = null;
            //isInstance
            if (property.getType() instanceof StringFormType) {
                LOGGER.info("请输入 {} ?", property.getName());
                line = scanner.nextLine();
                variables.put(property.getId(), line);
            } else if (property.getType() instanceof DateFormType) {
                LOGGER.info("请输入 {} ? 格式 (yyyy-MM-dd)", property.getName());
                line = scanner.nextLine();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = simpleDateFormat.parse(line);
                variables.put(property.getId(), date);
            } else {
                LOGGER.info("类型暂时不支持 {}", property.getType());
            }
            LOGGER.info("您输入的内容是 [{}]", line);
        }
        return variables;
    }

    private static ProcessInstance getProcessInstance(ProcessEngine processEngine, ProcessDefinition processDefinition) {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());
        LOGGER.info("启动流程 [{}]", processInstance.getProcessDefinitionKey());
        return processInstance;
    }

    private static ProcessDefinition getProcessDefinition(ProcessEngine processEngine) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        deploymentBuilder.addClasspathResource("second_approve.bpmn20.xml");
        Deployment deployment = deploymentBuilder.deploy();
        String deploymentId = deployment.getId();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deploymentId)
                .singleResult();
        LOGGER.info("流程定义文件 [{}], 流程ID [{}]", processDefinition.getName(), processDefinition.getId());
        return processDefinition;
    }

    private static ProcessEngine getProcessEngine() {
        ProcessEngineConfiguration cfg = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration();
        ProcessEngine processEngine = cfg.buildProcessEngine();
        String name = processEngine.getName();
        String version = ProcessEngine.VERSION;

        LOGGER.info("流程引擎名称{},版本{}", name, version);
        return processEngine;
    }

/*
    启动我们的程序
    流程引擎名称default,版本6.0.0.4
    流程定义文件 [二级审批流程], 流程ID [second_approve:1:4]
    启动流程 [second_approve]
    待处理任务数量 [1]
    待处理任务 [填写审批信息]
    请输入 审批信息 ?
    加薪
    您输入的内容是 [加薪]
    请输入 申请人姓名 ?
    eddie
    您输入的内容是 [eddie]
    请输入 提交时间 ? 格式 (yyyy-MM-dd)
    2018-12-01
    您输入的内容是 [2018-12-01]
    请输入 确认申请 ?
    y
    您输入的内容是 [y]
    待处理任务数量 [1]
    待处理任务 [主管审批]
    请输入 主管审批结果 ?
    y
    您输入的内容是 [y]
    请输入 主管审批备注 ?
    无
    您输入的内容是 [无]
    待处理任务数量 [1]
    待处理任务 [人事审批]
    请输入 人事审批结果 ?
    y
    您输入的内容是 [y]
    请输入 人事审批备注 ?
    批准
    您输入的内容是 [批准]
    结束我们的程序
*/

}
