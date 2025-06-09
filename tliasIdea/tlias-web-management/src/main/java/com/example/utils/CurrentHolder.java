package com.example.utils;

import com.example.pojo.OperateLog;

public class CurrentHolder {
    private static final ThreadLocal<Integer> CURRENT_LOCAL = new ThreadLocal<>();

    public static void setOperateId(Integer employeeId) {
        CURRENT_LOCAL.set(employeeId);
    }

    public static Integer getOperateLog() {
        return CURRENT_LOCAL.get();
    }

    public static void removeOperateLog() {
        CURRENT_LOCAL.remove();
    }
}
