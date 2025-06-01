package com.example.mapper;


import com.example.pojo.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * 部门管理模块的持久层接口
 */
@Mapper
public interface DeptMapper {
    // 查询所有部门数据
    @Select("select id, name, create_time, update_time from dept order by update_time desc ")
    List<Dept> findAll();

    // 根据id删除部门
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    // 添加部门
    @Select("insert into dept (name, create_time, update_time) values (#{name}, #{createTime}, #{updateTime})")
    void add(Dept dept);

    // 根据id查询部门
    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept findById(Integer id);


    // 修改部门
    @Select("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}
