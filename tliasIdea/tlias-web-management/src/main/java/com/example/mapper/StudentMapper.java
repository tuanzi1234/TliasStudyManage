package com.example.mapper;

import com.example.pojo.Student;
import com.example.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 学生信息持久层接口
 */
@Mapper
public interface StudentMapper {
    /*
     * 分页查询所有学生信息
     */
    List<Student> findByPage(StudentQueryParam studentQueryParam);
}
