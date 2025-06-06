package com.example.mapper;

import com.example.pojo.Student;
import com.example.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 学生信息持久层接口
 */
@Mapper
public interface StudentMapper {
    /*
     * 分页查询所有学生信息
     */
    List<Student> findByPage(StudentQueryParam studentQueryParam);

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
     * 获取学生信息统计数据
     */
    @MapKey("name")
    List<Map<String, Object>> getStudentDegreeData();

    /*
     * 获取学生数量统计数据
     */
    @MapKey("clazzList")
    List<Map<String, Object>> getStudentCountData();

    // 校验学生数量
    @Select("SELECT COUNT(*) FROM student WHERE clazz_id = #{classId}")
    int countByClassId(Integer classId);
}
