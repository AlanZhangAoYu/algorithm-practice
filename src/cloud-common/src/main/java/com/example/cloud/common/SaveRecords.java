package com.example.cloud.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: candy33
 * @Date: 2021/10/15 17:28
 * com.example.cloud.common
 */
@Data
public class SaveRecords<T> implements Serializable {
    /**
     * 插入的行数组
     */
    private List<T> insertRecords;

    /**
     * 更新的行数组
     */
    private List<T> updateRecords;

    /**
     * 删除的行数组
     */
    private List<T> removeRecords;

    /**
     * 标记未处理的行数组
     */
    private List<T> pendingRecords;
}
