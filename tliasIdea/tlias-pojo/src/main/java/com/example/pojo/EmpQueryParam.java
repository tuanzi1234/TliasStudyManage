package com.example.pojo;


import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Objects;

/*
 * 使用实体类封装查询参数
 */
public class EmpQueryParam {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String name;
    private Integer gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;

    public EmpQueryParam() {
    }

    public EmpQueryParam(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
        this.page = page;
        this.pageSize = pageSize;
        this.name = name;
        this.gender = gender;
        this.begin = begin;
        this.end = end;
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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public LocalDate getBegin() {
        return begin;
    }

    public void setBegin(LocalDate begin) {
        this.begin = begin;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        EmpQueryParam that = (EmpQueryParam) object;
        return Objects.equals(page, that.page) && Objects.equals(pageSize, that.pageSize) && Objects.equals(name, that.name) && Objects.equals(gender, that.gender) && Objects.equals(begin, that.begin) && Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, pageSize, name, gender, begin, end);
    }

    @Override
    public String toString() {
        return "EmpQueryParam{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", begin=" + begin +
                ", end=" + end +
                '}';
    }
}
