package com.example.mapper;


import com.example.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作日志Mapper接口
 */
@Mapper
public interface OperateLogMapper {

    /*
     * 添加操作日志
     */
    @Insert("insert into operate_log(operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time) values(#{operatorEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime})")
    void insert(OperateLog log);
}
