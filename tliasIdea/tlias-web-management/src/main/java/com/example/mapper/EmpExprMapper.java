package com.example.mapper;

import com.example.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 员工工作经历的持久层接口
 */
@Mapper
public interface EmpExprMapper {
    /*
     * 批量添加员工工作经历
     */
    void addBatch(List<EmpExpr> exprList);
}
