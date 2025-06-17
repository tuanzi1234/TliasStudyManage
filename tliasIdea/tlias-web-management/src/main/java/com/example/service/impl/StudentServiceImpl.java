package com.example.service.impl;

import com.example.mapper.StudentMapper;
import com.example.pojo.PageResult;
import com.example.pojo.Student;
import com.example.pojo.StudentQueryParam;
import com.example.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 学生信息业务逻辑接口实现类
 */
@Service
public class StudentServiceImpl implements StudentService {
    //  注入学生信息Mapper接口
    @Autowired
    private StudentMapper studentMapper;

    /*
     * 分页查询学生信息
     */
    @Override
    public PageResult<Student> findByPage(StudentQueryParam studentQueryParam) {
        //设置分页参数
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        //执行查询
        List<Student> studentList = studentMapper.findByPage(studentQueryParam);
        //封装分页结果
        Page<Student> pageObj = (Page<Student>) studentList;
        return new PageResult<>(pageObj.getTotal(), pageObj.getResult());
    }

    /*
     * 添加学生信息
     */
    @Override
    public void add(Student student) {
        //设置创建时间与修改时间
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.add(student);
    }

    /*
     * 根据id删除学生信息
     */
    @Override
    public void deleteById(ArrayList<Integer> ids) {
        studentMapper.deleteById(ids);
    }

    /*
     * 根据id查询学生信息
     */
    @Override
    public Student findById(Integer id) {
        return studentMapper.findById(id);
    }

    /*
     * 修改学生信息
     */
    @Override
    public void updateById(Student student) {
        //设置修改时间
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.updateById(student);
    }

    /*
     * 修改学生违纪信息
     */
    @Override
    public void updateViolation(Integer id, Integer score) {
        Student student = studentMapper.findById(id);
        if (score > 0) {
            student.setViolationScore(student.getViolationScore() + score);
            student.setViolationCount(student.getViolationCount() + 1);
            student.setUpdateTime(LocalDateTime.now());  // 已包含在选中代码
            studentMapper.updateById(student);  // 使用通用更新方法替代专用方法
        } else {
            throw new IllegalArgumentException("违纪分数不能小于或等于0");
        }

    }
}
