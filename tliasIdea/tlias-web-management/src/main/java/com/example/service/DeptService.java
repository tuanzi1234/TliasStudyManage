package com.example.service;

import com.example.pojo.Dept;

import java.util.List;

public interface DeptService {

    List<Dept> findAll();

    void delete(Integer id);
}
