package com.example.cloud.common.aop;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ServiceLog {
    /**
     * 操作模块
     *
     * @return
     */
    String module();

    /**
     * 所在页面
     * @return
     */
    String page();

    /**
     * 业务描述
     *
     * @return
     */
    String business() default "";

    /**
     * 操作类型 create modify delete
     *
     * @return
     */
    OperateType operateType();
}
