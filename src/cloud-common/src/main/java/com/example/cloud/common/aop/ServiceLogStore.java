package com.example.cloud.common.aop;

/**
 * 业务日志存储
 */
public interface ServiceLogStore {
    /**
     * 获取操作者id编号
     *
     * @return
     */
    Integer getCreatorId();

    /**
     * 获取操作者的姓名
     *
     * @return
     */
    String getCreator();

    /**
     * 保存日志
     *
     * @param operateLog
     */
    void save(OperateLog operateLog);
}
