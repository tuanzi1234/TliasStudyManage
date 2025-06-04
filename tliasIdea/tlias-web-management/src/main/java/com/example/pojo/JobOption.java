package com.example.pojo;

import java.util.List;
import java.util.Objects;

/**
 * 折线图统计数据的实体类
 */
public class JobOption {
    private List jobList;
    private List DataList;

    public JobOption() {
    }

    public JobOption(List jobList, List dataList) {
        this.jobList = jobList;
        DataList = dataList;
    }

    public List getJobList() {
        return jobList;
    }

    public void setJobList(List jobList) {
        this.jobList = jobList;
    }

    public List getDataList() {
        return DataList;
    }

    public void setDataList(List dataList) {
        DataList = dataList;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        JobOption jobOption = (JobOption) object;
        return Objects.equals(jobList, jobOption.jobList) && Objects.equals(DataList, jobOption.DataList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobList, DataList);
    }

    @Override
    public String toString() {
        return "JobOption{" +
                "jobList=" + jobList +
                ", DataList=" + DataList +
                '}';
    }
}
