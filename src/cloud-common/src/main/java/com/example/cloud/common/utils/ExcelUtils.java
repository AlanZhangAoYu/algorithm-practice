package com.example.cloud.common.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.example.cloud.common.ResultCode;
import com.example.cloud.common.exception.ServiceException;
import org.apache.commons.beanutils.BeanUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Excel工具类
 * @author: candy33
 * @Date: 2021/11/12 14:24
 * com.example.cloud.business.utils
 */
public class ExcelUtils {

    /**
     * 导出excel中的数据，仅支持简单数据导出，不涉及单元格合并情况
     * @param file 文件
     * @param columnPropList 根据表格列index排序的字段名，读取的数据会按照当前集合的顺序进行组装
     * @param isDel 执行之后是否删除文件
     * @return
     */
    public static List<Map<String, Object>> readExcel(File file, List columnPropList, boolean isDel) {
        List<Map<String, Object>> lists = new ArrayList<>();
        EasyExcel.read(file, ExcelEntity.class, new PageReadListener<ExcelEntity>(dataList -> {
            dataList.forEach(item -> {
                Map<String, String> map;
                Map<String, Object> arrMap = new HashMap<>();
                try {
                    map = BeanUtils.describe(item);
                }catch (Exception e) {
                    throw new ServiceException(ResultCode.fail("excel读取转换异常！"));
                }
                int i = 1;
                for (Object s : columnPropList) {
                    String string = (String) s;
                    arrMap.put(string, map.getOrDefault("excel" + i, null));
                    i++;
                }
                lists.add(arrMap);
            });
        })).sheet().doRead();
        if (isDel) {
            file.delete();
        }
        return lists;
    }
}
