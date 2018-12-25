package com.lwc.activiti.event;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author eddie.lee
 * @ProjectName activiti6-samples
 * @Package com.lwc.activiti.event
 * @ClassName CustomEventListener
 * @description
 * @date created in 2018-12-25 14:07
 * @modified by
 */
public class CustomEventListener implements ActivitiEventListener {
    private static final Logger logger = LoggerFactory.getLogger(CustomEventListener.class);

    @Override
    public void onEvent(ActivitiEvent event) {
        ActivitiEventType eventType = event.getType();

        if(ActivitiEventType.CUSTOM.equals(eventType)){
            logger.info("监听到自定义事件 {} \t {}",eventType,event.getProcessInstanceId());
        }
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}