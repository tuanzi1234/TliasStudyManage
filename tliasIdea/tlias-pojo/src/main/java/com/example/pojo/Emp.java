package com.example.pojo;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 员工实体类
 */
public class Emp {
    private Integer id; //ID,主键
    private String username; //用户名
    private String password; //密码
    private String name; //姓名
    private Integer gender; //性别, 1:男, 2:女
    private String phone; //手机号
    private Integer job; //职位, 1:班主任,2:讲师,3:学工主管,4:教研主管,5:咨询师
    private Integer salary; //薪资
    private String image; //头像
    private LocalDate entryDate; //入职日期
    private Integer deptId; //关联的部门ID
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间
    //添加一个部门名称属性，封装员工所属的部门名称
    private String deptName;
    //添加一个集合，封装工作经历列表
    private List<EmpExpr> exprList;

    public Emp() {
    }

    public Emp(Integer id, String username, String password, String name, Integer gender, String phone, Integer job, Integer salary, String image, LocalDate entryDate, Integer deptId, LocalDateTime createTime, LocalDateTime updateTime, String deptName, List<EmpExpr> exprList) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.job = job;
        this.salary = salary;
        this.image = image;
        this.entryDate = entryDate;
        this.deptId = deptId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deptName = deptName;
        this.exprList = exprList;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getJob() {
        return job;
    }

    public void setJob(Integer job) {
        this.job = job;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<EmpExpr> getExprList() {
        return exprList;
    }

    public void setExprList(List<EmpExpr> exprList) {
        this.exprList = exprList;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Emp emp = (Emp) object;
        return Objects.equals(id, emp.id) && Objects.equals(username, emp.username) && Objects.equals(password, emp.password) && Objects.equals(name, emp.name) && Objects.equals(gender, emp.gender) && Objects.equals(phone, emp.phone) && Objects.equals(job, emp.job) && Objects.equals(salary, emp.salary) && Objects.equals(image, emp.image) && Objects.equals(entryDate, emp.entryDate) && Objects.equals(deptId, emp.deptId) && Objects.equals(createTime, emp.createTime) && Objects.equals(updateTime, emp.updateTime) && Objects.equals(deptName, emp.deptName) && Objects.equals(exprList, emp.exprList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, name, gender, phone, job, salary, image, entryDate, deptId, createTime, updateTime, deptName, exprList);
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", job=" + job +
                ", salary=" + salary +
                ", image='" + image + '\'' +
                ", entryDate=" + entryDate +
                ", deptId=" + deptId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deptName='" + deptName + '\'' +
                ", empExprs=" + exprList +
                '}';
    }
}
