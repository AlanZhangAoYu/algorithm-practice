package com.example.cloud.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DefaultServiceLogStore implements ServiceLogStore {
    @Override
    public Integer getCreatorId() {
        return -1;
    }

    @Override
    public String getCreator() {
        return "-";
    }

    @Override
    public void save(OperateLog operateLog) {
        log.info("operate-log: {}", operateLog.toString());
    }
}
