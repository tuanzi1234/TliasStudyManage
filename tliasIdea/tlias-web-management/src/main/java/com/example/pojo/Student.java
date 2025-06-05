package com.example.pojo;

import java.time.LocalDate;
import java.util.Objects;

/**
 * 学生信息实体类
 */
public class Student {
    private Integer id;
    private String name;
    private String no;
    private Integer gender;
    private String phone;
    private String idCard;
    private Integer isCollege;
    private String address;
    private Integer degree;
    private LocalDate gradeDate;
    private Integer clazzId;
    private Integer violationCount;
    private Integer violationScore;
    private LocalDate createTime;
    private LocalDate updateTime;
    //  关联课程名称
    private String clazzName;

    public Student() {
    }

    public Student(Integer id, String name, String no, Integer gender, String phone, String idCard, Integer isCollege, String address, Integer degree, LocalDate gradeDate, Integer clazzId, Integer violationCount, Integer violationScore, LocalDate createTime, LocalDate updateTime, String clazzName) {
        this.id = id;
        this.name = name;
        this.no = no;
        this.gender = gender;
        this.phone = phone;
        this.idCard = idCard;
        this.isCollege = isCollege;
        this.address = address;
        this.degree = degree;
        this.gradeDate = gradeDate;
        this.clazzId = clazzId;
        this.violationCount = violationCount;
        this.violationScore = violationScore;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.clazzName = clazzName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getIsCollege() {
        return isCollege;
    }

    public void setIsCollege(Integer isCollege) {
        this.isCollege = isCollege;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public LocalDate getGradeDate() {
        return gradeDate;
    }

    public void setGradeDate(LocalDate gradeDate) {
        this.gradeDate = gradeDate;
    }

    public Integer getClazzId() {
        return clazzId;
    }

    public void setClazzId(Integer clazzId) {
        this.clazzId = clazzId;
    }

    public Integer getViolationCount() {
        return violationCount;
    }

    public void setViolationCount(Integer violationCount) {
        this.violationCount = violationCount;
    }

    public Integer getViolationScore() {
        return violationScore;
    }

    public void setViolationScore(Integer violationScore) {
        this.violationScore = violationScore;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public LocalDate getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Student student = (Student) object;
        return Objects.equals(id, student.id) && Objects.equals(name, student.name) && Objects.equals(no, student.no) && Objects.equals(gender, student.gender) && Objects.equals(phone, student.phone) && Objects.equals(idCard, student.idCard) && Objects.equals(isCollege, student.isCollege) && Objects.equals(address, student.address) && Objects.equals(degree, student.degree) && Objects.equals(gradeDate, student.gradeDate) && Objects.equals(clazzId, student.clazzId) && Objects.equals(violationCount, student.violationCount) && Objects.equals(violationScore, student.violationScore) && Objects.equals(createTime, student.createTime) && Objects.equals(updateTime, student.updateTime) && Objects.equals(clazzName, student.clazzName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, no, gender, phone, idCard, isCollege, address, degree, gradeDate, clazzId, violationCount, violationScore, createTime, updateTime, clazzName);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", no=" + no +
                ", gender=" + gender +
                ", phone=" + phone +
                ", idCard=" + idCard +
                ", isCollege=" + isCollege +
                ", address='" + address + '\'' +
                ", degree=" + degree +
                ", gradeDate=" + gradeDate +
                ", clazzId=" + clazzId +
                ", violationCount=" + violationCount +
                ", violationScore=" + violationScore +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", clazzName='" + clazzName + '\'' +
                '}';
    }
}
