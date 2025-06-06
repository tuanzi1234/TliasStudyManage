package com.example.controller;

import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.pojo.Student;
import com.example.pojo.StudentQueryParam;
import com.example.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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

    /*
     * 根据id添加学生信息
     */
    @PostMapping
    public Result add(@RequestBody Student student) {
        //打印日志
        logger.info("StudentController.add()");
        //调用StudentService的add()方法添加学生信息
        studentService.add(student);
        return Result.success();
    }
    /*
     * 根据id批量删除学生信息
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable ArrayList<Integer> ids) {
        //打印日志
        logger.info("StudentController.delete()");
        //调用StudentService的delete()方法删除学生信息
        studentService.deleteById(ids);
        return Result.success();
    }

    /*
     * 根据id查询学生信息
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        //打印日志
        logger.info("StudentController.findById()");
        //调用StudentService的findById()方法查询学生信息
        Student student = studentService.findById(id);
        return Result.success(student);
    }

    /*
     * 根据id修改学生信息
     */
    @PutMapping
    public Result update(@RequestBody Student student) {
        //打印日志
        logger.info("StudentController.update()");
        //调用StudentService的update()方法修改学生信息
        studentService.updateById(student);
        return Result.success();
    }
    /*
     * 根据id修改学生违纪：传递学生id和分数
     */
    @PutMapping("/violation/{id}/{score}")
    public Result updateViolation(@PathVariable Integer id, @PathVariable Integer score) {
        //打印日志
        logger.info("StudentController.updateViolation()");
        //调用StudentService的updateViolation()方法修改学生违纪
        studentService.updateViolation(id, score);
        return Result.success();
    }

}
