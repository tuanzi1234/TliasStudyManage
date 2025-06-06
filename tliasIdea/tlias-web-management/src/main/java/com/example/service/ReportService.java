package com.example.service;

import com.example.pojo.JobOption;
import com.example.pojo.StudentCountOption;

import java.util.List;
import java.util.Map;

/**
 * 统计对应部门的员工人数业务逻辑接口
 */
public interface ReportService {
    //  统计员工职位数据
    JobOption getEmpJobData();

    //  统计员工性别数据
    List<Map<String, Object>> getEmpGenderData();

    //  统计学生学历数据
    List<Map<String, Object>> getStudentDegreeData();

    //  统计学生数量
    StudentCountOption getStudentCountData();
}
