package com.example.service;

import com.example.pojo.Class;
import com.example.pojo.ClassQueryParam;
import com.example.pojo.PageResult;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * 班级管理业务逻辑接口
 */
public interface ClassService {
    //分页查询所有班级
    PageResult<Class> findByPage(ClassQueryParam classQueryParam);

    //添加班级
    void add(Class clazz);

    //删除班级
    void deleteById(Integer id);

    //根据id查询班级
    Class findById(Integer id);

    //根据id修改班级
    void updateById(Class clazz);

    //查询所有班级
    List<Class> findAll();
}
