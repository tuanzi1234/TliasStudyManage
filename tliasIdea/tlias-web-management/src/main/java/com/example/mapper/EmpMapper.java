package com.example.mapper;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;


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
}
