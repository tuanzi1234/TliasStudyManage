package com.example.pojo;

import java.util.List;
import java.util.Objects;

public class StudentCountOption {
    private List clazzList;
    private List dataList;

    public StudentCountOption() {
    }

    public StudentCountOption(List clazzList, List dataList) {
        this.clazzList = clazzList;
        this.dataList = dataList;
    }

    public List getClazzList() {
        return clazzList;
    }

    public void setClazzList(List clazzList) {
        this.clazzList = clazzList;
    }

    public List getDataList() {
        return dataList;
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        StudentCountOption that = (StudentCountOption) object;
        return Objects.equals(clazzList, that.clazzList) && Objects.equals(dataList, that.dataList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clazzList, dataList);
    }

    @Override
    public String toString() {
        return "StudentCountOption{" +
                "clazzList='" + clazzList + '\'' +
                ", dataList=" + dataList +
                '}';
    }
}
