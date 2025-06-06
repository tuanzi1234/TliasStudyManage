package com.example.service;

import com.example.pojo.PageResult;
import com.example.pojo.Student;
import com.example.pojo.StudentQueryParam;

import java.util.ArrayList;

/**
 * 学生信息业务逻辑接口
 */
public interface StudentService {

    /*
     * 分页查询学生信息
     */
    PageResult<Student> findByPage(StudentQueryParam studentQueryParam);

    /*
     * 添加学生信息
     */
    void add(Student student);

    /*
     * 根据id删除学生信息
     */
    void deleteById(ArrayList<Integer> ids);

    /*
     * 根据id查询学生信息
     */
    Student findById(Integer id);

    /*
     * 根据id修改学生信息
     */
    void updateById(Student student);

    /*
     * 根据id修改学生违纪信息
     */
    void updateViolation(Integer id, Integer score);
}
