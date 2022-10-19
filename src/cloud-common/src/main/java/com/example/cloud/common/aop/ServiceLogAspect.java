package com.example.cloud.common.aop;

import com.example.cloud.common.ResultCode;
import com.example.cloud.common.exception.ServiceException;
import com.example.cloud.common.utils.HttpUtil;
import com.example.cloud.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Aspect
@Component
public class ServiceLogAspect {
    @Resource
    private ServiceLogStore serviceLogStore;

    /**
     * 定义日志收集切入点.
     */
    @Pointcut("@annotation(com.example.cloud.common.aop.ServiceLog)")
    public void serviceLog() {
    }

    @Around("serviceLog()")
    public Object round(ProceedingJoinPoint joinPoint) throws Throwable {

        /**
         * 记录请求开始时间
         */
        long startTime = System.currentTimeMillis();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        ServiceLog serviceLog = AnnotationUtils.getAnnotation(methodSignature.getMethod(), ServiceLog.class);

        Object value;
        try {
            value = joinPoint.proceed();
        }catch (Exception e) {
            value = e;
        }
        if (!(value instanceof ResultCode)) {
            // 如果是自定义抛出异常
            if (value instanceof ServiceException) {
                value = ((ServiceException) value).getResultModel();
            } else if (value instanceof ResponseEntity){
                // 接收文件
                value = "";
            } else {
                log.error("系统执行异常：", value);
                value = ResultCode.fail("程序执行错误，请联系管理员");
            }
        }
        OperateLog operateLog = new OperateLog();
        operateLog.setModule(serviceLog.module());
        operateLog.setPage(serviceLog.page());
        operateLog.setBusiness(serviceLog.business());
        operateLog.setPath(HttpUtil.getPath());
        operateLog.setOperateType(serviceLog.operateType().toString());
        operateLog.setCreatorId(serviceLogStore.getCreatorId());
        operateLog.setCreator(serviceLogStore.getCreator());
        operateLog.setCreateAt(LocalDateTime.now());
        operateLog.setIp(HttpUtil.getIP());
        operateLog.setParams(JsonUtil.toString(getParameters(joinPoint, methodSignature, serviceLog.operateType())));
        operateLog.setReturnData(JsonUtil.toString(value));
        operateLog.setReturnCode(((ResultCode) value).getCode());
        /**
         * 记录请求结束时间
         */
        long endTime = System.currentTimeMillis();
        operateLog.setRunTime(endTime - startTime);
        serviceLogStore.save(operateLog);
        return value;
    }

    /**
     * 获取调用方法传递的参数和值
     *
     * @param joinPoint
     * @return
     */
    private Map<String, Object> getParameters(ProceedingJoinPoint joinPoint, MethodSignature methodSignature, OperateType operateType) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String[] paramNames = methodSignature.getParameterNames();
        Map<String, Object> params = new HashMap<>();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            if (null != args[i]) {
                if (args[i] instanceof BindingResult) {
                    params.put(paramNames[i], "bindingResult");
                } else {
                    if (operateType == OperateType.UPLOAD) {
                        // 解决上传文件请求解析失败的问题，对于上传的文件，只获取文件名和文件大小
                        Map<String, Object> map = new HashMap<>();
                        Field[] fields = args[i].getClass().getDeclaredFields();
                        for (Field field : fields) {
                            try {
                                String getter = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                                Method method = args[i].getClass().getMethod(getter);
                                Object value = method.invoke(args[i]);
                                if (value instanceof MultipartFile) {
                                    MultipartFile file = (MultipartFile) value;
                                    if (file.isEmpty()) {
                                        map.put(field.getName(), "无文件");
                                    } else {
                                        Map<String, Object> fileMap = new HashMap<>();
                                        fileMap.put("name", file.getOriginalFilename());
                                        fileMap.put("size", file.getSize());
                                        map.put(field.getName(), fileMap);
                                    }
                                } else if (value instanceof Collection) {
                                    Collection collection = (Collection) value;
                                    Object[] o = collection.toArray();
                                    if (o.length == 0) {
                                        map.put(field.getName(), "无文件");
                                    } else {
                                        if (o[0] instanceof  MultipartFile) {
                                            List<Map> maps = new ArrayList<>();
                                            collection.forEach(item -> {
                                                MultipartFile file = (MultipartFile) item;
                                                if (!file.isEmpty()) {
                                                    Map<String, Object> fileMap = new HashMap<>();
                                                    fileMap.put("name", file.getOriginalFilename());
                                                    fileMap.put("size", file.getSize());
                                                    maps.add(fileMap);
                                                }
                                            });
                                            map.put(field.getName(), maps);
                                        }
                                    }
                                } else {
                                    map.put(field.getName(), value);
                                }
                            } catch (Exception e) {
                                log.error("日志获取列数据失败", e);
                                break;
                            }
                        }
                        params.put(paramNames[i], map);
                    } else {
                        params.put(paramNames[i], args[i]);
                    }
                }
            } else {
                params.put(paramNames[i], "无");
            }
        }
        return params;
    }
}
