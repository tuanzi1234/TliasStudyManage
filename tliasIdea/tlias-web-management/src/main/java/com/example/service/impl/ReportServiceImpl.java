package com.example.service.impl;

import com.example.mapper.EmpMapper;
import com.example.pojo.JobOption;
import com.example.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 统计对应部门的员工人数业务逻辑接口实现类
 */
@Service
public class ReportServiceImpl implements ReportService {
    //注入统计员工人数的mapper接口
    @Autowired
    private EmpMapper empMapper;

    //统计对应部门的员工人数
    @Override
    public JobOption getEmpJobData() {
        //1.获取统计数据
        List<Map<String, Object>> list = empMapper.countByJob();
        //2.组装结果
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();
        return new JobOption(jobList, dataList);
    }

    //统计员工性别人数
    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.getEmpGenderData();
    }
}
