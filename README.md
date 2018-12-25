# activiti6-samples

#### 4-2 创建流程引擎配置-config_samples
- 把原来项目DemoMain和xml复制到，config下面，
- 创建 configTest 方法和 activiti.cfg.xml

#### 4-5 数据库配置-dbconfig_code 
- 添加 ConfigDBTest 测试类
- 添加 activiti_druid.cfg.xml 数据库配置文件

#### 4-7 日志记录配置-logging_mdc
- add class : MDCCommandInvoker ConfigMDCTest
- add xml : activiti_mdc.cfg.xml my-process.bpmn20.xml

#### 4-8 4-9 历史记录配置
- add xml activiti_history.cfg.xml
- add class ConfigHistoryLevelTest

#### 4-10 事件处理及监听器配置-eventlog
- add class ConfigEventLogTest
- add xml activiti_eventlog.cfg.xml

#### 4-11 事件处理及监听器配置-eventLinstener
- ConfigEventListenerTest CustomEventListener ProcessEventListener
- activiti_eventlistener.cfg.xml
  
