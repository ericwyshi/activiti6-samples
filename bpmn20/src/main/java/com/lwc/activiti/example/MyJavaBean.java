package com.lwc.activiti.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author eddie.lee
 * @ProjectName activiti6-samples
 * @Package com.lwc.activiti.example
 * @ClassName MyJavaBean
 * @description
 * @date created in 2019-02-12 15:24
 * @modified by
 */
public class MyJavaBean implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(MyActivityBehavior.class);


    private String name;

    public String getName() {
        logger.info("run getName name:{}", name);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyJavaBean() {
    }

    public MyJavaBean(String name) {
        this.name = name;
    }

    public void sayHello() {
        logger.info("run sayHello");

    }
}
