package com.example.pojo;

import java.util.Objects;

/**
 * 学生查询参数
 */
public class StudentQueryParam {
    private Integer page;
    private Integer pageSize;
    private String name;
    private Integer degree;
    private Integer clazzId;

    public StudentQueryParam() {
    }

    public StudentQueryParam(Integer page, Integer pageSize, String name, Integer degree, Integer clazzId) {
        this.page = page;
        this.pageSize = pageSize;
        this.name = name;
        this.degree = degree;
        this.clazzId = clazzId;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public Integer getClazzId() {
        return clazzId;
    }

    public void setClazzId(Integer clazzId) {
        this.clazzId = clazzId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        StudentQueryParam that = (StudentQueryParam) object;
        return Objects.equals(page, that.page) && Objects.equals(pageSize, that.pageSize) && Objects.equals(name, that.name) && Objects.equals(degree, that.degree) && Objects.equals(clazzId, that.clazzId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, pageSize, name, degree, clazzId);
    }

    @Override
    public String toString() {
        return "StudentQueryParam{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", name='" + name + '\'' +
                ", degree=" + degree +
                ", clazzId=" + clazzId +
                '}';
    }
}
