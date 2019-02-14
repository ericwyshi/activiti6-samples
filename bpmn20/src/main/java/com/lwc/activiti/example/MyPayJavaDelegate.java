package com.lwc.activiti.example;

import org.activiti.engine.delegate.BpmnError;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author eddie.lee
 * @ProjectName activiti6-samples
 * @Package com.lwc.activiti.example
 * @ClassName MyPayJavaDelegate
 * @description BPMN2.0子流程
 * @date created in 2019-02-12 15:25
 * @modified by
 */
public class MyPayJavaDelegate implements JavaDelegate, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(MyPayJavaDelegate.class);


    private Expression name;
    private Expression desc;

    @Override
    public void execute(DelegateExecution execution) {
        logger.info("variables = {}", execution.getVariables());
        logger.info("run my java delegate {}", this);
        execution.getParent().setVariableLocal("key2", "value2");
        execution.setVariable("key1", "value1");
        execution.setVariable("key3", "value3");
        Object errorflag = execution.getVariable("errorflag");
        if (Objects.equals(errorflag, true)) {
            throw new BpmnError("bpmnError");
        }
    }
}
