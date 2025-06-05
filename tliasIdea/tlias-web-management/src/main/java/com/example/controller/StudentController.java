package com.example.controller;

import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.pojo.Student;
import com.example.pojo.StudentQueryParam;
import com.example.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学生信息控制器
 */

@RequestMapping("/students")
@RestController
public class StudentController {
    //声明Logger对象
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    //注入StudentService对象
    @Autowired
    private StudentService studentService;
    /*
     * 分页查询所有学生信息
     */
    @GetMapping
    public Result list(StudentQueryParam studentQueryParam) {
        //打印日志
        logger.info("StudentController.list()");
        //使用PageHelper分页查询所有学生信息
        PageResult<Student> pageResult = studentService.findByPage(studentQueryParam);
        return Result.success(pageResult);
    }

}
