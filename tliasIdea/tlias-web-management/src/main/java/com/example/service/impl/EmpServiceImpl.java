package com.example.service.impl;

import com.example.mapper.EmpExprMapper;
import com.example.mapper.EmpMapper;
import com.example.pojo.*;
import com.example.service.EmpLogService;
import com.example.service.EmpService;
import com.example.utils.JwtUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
    //  注入员工操作日志业务逻辑
    @Autowired
    private EmpLogService empLogService;

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
     * 添加员工时，同时添加员工工作经历，需要使用@Transactional开启事务，
     * 默认回滚RuntimeException的异常，其他异常不回滚，需要使用rollbackFor指定需要回滚的异常
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(Emp emp) {
        //将整个代码块放入try finally代码块中,
        try {
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
        } finally {
            //记录操作日志，新增员工时，无论成功或失败都记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "添加员工：" + emp);
            empLogService.insertLog(empLog);
        }
    }

    /*
     * 批量删除员工
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(ArrayList<Integer> ids) {
        //批量删除员工
        empMapper.deleteByIds(ids);
        //批量删除员工工作经历
        empExprMapper.deleteByEmpIds(ids);

    }

    /*
     * 根据id查询员工
     */
    @Override
    public Emp findById(Integer id) {
        //查询员工基本信息和工作经历
        return empMapper.findById(id);
    }

    /*
     * 修改员工
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateById(Emp emp) {
        //根据id修改用户基本信息
        //更新时间
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //根据id删除或增加员工工作经历
        empExprMapper.deleteByEmpIds(Collections.singletonList(emp.getId()));
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            // 遍历集合， 为每一个员工工作经历设置员工id
            exprList.forEach(expr -> expr.setEmpId(emp.getId()));
            empExprMapper.addBatch(exprList);
        }
    }

    /*
     * 查询所有员工
     */
    @Override
    public List<Emp> findAll() {
        return empMapper.findAll();
    }

    /*
     * 员工登录
     */
    @Override
    public LoginInfo login(Emp emp) {
        //1.调用Mapper，查询用户信息
        Emp empDB = empMapper.findByUsernameAndPassword(emp.getUsername(), emp.getPassword());
        //2.判断用户是否存在，存在则组装登录信息，不存在则返回null
        if (empDB != null){
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setId(empDB.getId());
            loginInfo.setUsername(empDB.getUsername());
            loginInfo.setName(empDB.getName());
            //生成token
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", empDB.getId());
            claims.put("username", empDB.getUsername());
            loginInfo.setToken(JwtUtils.generateJwt(claims));
            return loginInfo;
        }
        return null;
    }
}
