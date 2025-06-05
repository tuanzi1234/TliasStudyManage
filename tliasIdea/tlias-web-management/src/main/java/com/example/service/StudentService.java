package com.example.service;

import com.example.pojo.PageResult;
import com.example.pojo.Student;
import com.example.pojo.StudentQueryParam;

/**
 * 学生信息业务逻辑接口
 */
public interface StudentService {

    /*
    * 分页查询学生信息
     */
    PageResult<Student> findByPage(StudentQueryParam studentQueryParam);
}
