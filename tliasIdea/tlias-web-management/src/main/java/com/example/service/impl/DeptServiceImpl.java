package com.example.service.impl;

import com.example.mapper.DeptMapper;
import com.example.pojo.Dept;
import com.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
