package com.example.service.impl;

import com.example.mapper.EmpExprMapper;
import com.example.mapper.EmpMapper;
import com.example.pojo.Emp;
import com.example.pojo.EmpExpr;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.PageResult;
import com.example.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工管理业务实现类
 */
@Service
public class EmpServiceImpl implements EmpService {
    // 注入员工持久层
    @Autowired
    private EmpMapper empMapper;
    //注入员工工作经历持久层
    @Autowired
    private EmpExprMapper empExprMapper;

    /*
     * 分页查询员工列表
     * 原始分页查询
     */
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        //1.调用mapper接口，查询总记录数
//        Long total = empMapper.count();
//        //2.调用mapper接口，查询结果列表
//        Integer start = (page - 1) * pageSize;
//        List<Emp> rows = empMapper.list(start, pageSize);
//        //3.封装到PageResult对象中，并返回
//        return new PageResult<>(total, rows);
//    }
    /*
     * 分页查询员工列表：基于pageHelper实现
     */
    /*@Override
     public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
        //1.设置分页参数
         PageHelper.startPage(page, pageSize);
        //2.执行查询
        List<Emp> empList = empMapper.list( name, gender, begin, end);
        //3.封装结果
        Page<Emp> pageObj = (Page<Emp>) empList;
        return new PageResult<>(pageObj.getTotal(), pageObj.getResult());
    }*/
    //继续改造上述代码：
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        //2.执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
        //3.封装结果
        Page<Emp> pageObj = (Page<Emp>) empList;
        return new PageResult<>(pageObj.getTotal(), pageObj.getResult());
    }

    /*
     * 添加员工
     */
    @Override
    public void add(Emp emp) {
        // 保存员工的基本信息
        //为基础属性赋值
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.add(emp);
        // 保存员工的工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            // 遍历集合， 为每一个员工工作经历设置员工id
            exprList.forEach(expr -> expr.setEmpId(emp.getId()));
            empExprMapper.addBatch(exprList);
        }

    }
}
