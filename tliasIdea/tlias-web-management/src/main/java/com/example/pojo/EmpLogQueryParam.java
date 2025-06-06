package com.example.pojo;

import java.util.Objects;

public class EmpLogQueryParam {
    private Integer page;
    private Integer pageSize;

    public EmpLogQueryParam() {
    }

    public EmpLogQueryParam(Integer page, Integer pageSize) {
        this.page = page;
        this.pageSize = pageSize;
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        EmpLogQueryParam that = (EmpLogQueryParam) object;
        return Objects.equals(page, that.page) && Objects.equals(pageSize, that.pageSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, pageSize);
    }

    @Override
    public String toString() {
        return "EmpLogQueryParam{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }
}
