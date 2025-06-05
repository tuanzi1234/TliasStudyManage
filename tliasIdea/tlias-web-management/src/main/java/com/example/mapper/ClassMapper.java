package com.example.mapper;


import com.example.pojo.Class;
import com.example.pojo.ClassQueryParam;
import com.example.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 班级管理持久层
 */
@Mapper
public interface ClassMapper {

    /*
     * 查询所有班级信息
     */
    List<Class> findByPage(ClassQueryParam classQueryParam);

    /*
     * 添加班级信息
     */
    void add(Class clazz);

    /*
     * 删除班级信息
     */
    void deleteById(Integer id);

    /*
     * 根据id查询班级信息
     */
    Class findById(Integer id);

    /*
     * 根据id修改班级信息
     */
    void updateById(Class clazz);

    /*
     * 查询所有班级信息
     */
    List<Class> findAll();
}
