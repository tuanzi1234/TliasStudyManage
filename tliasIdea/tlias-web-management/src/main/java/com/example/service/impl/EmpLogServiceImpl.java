package com.example.service.impl;

import com.example.mapper.EmpLogMapper;
import com.example.pojo.EmpLog;
import com.example.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
}
