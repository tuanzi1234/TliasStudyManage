package com.example.controller;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * 员工管理控制器
 */
@RequestMapping("/emps")
@RestController
public class EmpController {
    //定义日志常量
    private static final Logger log = LoggerFactory.getLogger(EmpController.class);
    //注入员工业务逻辑层
    @Autowired
    private EmpService empService;

    /*
     * 分页查询员工列表+条件分页查询
     */
    /*@GetMapping
    public Result page(@RequestParam( defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Integer gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {//可以使用@RequestParam注解来给参数添加默认值
        //记录所有的参数日志
        log.info("分页查询员工列表，参数：page={},pageSize={},name={},gender={},begin={},end={}", page, pageSize, name, gender, begin, end);
        PageResult<Emp> pageResult = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageResult);
    }*/
    //使用对象封装了查询参数后，上述代码可改为以下代码
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {//可以使用@RequestParam注解来给参数添加默认值
        //记录所有的参数日志
        log.info("分页查询员工列表，参数：{}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /*
     * 新增员工
     */
    @PostMapping
    public Result add(@RequestBody Emp emp) {//使用@RequestBody注解来接收JSON格式的请求体参数
        //记录所有的参数日志
        log.info("新增员工，参数：{}", emp);
        empService.add(emp);
        return Result.success();
    }

    /*
     * 删除员工: 使用数组封装id
     */
    /*@DeleteMapping
    public Result delete( Integer[] ids) {
        log.info("删除员工，参数：{}", Arrays.toString(ids));

        return Result.success();
    }*/
    /*
     * 删除员工: 使用集合封装id
     */
    @DeleteMapping
    public Result delete(@RequestParam ArrayList<Integer> ids) {
        log.info("删除员工，参数：{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /*
     * 根据id查询员工基本信息以及工作经历
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        log.info("根据id查询员工基本信息以及工作经历，参数：{}", id);
        Emp emp = empService.findById(id);
        return Result.success(emp);
    }

    /*
     * 修改员工信息
     */
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工信息，参数：{}", emp);
        empService.updateById(emp);
        return Result.success();
    }
}
