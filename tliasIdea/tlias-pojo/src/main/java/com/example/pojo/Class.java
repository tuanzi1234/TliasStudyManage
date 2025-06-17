package com.example.pojo;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 班级信息的实体类
 */
public class Class {
    private Integer id;
    private String name;
    private Integer room;
    private LocalDate beginDate;
    private LocalDate endDate;
    private Integer masterId;
    private Integer subject;
    private LocalDateTime createTime;
    private LocalDateTime  updateTime;
    //  班级的班主任
    private String masterName;
    //班级状态
    private String status;

    public Class() {
    }

    public Class(Integer id, String name, Integer room, LocalDate beginDate, LocalDate endDate, Integer masterId, Integer subject, LocalDateTime createTime, LocalDateTime updateTime, String masterName, String status) {
        this.id = id;
        this.name = name;
        this.room = room;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.masterId = masterId;
        this.subject = subject;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.masterName = masterName;
        this.status = status;
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

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public Integer getSubject() {
        return subject;
    }

    public void setSubject(Integer subject) {
        this.subject = subject;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Class aClass = (Class) object;
        return Objects.equals(id, aClass.id) && Objects.equals(name, aClass.name) && Objects.equals(room, aClass.room) && Objects.equals(beginDate, aClass.beginDate) && Objects.equals(endDate, aClass.endDate) && Objects.equals(masterId, aClass.masterId) && Objects.equals(subject, aClass.subject) && Objects.equals(createTime, aClass.createTime) && Objects.equals(updateTime, aClass.updateTime) && Objects.equals(masterName, aClass.masterName) && Objects.equals(status, aClass.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, room, beginDate, endDate, masterId, subject, createTime, updateTime, masterName, status);
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", room=" + room +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", masterId=" + masterId +
                ", subject=" + subject +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", masterName='" + masterName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
