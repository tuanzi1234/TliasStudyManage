package com.example.mapper;

import com.example.pojo.EmpLog;
import com.example.pojo.EmpLogQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpLogMapper {

    //  添加员工日志
    @Insert("insert into emp_log(operate_time, info) values(#{operateTime}, #{info})")
    void insert(EmpLog empLog);

    //  分页查询员工日志
    List<EmpLogQueryParam> findByPage(EmpLogQueryParam empLogQueryParam);


    
}
