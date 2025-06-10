package com.example.pojo;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 员工操作日志实体类
 */
public class EmpLog {
    private Integer id; //ID
    private LocalDateTime operateTime; //操作时间
    private String info; //详细信息

    public EmpLog() {
    }

    public EmpLog(Integer id, LocalDateTime operateTime, String info) {
        this.id = id;
        this.operateTime = operateTime;
        this.info = info;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(LocalDateTime operateTime) {
        this.operateTime = operateTime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        EmpLog empLog = (EmpLog) object;
        return Objects.equals(id, empLog.id) && Objects.equals(operateTime, empLog.operateTime) && Objects.equals(info, empLog.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, operateTime, info);
    }

    @Override
    public String toString() {
        return "EmpLog{" +
                "id=" + id +
                ", operateTime=" + operateTime +
                ", info='" + info + '\'' +
                '}';
    }
}
