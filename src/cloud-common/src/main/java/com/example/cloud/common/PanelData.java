package com.example.cloud.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author candy33
 * @Date 2021/12/16 10:55
 * @ClassName com.example.cloud.common
 **/
@Data
public class PanelData<T, E, M, N> implements Serializable {

    /**
     * 单头数据
     */
    private T info;

    /**
     * 单身B表
     */
    private SaveRecords<E> data;

    /**
     * 单身C表
     */
    private SaveRecords<M> twos;

    /**
     * 单身D表
     */
    private SaveRecords<N> threes;

    /**
     * 判定新增/修改状态
     */
    private Boolean isAdd;
}
