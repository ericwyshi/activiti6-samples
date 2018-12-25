package com.lwc.activiti.delegate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author eddie.lee
 * @ProjectName activiti6-samples
 * @Package com.lwc.activiti.delegate
 * @ClassName HelloBean
 * @description
 * @date created in 2018-12-25 16:00
 * @modified by
 */
public class HelloBean {

    private static final Logger logger = LoggerFactory.getLogger(HelloBean.class);

    public void sayHello(){
        logger.info(" === sayHello === ");
    }

}
