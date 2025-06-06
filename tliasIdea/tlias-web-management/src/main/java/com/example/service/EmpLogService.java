package com.example.service;

import com.example.pojo.EmpLog;
import com.example.pojo.EmpLogQueryParam;
import com.example.pojo.PageResult;

/*
 * 记录员工日志的功能逻辑
 */
public interface EmpLogService {

    /*
     * 添加员工日志
     */
    void insertLog(EmpLog empLog);

    /*
     * 分页查询员工日志
     */
    PageResult<EmpLogQueryParam> findByPage(EmpLogQueryParam empLogQueryParam);
}
