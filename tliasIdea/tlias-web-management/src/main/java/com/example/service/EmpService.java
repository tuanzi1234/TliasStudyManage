package com.example.service;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.LoginInfo;
import com.example.pojo.PageResult;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    /*
     * 删除员工
     */
    void delete(ArrayList<Integer> ids);

    /*
     * 根据id查询员工
     */
    Emp findById(Integer id);

    /*
     * 修改员工
     */
    void updateById(Emp emp);

    /*
     * 查询所有员工
     */
    List<Emp> findAll();

    /*
     * 员工登录
     */
    LoginInfo login(Emp emp);
}
