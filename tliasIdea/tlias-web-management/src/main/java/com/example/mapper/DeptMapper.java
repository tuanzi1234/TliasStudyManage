package com.example.mapper;


import com.example.pojo.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {
    // 查询所有部门数据
    @Select("select id, name, create_time, update_time from dept order by update_time desc ")
    List<Dept> findAll();

    // 根据id删除部门
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);
}
