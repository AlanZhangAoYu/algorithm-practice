package com.example.cloud.common.vo;

import lombok.Data;

import java.util.List;

/**
 * @author wy
 * @date 2022/7/4
 */
@Data
public class DeviceParamVo {


    /**************设备连接相关********************/

    /**
     * 必填：设备连接IP
     */
    private String ip;

    /**
     * 必填：设备连接端口
     */
    private String port;

    /**
     * 必填：设备登陆用户名
     */
    private String longingName;

    /**
     * 必填：设备登陆密码
     */
    private String passWord;

    /**
     * 必填：请求接口地址
     */
    private String uri;
    /**
     *  连接超时时间设置
     */
    private String connectionTimeout;

    /**
     * 请求ID
     */
    private String streamId;

    /**
     * 请求的方法名称（必传）
     */
    private String methodName;

    /**
     * 前置机ip（必传）
     */
    private String premisesServerIp;


    /**************设备基础信息********************/

    /**
     * 设备名称
     */
    private String displayInfo;

    /**
     * 设备地址
     */
    private String location;

    /**
     * 子网掩码
     */
    private String subnetMask;

    /**
     * 默认网关
     */
    private String defaultGateway;

    /**
     * 设备id
     */
    private String deviceId;

    /**************人员基础信息********************/


    /**
     * 人员id
     */
    private String userId;

    /**
     * 人员名称
     */
    private String userName;

    /**
     * 人员编码（工号）
     */
    private String userCode;

    /**
     * 人员类型
     */
    private String userType;

    /**
     * 证件类型：  0:身份证  1:IC卡 99:其他
     */
    private int identificationType;

    /**
     * 证件号，范围:[1, 20]，大小写英文字母、数字；
     */
    private String identificationNumber;


    /**
     *身份证号
     */
    private String idCardNo;

    /**************人脸基础信息********************/

    /**
     * 人脸库类型
     */
    private String faceLibType;

    /**
     * 人脸库类型
     */
    private String faceLibId;

    /**
     * 人脸地址
     */
    private String facePath;

    /**
     * 查询人脸返回数据保存地址
     */
    private String outPutFacePath;

    /**************t通行时间信息********************/

    /**
     * 开始日期 yyyy-MM-dd HH:mm:ss
     */
    private String startDate;

    /**
     * 结束日期 yyyy-MM-dd HH:mm:ss
     */
    private String endDate;

    /**
     * 开始时间 yyyy-MM-dd HH:mm:ss
     */
    private String startTime;

    /**
     * 结束时间 yyyy-MM-dd HH:mm:ss
     */
    private String endTime;

    /**
     * 通行次数
     */
    private String transitTimes;

    /**
     * 有效开始时间
     */
    private String effectiveStartTime;

    /**
     *
     * 休息日开始日期：yyyy-MM-dd
     */
    private String restingStartDate;

    /**
     * 休息日期天数
     */
    private int restingDays;


    // 通行时间段
    private List<TimeSpanVo> timeSpans;

    /**
     * 通行时间模板id
     */
    private String templateId;
    /**
     * 通行时间模板名称
     */
    private String templateName;

    /**
     * 一周内的通行时间模板
     */
    private List<WeekSpanVo> weekSpans;

    /***********操作类型**************/

    /**
     * 接口操作类型：ADD;MODIFY;REMOVE
     */
    private String operationType;

    /***************订阅服务信息************************/

    /**
     * IP地址类型 0: IPv4  1: IPv6
     */
    private long subAddressType;

    /**
     * 订阅方设备IP地址
     */
    private String subIpAddress;

    /**
     * 订阅方设备端口
     */
    private long subPort;


    /**
     * 订阅时间，单位秒 范围：[30, 3600]
     */
    private long subDuration;

    /**
     * 订阅id：由订阅接口返回的订阅ID,一般用于刷新/删除订阅接口用
     */
    private long subId;

    /***************日志拉取************************/
    // 以下参数主要是YITU 设备的相关参数
    /**
     * 拉取日志类型（需要图片：include_image，不需要图片：non_image）：图片的保存地址需要配置
     */
    private String accessRecordsType;

    /**
     * 拉取日志的起始Id
     */
    private String checkpoint;

    /**
     * 是否需要重新从 checkpoint开始来去日志
     */
    private String reloadPull;

    /**
     * 文本内容
     */
    private String textContent;

    /**
     * 文本颜色
     */
    private int textColor;
    /**
     * 文本字体
     */
    private int textSizeSp;
    /**
     * 文本居中显示的X
     */
    private int textCenterPosXDp;
    /**
     * 文本居中显示的Y
     */
    private int textCenterPosYDp;

    /**
     * 前景内容显示时长： 0-30000
     */
    private int displayMillis;

    /**
     * 是否保存日志
     */
    private boolean yituSaveLog;
}

