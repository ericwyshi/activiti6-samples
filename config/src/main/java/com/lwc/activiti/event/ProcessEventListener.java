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
 * @ClassName ProcessEventListener
 * @description
 * @date created in 2018-12-25 14:08
 * @modified by
 */
public class ProcessEventListener implements ActivitiEventListener {

    private static final Logger logger = LoggerFactory.getLogger(ProcessEventListener.class);

    @Override
    public void onEvent(ActivitiEvent event) {
        ActivitiEventType type = event.getType();
        if (ActivitiEventType.PROCESS_STARTED.equals(type)) {
            logger.info("流程启动 {} \t {}", type, event.getProcessInstanceId());
        } else if (ActivitiEventType.PROCESS_COMPLETED.equals(type)) {
            logger.info("流程结束 {} \t {}", type, event.getProcessInstanceId());
        }
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }

}
