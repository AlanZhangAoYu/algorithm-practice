package com.example.cloud.common.aop;

/**
 * @Author candy33
 * @Date 2021/12/9 9:55
 * @ClassName com.example.cloud.common.aop
 **/
public enum OperateType {

    /**
     * 新增、创建、添加等操作
     */
    CREATE,

    /**
     * 修改、更新等操作
     */
    MODIFY,

    /**
     * 删除操作
     */
    DELETE,

    /**
     * 登录接口专用
     */
    LOGIN,

    /**
     * 登出接口专用
     */
    LOGOUT,

    /**
     * 文件上传、下载等操作
     */
    FILE,

    /**
     * 上传文件
     */
    UPLOAD,

    /**
     * 下载文件
     */
    DOWNLOAD,

    /**
     * 接口调用操作
     */
    INTERFACE,
}
