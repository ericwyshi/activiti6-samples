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

#### 4-14 命令拦截器配置-command-2 
- activiti_interceptor.cfg.xml
- ConfigInterceptorTest DurationCommandInterceptor
  
#### 4-16 作业执行器配置-job-2
- ConfigJobTest JobEventListener
- activiti_job.cfg.xml my-process_job.bpmn20.xml

#### 4-19 Activiti与spring集成-3
- ConfigSpringTest HelloBean
- activiti-context.xml my-process_spring.bpmn20.xml

#### 5-3 流程存储服务-RepositoryService
- 新建coreapi子项目 复制config的 activiti.cfg.xml logback.xml second_approve.bpmn20.xml my-process.bpmn20.xml（test/resources）
- RepositoryServiceTest

#### 5-4、5-5、5-6 流程运行 控制服务-RuntimeService

#### 5-9 任务管理服务-TaskService
- TaskServiceTest
- my-process-task.bpmn20.xml

#### 5-10 用户和用户组管理
- IdentityServiceTest

#### 5-11 表单管理
- FormServiceTest
- my-process-form.bpmn20.xml

#### 5-13 历史数据管理服务-HistoryService
- HistoryServiceTest
- activiti_history.cfg.xml

#### 5-14 其它管理服务-OtherService 
- MyCustomMapper
- activiti_job.cfg.xml
- test.ManagementServiceTest
- test.my-process-job.bpmn20.xml

#### 6-1 通用数据表设计
- 新建dbentity
- DbConfigTest DbGeTest
- activiti-mysql.cfg.xml
- my-process.bpmn20.xml

#### 6-2 流程定义存储表设计
- DbRepositoryTest
- com/lwc/activiti/my-process.bpmn20.xml
- MyUnitTest
- activiti.cfg.xml
- second_approve.bpmn20.xml
- logback.xml

#### 6-3 身份数据表设计
- DbIdentityTest

#### 6-5 运行时流程数据表设计-2
- DbRuntimeTest
- my-process-job.bpmn20.xml
- my-process-message.bpmn20.xml
- my-process-message-received.bpmn20.xml