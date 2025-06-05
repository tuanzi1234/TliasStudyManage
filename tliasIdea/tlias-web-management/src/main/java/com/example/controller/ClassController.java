package com.example.controller;

import com.example.pojo.*;
import com.example.pojo.Class;
import com.example.service.ClassService;
import com.github.pagehelper.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 班级管理控制器
 */
@RestController
@RequestMapping("/clazzs")
public class ClassController {
    //定义Logger对象
    private static final Logger logger = LoggerFactory.getLogger(ClassController.class);
    //注入业务逻辑层接口
    @Autowired
    private ClassService classService;

    /*
     * 分页查询班级信息
     */
    @GetMapping
    public Result list(ClassQueryParam classQueryParam) {
        logger.info("分页查询班级信息：{}" , classQueryParam);
        PageResult<Class> classList = classService.findByPage(classQueryParam);
        return Result.success(classList);
    }
    /*
     * 添加班级信息
     */
    @PostMapping
    public Result add(@RequestBody Class clazz) {
        logger.info("添加班级信息：{}" , clazz);
        classService.add(clazz);
        return Result.success();
    }
    /*
     * 删除班级信息
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        logger.info("删除班级信息：{}" , id);
        classService.deleteById(id);
        return Result.success();
    }
    /*
     * 根据id查询班级信息
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        logger.info("根据id查询班级信息{}", id);
        Class clazz = classService.findById(id);
        return Result.success(clazz);
    }
    /*
     * 根据id修改班级信息
     */
    @PutMapping
    public Result update(@RequestBody Class clazz) {
        logger.info("根据id修改班级信息{}", clazz);
        classService.updateById(clazz);
        return Result.success();
    }
    /*
     * 查询所有班级信息
     */
    @GetMapping("/list")
    public Result findAll() {
        logger.info("查询所有班级信息");
        List<Class> clazzList = classService.findAll();
        return Result.success(clazzList);
    }
}
