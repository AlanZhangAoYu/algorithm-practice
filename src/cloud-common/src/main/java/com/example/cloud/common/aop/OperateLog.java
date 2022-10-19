package com.example.cloud.common.aop;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@ToString
@Data
public class OperateLog implements Serializable {
    /**
     * 模块名称
     */
    private String module;

    /**
     * 所在页面
     */
    private String page;

    /**
     * 业务描述
     */
    private String business;

    /**
     * 请求的接口地址
     */
    private String path;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 操作人id
     */
    private Integer creatorId;

    /**
     * 操作人
     */
    private String creator;

    /**
     * 操作时间
     */
    private LocalDateTime createAt;

    /**
     * 操作者ip地址
     */
    private String ip;

    /**
     * 传递参数
     */
    private String params;

    /**
     * 返回的状态码
     */
    private Integer returnCode;

    /**
     * 返回的数据
     */
    public String returnData;

    /**
     * 程序执行时长
     */
    public long runTime;
}