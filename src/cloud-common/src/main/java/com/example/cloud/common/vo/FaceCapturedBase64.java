package com.example.cloud.common.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FaceCapturedBase64 {

    // 人脸子图的图片数据(Base64)
    private String smallBytes;

    // 背景图的图片数据(Base64)
    private String bigBytes;

    // 抓拍时间
    private String captureTime;

    // 设备ID
    private String deviceId;
}
