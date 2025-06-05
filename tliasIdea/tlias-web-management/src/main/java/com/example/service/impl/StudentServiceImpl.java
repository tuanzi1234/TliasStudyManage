package com.example.service.impl;

import com.example.mapper.StudentMapper;
import com.example.pojo.PageResult;
import com.example.pojo.Student;
import com.example.pojo.StudentQueryParam;
import com.example.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学生信息业务逻辑接口实现类
 */
@Service
public class StudentServiceImpl implements StudentService {
    //  注入学生信息Mapper接口
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public PageResult<Student> findByPage(StudentQueryParam studentQueryParam) {
        //设置分页参数
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        //执行查询
        List<Student> studentList =  studentMapper.findByPage(studentQueryParam);
        //封装分页结果
        Page<Student> pageObj = (Page<Student>) studentList;
        return new PageResult<>(pageObj.getTotal(), pageObj.getResult());

    }
}
