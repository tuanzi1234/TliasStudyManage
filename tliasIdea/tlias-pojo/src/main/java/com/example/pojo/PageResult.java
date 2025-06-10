package com.example.pojo;

import java.util.List;
import java.util.Objects;

/**
 * 分页结果
 */
public class PageResult<T> {
    private Long total; // 总记录数
    private List<T> rows; // 当前页结果

    public PageResult() {
    }

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PageResult<?> that = (PageResult<?>) object;
        return Objects.equals(total, that.total) && Objects.equals(rows, that.rows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, rows);
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
