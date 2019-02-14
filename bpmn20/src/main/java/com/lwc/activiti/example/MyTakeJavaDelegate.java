package com.lwc.activiti.example;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author eddie.lee
 * @ProjectName activiti6-samples
 * @Package com.lwc.activiti.example
 * @ClassName MyTakeJavaDelegate
 * @description BPMN2.0子流程
 * @date created in 2019-02-12 15:25
 * @modified by
 */
public class MyTakeJavaDelegate implements JavaDelegate, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(MyTakeJavaDelegate.class);

    private Expression name;
    private Expression desc;

    @Override
    public void execute(DelegateExecution execution) {
        if(name != null){
            Object value = name.getValue(execution);
            logger.info("name = {}",value);
        }
        if(desc != null){
            Object value = desc.getValue(execution);
            logger.info("desc = {}",value);
        }
        logger.info("run my java delegate {}",this);
    }
}
