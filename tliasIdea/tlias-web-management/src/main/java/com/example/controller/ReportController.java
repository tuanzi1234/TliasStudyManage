package com.example.controller;

import com.example.pojo.JobOption;
import com.example.pojo.Result;
import com.example.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/report")
public class ReportController {
    //定义日志对象log
    private static final Logger log = LoggerFactory.getLogger(ReportController.class);

    //注入ReportService
    @Autowired
    private ReportService reportService;

    /*
     * 统计员工对应职位的人数
     */
    @GetMapping("/empJobData")
    public Result empJobData() {
        log.info("统计员工对应职位的人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }
    /*
     * 统计员工性别人数
     */
    @GetMapping("/empGenderData")
    public Result empGenderData() {
        log.info("统计员工性别人数");
        List<Map<String, Object>> genderData = reportService.getEmpGenderData();
        return Result.success(genderData);
    }

}
