package com.lwc.activiti.dbentity;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author eddie.lee
 * @ProjectName activiti6-samples
 * @Package com.lwc.activiti.dbentity
 * @ClassName DbIdentityTest
 * @description
 * @date created in 2019-01-06 19:12
 * @modified by
 */
public class DbIdentityTest {

    private static final Logger logger = LoggerFactory.getLogger(DbIdentityTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti-mysql.cfg.xml");

    @Test
    public void testIdentity() {
        IdentityService identityService = activitiRule.getIdentityService();

        //创建用户 ACT_ID_USER
        User user1 = identityService.newUser("user1");
        user1.setFirstName("firstName");
        user1.setLastName("lastName");
        user1.setEmail("eddie@qq.com");
        user1.setPassword("123456");
        identityService.saveUser(user1);

        User user2 = identityService.newUser("user2");
        identityService.saveUser(user2);

        //创建用户组 ACT_ID_GROUP
        Group group1 = identityService.newGroup("group1");
        group1.setName("for test");
        identityService.saveGroup(group1);

        //用户和组的关系 ACT_ID_MEMBERSHIP
        identityService.createMembership(user1.getId(), group1.getId());
        identityService.createMembership(user2.getId(), group1.getId());

        identityService.setUserInfo(user1.getId(), "age", "18");
        identityService.setUserInfo(user1.getId(), "address", "guang zhou");

    }

}
