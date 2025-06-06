package com.example.service.impl;

import com.example.mapper.EmpLogMapper;
import com.example.pojo.EmpLog;
import com.example.pojo.EmpLogQueryParam;
import com.example.pojo.PageResult;
import com.example.service.EmpLogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 员工日志业务层实现类
 */
@Service
public class EmpLogServiceImpl implements EmpLogService {
    @Autowired
    private EmpLogMapper empLogMapper;

    //添加员工日志
    @Transactional(propagation = Propagation.REQUIRES_NEW) // 开启新事务,以下方法在新事务中运行
    @Override
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }

    //分页查询员工日志
    @Override
    public PageResult<EmpLogQueryParam> findByPage(EmpLogQueryParam empLogQueryParam) {
        PageHelper.startPage(empLogQueryParam.getPage(), empLogQueryParam.getPageSize());
        List<EmpLogQueryParam> empLogList = empLogMapper.findByPage(empLogQueryParam);
        Page<EmpLogQueryParam> pageObj = (Page<EmpLogQueryParam>) empLogList;
        return new PageResult<>(pageObj.getTotal(), pageObj.getResult());
    }


}
