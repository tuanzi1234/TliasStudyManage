package com.example.pojo;


import java.time.LocalDate;
import java.util.Objects;

/**
 * 分页查询班级信息的参数
 */
public class ClassQueryParam {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String name;
    private LocalDate  begin;
    private LocalDate end;

    public ClassQueryParam() {
    }

    public ClassQueryParam(Integer page, Integer pageSize, String name, LocalDate begin, LocalDate end) {
        this.page = page;
        this.pageSize = pageSize;
        this.name = name;
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
        ClassQueryParam that = (ClassQueryParam) object;
        return Objects.equals(page, that.page) && Objects.equals(pageSize, that.pageSize) && Objects.equals(name, that.name) && Objects.equals(begin, that.begin) && Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, pageSize, name, begin, end);
    }

    @Override
    public String toString() {
        return "ClassQueryParam{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", name='" + name + '\'' +
                ", begin=" + begin +
                ", end=" + end +
                '}';
    }
}
