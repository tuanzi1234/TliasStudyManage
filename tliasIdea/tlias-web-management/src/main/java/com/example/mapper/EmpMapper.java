package com.example.mapper;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 员工管理的持久层接口
 */
@Mapper
public interface EmpMapper {
    //    /*
//     * 查询总记录数
//     */
//    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
//    Long count();
//    /*
//     * 分页查询员工数据
//     */
//    @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id order by update_time desc limit #{start},#{pageSize}")
//    List<Emp> list(Integer start, Integer pageSize);
    //引入pageHelper插件，实现分页查询，改造上述代码
    //@Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id order by update_time desc")
//     List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);
    // 改造上述代码：
    List<Emp> list(EmpQueryParam empQueryParam);

    //添加员工基本信息
    @Options(useGeneratedKeys = true, keyProperty = "id")// 获取新增数据的主键id 主键返回
    @Insert("insert into emp (username, name, gender, phone, job, salary, image, entry_date, create_time, update_time, dept_id) values (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{createTime}, #{updateTime}, #{deptId})")
    void add(Emp emp);

    //删除员工
    void deleteByIds(List<Integer> ids);

    //查询员工详情
    Emp findById(Integer id);

    //根据id修改员工信息
    void updateById(Emp emp);

    //统计职位对应的员工数量
    @MapKey("pos")
    List<Map<String, Object>> countByJob();

    //统计员工性别数量
    @MapKey("gender")
    List<Map<String, Object>> getEmpGenderData();

    // 校验部门id对应的员工数量
    @Select("SELECT COUNT(*) FROM emp WHERE dept_id = #{deptId}")
    int countByDeptId(Integer deptId);

    //查询所有员工
    List<Emp> findAll();
}
