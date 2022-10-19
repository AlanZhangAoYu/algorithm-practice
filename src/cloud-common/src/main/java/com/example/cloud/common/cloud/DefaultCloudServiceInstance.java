package com.example.cloud.common.cloud;

import org.springframework.stereotype.Component;

@Component
public class DefaultCloudServiceInstance implements CloudServiceInstance {
    @Override
    public String url(String serviceName) {
        return serviceName;
    }
}