package com.lwc.activiti.example;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.impl.delegate.ActivityBehavior;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author eddie.lee
 * @ProjectName activiti6-samples
 * @Package com.lwc.activiti.example
 * @ClassName MyActivityBehavior
 * @description
 * @date created in 2019-02-12 15:23
 * @modified by
 */
public class MyActivityBehavior implements ActivityBehavior {

    private static final Logger logger = LoggerFactory.getLogger(MyActivityBehavior.class);

    @Override
    public void execute(DelegateExecution execution) {
        logger.info("run my activity behavior");
    }

}
