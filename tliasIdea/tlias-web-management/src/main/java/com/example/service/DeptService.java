package com.example.service;

import com.example.pojo.Dept;

import java.util.List;

/**
 * 部门管理业务逻辑层接口
 */
public interface DeptService {

    // 查询所有部门
    List<Dept> findAll();

    // 删除部门
    void delete(Integer id);

    // 添加部门
    void add(Dept dept);

    // 根据id查询部门
    Dept findById(Integer id);

    // 修改部门
    void update(Dept dept);
}
