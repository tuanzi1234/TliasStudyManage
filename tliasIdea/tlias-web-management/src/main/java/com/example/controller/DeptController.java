package com.example.controller;


import com.example.anno.Log;
import com.example.pojo.Dept;
import com.example.pojo.Result;
import com.example.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理控制器
 */
@RequestMapping("/depts")
@RestController
public class DeptController {
    //记录日志
    private static final Logger logger = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;//  注入DeptService

    /*
     * 查询所有部门信息
     */

    @GetMapping
    public Result list() {
        logger.info("查询所有部门信息");
        List<Dept> depts = deptService.findAll();
        return Result.success(depts);
    }

    /*
     * 根据id删除部门信息
     */
    @Log
    @DeleteMapping
    public Result delete(@RequestParam Integer id) {
        logger.info("根据id删除部门信息{}", id);
        deptService.delete(id);
        return Result.success();
    }

    /*
     * 添加部门信息
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        logger.info("添加部门信息{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    /*
     * 根据id查询部门信息
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        logger.info("根据id查询部门信息{}", id);
        Dept dept = deptService.findById(id);
        return Result.success(dept);
    }

    /*
     * 修改部门信息
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        logger.info("修改部门信息{}", dept);
        deptService.update(dept);
        return Result.success();
    }
}
