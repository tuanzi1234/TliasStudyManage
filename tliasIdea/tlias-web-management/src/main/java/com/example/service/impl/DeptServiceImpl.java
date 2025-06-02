package com.example.service.impl;

import com.example.mapper.DeptMapper;
import com.example.pojo.Dept;
import com.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 部门管理业务层实现类
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    /*
     * 查询所有部门信息
     */
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    /*
     * 根据id删除部门信息
     */
    @Override
    public void delete(Integer id) {
        deptMapper.deleteById(id);
    }

    /*
     * 添加部门信息
     */
    @Override
    public void add(Dept dept) {
        // 设置创建时间与修改时间为现在，补全字段
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //再调用添加方法
        deptMapper.add(dept);
    }

    /*
     * 根据id查询部门信息
     */
    @Override
    public Dept findById(Integer id) {
        return deptMapper.findById(id);
    }

    /*
     * 修改部门信息
     */
    @Override
    public void update(Dept dept) {
        //  设置修改时间为现在
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
