package com.example.controller;


import com.example.pojo.Dept;
import com.example.pojo.Result;
import com.example.service.DeptService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;//  注入DeptService

 /*
  * 查询所有部门信息
  */
    @GetMapping("/depts")
    public Result list() {
        List<Dept> depts = deptService.findAll();
        return Result.success(depts);
    }

    /*
     * 根据id删除部门信息
     */
    @DeleteMapping("/depts")
    public Result delete(@RequestParam Integer id) {
        deptService.delete(id);
        return Result.success();
    }
}
