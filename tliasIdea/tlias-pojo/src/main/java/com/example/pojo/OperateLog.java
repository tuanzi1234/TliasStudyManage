package com.example.pojo;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 操作日志
 */
public class OperateLog {
    private Integer id;
    private Integer operateEmpId;
    private LocalDateTime operateTime;
    private String className;
    private String methodName;
    private String methodParams;
    private String returnValue;
    private Long  costTime;

    private String operateEmpName;

    public OperateLog() {
    }


    public OperateLog(Integer id, Integer operateEmpId, LocalDateTime operateTime, String className, String methodName, String methodParams, String returnValue, Long costTime, String operateEmpName) {
        this.id = id;
        this.operateEmpId = operateEmpId;
        this.operateTime = operateTime;
        this.className = className;
        this.methodName = methodName;
        this.methodParams = methodParams;
        this.returnValue = returnValue;
        this.costTime = costTime;
        this.operateEmpName = operateEmpName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOperateEmpId() {
        return operateEmpId;
    }

    public void setOperateEmpId(Integer operateEmpId) {
        this.operateEmpId = operateEmpId;
    }

    public LocalDateTime getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(LocalDateTime operateTime) {
        this.operateTime = operateTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodParams() {
        return methodParams;
    }

    public void setMethodParams(String methodParams) {
        this.methodParams = methodParams;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    public Long getCostTime() {
        return costTime;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }

    public String getOperateEmpName() {
        return operateEmpName;
    }

    public void setOperateEmpName(String operateEmpName) {
        this.operateEmpName = operateEmpName;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        OperateLog that = (OperateLog) object;
        return Objects.equals(id, that.id) && Objects.equals(operateEmpId, that.operateEmpId) && Objects.equals(operateTime, that.operateTime) && Objects.equals(className, that.className) && Objects.equals(methodName, that.methodName) && Objects.equals(methodParams, that.methodParams) && Objects.equals(returnValue, that.returnValue) && Objects.equals(costTime, that.costTime) && Objects.equals(operateEmpName, that.operateEmpName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, operateEmpId, operateTime, className, methodName, methodParams, returnValue, costTime, operateEmpName);
    }

    @Override
    public String toString() {
        return "OperateLog{" +
                "id=" + id +
                ", operateEmpId=" + operateEmpId +
                ", operateTime=" + operateTime +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", methodParams='" + methodParams + '\'' +
                ", returnValue='" + returnValue + '\'' +
                ", costTime=" + costTime +
                ", operateEmpName='" + operateEmpName + '\'' +
                '}';
    }
}
