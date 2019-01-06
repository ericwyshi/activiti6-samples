package com.lwc.activiti.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author eddie.lee
 * @ProjectName activiti6-samples
 * @Package com.lwc.activiti.mapper
 * @ClassName MyCustomMapper
 * @description
 * @date created in 2019-01-06 15:49
 * @modified by
 */
public interface MyCustomMapper {

    @Select("select * from act_ru_task")
    List<Map<String, Object>> findAll();
}
