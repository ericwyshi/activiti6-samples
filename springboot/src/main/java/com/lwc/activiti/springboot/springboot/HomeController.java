package com.lwc.activiti.springboot.springboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author eddie.lee
 * @ProjectName activiti6-samples
 * @Package com.lwc.activiti.springboot.springboot
 * @ClassName HomeController
 * @description
 * @date created in 2019-02-18 23:39
 * @modified by
 */
@RestController
public class HomeController {

    @RequestMapping("/home")
    public String home(){
        return "Hello World!";
    }

}
