package com.example.controller;

import com.example.pojo.EmpLogQueryParam;
import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.service.EmpLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/log")
@RestController
public class EmpLogController {
    //声明Logger对象
    private static final Logger log = LoggerFactory.getLogger(EmpLogController.class);
    //注入业务逻辑
    @Autowired
    private EmpLogService empLogService;

    /*
     * 分页查询员工日志
     */
    @GetMapping("/page")
    public Result page(EmpLogQueryParam empLogQueryParam) {
        //记录日志
        log.info("分页查询员工日志，参数：{}", empLogQueryParam);
        PageResult<EmpLogQueryParam> pageResult = empLogService.findByPage(empLogQueryParam);
        return Result.success(pageResult);
    }
}
