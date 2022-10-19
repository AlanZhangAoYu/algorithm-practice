package com.example.cloud.common.vo;

import com.example.cloud.common.vo.TimeSpanVo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 一周内的时间计划模板
 */
@Getter
@Setter
public class WeekSpanVo {

    //星期索引：从0开始 0：周一；1：周二； 2：周三；3：周四；4：周五；5：周六；6：周日；
    private int day;

    // 当前的时间模板
    private List<TimeSpanVo> timeSpans;

}
