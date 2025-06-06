package com.example.service.impl;

import com.example.mapper.ClassMapper;
import com.example.mapper.StudentMapper;
import com.example.pojo.Class;
import com.example.pojo.ClassQueryParam;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.PageResult;
import com.example.service.ClassService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


/**
 * 班级管理业务逻辑接口实现类
 */
@Service
public class ClassServiceImpl implements ClassService {

    /*
     * 注入班级管理Mapper接口
     */
    @Autowired
    private ClassMapper classMapper;
    /*
     * 引入学生管理Mapper接口
     */
    @Autowired
    private StudentMapper studentMapper;

    /*
     * 分页查询所有班级信息
     */
    @Override
    public PageResult<Class> findByPage(ClassQueryParam classQueryParam) {
        //根据查询到的开课时间和结课时间做出判断，若在开课时间前，则将状态设置为“未开班”，若在结课时间后，则将状态设置为“已结课”，若在开课时间和结课时间之间，则将状态设置为“已开班”
        //设置分页参数
        PageHelper.startPage(classQueryParam.getPage(), classQueryParam.getPageSize());
        //使用list集合封装查询到的班级信息
        List<Class> clazzList = classMapper.findByPage(classQueryParam);
        //遍历list集合，对班级状态进行判断
        for (Class clazz : clazzList) {
            if (clazz.getBeginDate().isAfter(LocalDate.now())) {
                clazz.setStatus("未开班");
            } else if (clazz.getEndDate().isBefore(LocalDate.now())) {
                clazz.setStatus("已结课");
            } else {
                clazz.setStatus("已开班");
            }
        }
        //封装查询结果
        Page<Class> page = (Page<Class>) clazzList;
        return new PageResult<>(page.getTotal(), page.getResult());
    }

    /*
     * 添加班级信息
     */
    @Override
    public void add(Class clazz) {
        //设置创建时间和更新时间
        clazz.setCreateTime(LocalDate.now());
        clazz.setUpdateTime(LocalDate.now());
        //调用添加方法
        classMapper.add(clazz);
    }

    /*
     * 根据id删除班级信息
     */
    @Override
    public void deleteById(Integer id) {
        // 校验班级是否有关联学生
        if (studentMapper.countByClassId(id) > 0) {
            throw new RuntimeException("该班级下存在学生，无法删除");
        }
        classMapper.deleteById(id);
    }

    /*
     * 根据id查询班级信息
     */
    @Override
    public Class findById(Integer id) {
        return classMapper.findById(id);
    }

    /*
     * 根据id修改班级信息
     */
    @Override
    public void updateById(Class clazz) {
        //设置更新时间
        clazz.setUpdateTime(LocalDate.now());
        //调用修改方法
        classMapper.updateById(clazz);
    }

    /*
     * 查询所有班级信息
     */
    @Override
    public List<Class> findAll() {
        return classMapper.findAll();
    }
}
