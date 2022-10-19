package com.example.cloud.common.constant;

/**
 * @author: candy33
 * @Date: 2021/09/22 10:44
 * com.example.cloud.common.constant
 */
public class RedisConstant {

    /**
     *
     */
    public final static String REDIS_USER_TOKEN_PREFIX = "USER_TOKEN_";

    /**
     * 用户token前缀
     */
    public final static String REDIS_USER_INFO = "USER_INFO_";

    /**
     * 用户菜单前缀
     */
    public final static String REDIS_MENU_INFO = "MENU_INFO_";

    /**
     * 用户权限前缀
     */
    public final static String REDIS_AUTH_INFO = "AUTH_INFO_";

    /**
     * 字典前缀
     */
    public final static String REDIS_DICT_ALL = "DICT_ALL";

    /**
     * 表格列前缀
     */
    public final static String REDIS_TABLE_COLUMN = "TABLE_COLUMN_";

    /**
     * 表格前缀
     */
    public final static String REDIS_TABLE_INFO = "TABLE_INFO_";

    /**
     * 表格panel前缀
     */
    public final static String REDIS_TABLE_PANEL = "TABLE_PANEL_";

    /**
     * 表格panel前缀
     */
    public final static String REDIS_SPECIAL_ALL = "SPECIAL_ALL";

    /**
     * 用户token超时默认时间
     */
    public final static Integer REDIS_USER_TIMEOUT = 2 * 60 * 60 * 1000;

    /**
     * system模块用户部分信息
     */
    public final static String SYSTEM_MODEL_USER = "SYSTEM_MODEL_USER";

    /**
     * system模块菜单部分信息
     */
    public final static String SYSTEM_MODEL_MENU = "SYSTEM_MODEL_MENU";

    /**
     * system模块字典部分信息
     */
    public final static String SYSTEM_MODEL_DICT = "SYSTEM_MODEL_DICT";

    /**
     * system模块表格列部分信息
     */
    public final static String SYSTEM_MODEL_TABLE = "SYSTEM_MODEL_TAB";

    /**
     * system模块全局参数部分信息
     */
    public final static String SYSTEM_MODEL_SPECIAL = "SYSTEM_MODEL_SPECIAL";


    /**
     * auth-server模块
     */
    public final static String CLOUD_AUTH = "CLOUD_AUTH_";

    /**
     * 权限key
     */
    public final static String AUTHORITIES = "AUTHORITIES_";
}
