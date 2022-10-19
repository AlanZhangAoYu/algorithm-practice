package com.example.cloud.common.vo;

import lombok.Data;

/**
 * @author yuhongguang
 * @date 2022/7/29 14:06
 */
@Data
public class VirtualAccountUserVo {
    private Integer id;
    private String realName;
    private String phone;
    private String userNo;
    private Boolean isOpenAccount;
    private String firmName;
    private Integer parkId;
    private String parkName;

    public VirtualAccountUserVo() {
    }

    public VirtualAccountUserVo(Integer id, String realName, String phone, String userNo, Boolean isOpenAccount, String firmName,Integer parkId,String parkName) {
        this.id = id;
        this.realName = realName;
        this.phone = phone;
        this.userNo = userNo;
        this.isOpenAccount = isOpenAccount;
        this.firmName = firmName;
        this.parkId = parkId;
        this.parkName = parkName;
    }
}
