package com.example.service;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.PageResult;

import java.time.LocalDate;

/**
 * 员工管理业务逻辑层接口
 */
public interface EmpService {
    /*
     * 分页查询
     */
//    PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);
    //使用对象封装查询参数后，上述代码可改为以下代码
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /*
     * 新增员工
     */
    void add(Emp emp);
}
