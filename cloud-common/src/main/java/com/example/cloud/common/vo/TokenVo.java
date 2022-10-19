package com.example.cloud.common.vo;

import lombok.Data;

/**
 * @author yuhongguang
 * @date 2022/7/5 18:32
 */
@Data
public class TokenVo {
    String userId;
    String userName;
    String tenantId;
    String admin;
    String clientGroup;

    public TokenVo(String userId, String userName, String tenantId, String admin, String clientGroup) {
        this.userId = userId;
        this.userName = userName;
        this.tenantId = tenantId;
        this.admin = admin;
        this.clientGroup = clientGroup;
    }

    public TokenVo() {
    }
}
