package com.example.cloud.common.vo;

import lombok.Data;

import java.util.List;

/**
 * @author yuhongguang
 * @date 2022/7/14 2:15
 */
@Data
public class AccessPermissionsVo {

    Integer parkId;//园区ID（必传）
    Integer firmId;//企业ID
    Integer roleId;//角色ID
    Integer userId;//用户ID
    Integer type;//类型（1：人员管理传入，2：角色管理传入,3：企业管理传入）
    List<Integer> equipentIds;//设备ID（必传）

}
