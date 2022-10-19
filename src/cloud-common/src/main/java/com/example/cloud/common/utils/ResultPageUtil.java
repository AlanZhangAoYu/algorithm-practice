package com.example.cloud.common.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 联合查询传递的SQL以及查询条件，分页数据等
 *
 * @author lisheng
 */
@Data
@Slf4j
public class ResultPageUtil<T> {

    /**
     *
     */
    private Integer id;

    /**
     * JOSN [{key:'',value,'',symbol:''}]
     */
    private String searchDataArr;

    /**
     * 排序的HQL
     */
    private String sort = "";

    /**
     * 是否分页，默认分页，false为不分页
     */
    private boolean isPage = true;

    /**
     * 查询页码
     */
    private Integer currentPage = 1;

    /**
     * 每一页条数
     */
    private Integer pageSize = 10;

    /**
     * 总条数
     */
    private Integer total;

    /**
     * 数据集合
     */
    private List<T> records;

    /**
     * 总页码数
     */
    private Integer pageCount;

    /**
     * 搜索HQL
     */
    private String searchHql = "";

    /**
     * 搜索HQL对应的绑定值
     */
    private Map<String, Object> queryMap = new HashMap<String, Object>();

    /**
     * 前端表格传入的排序规则
     */
    private String advancedOrder;

    /**
     * 后台设置的排序规则
     */
    private String orderBy;

    /**
     * 搜索字段
     */
    private String searchType;

    /**
     * 搜索值
     */
    private String searchValue;

    /**
     * 搜索字段
     */
    private String paramKey;

    /**
     * 搜索值
     */
    private String paramValue;

    private Integer parkId;

    public void setCurrentPage(Integer currentPage) {
        if (currentPage == null) {
            this.currentPage = 1;
        } else {
            this.currentPage = currentPage;
        }
    }

    public void setPageSize(Integer pageSize) {
        if ((pageSize == null)) {
            this.pageSize = 20;
        } else {
            this.pageSize = pageSize;
        }
    }

    public void setTotal(Integer total) {
        if (total == null || total == 0) {
            this.total = 0;
            this.pageCount = 0;
        } else {
            this.total = total;
            if (this.total % this.pageSize == 0) {
                this.pageCount = this.total / this.pageSize;
            } else {
                this.pageCount = (this.total / this.pageSize) + 1;
            }
        }
        if(this.pageCount != 0 && (this.pageCount < this.currentPage)) {
            this.currentPage = this.pageCount;
        }
    }


    public static ResultPageUtil getInstance() {
        ResultPageUtil resultPageUtil = new ResultPageUtil();
        HttpServletRequest request = SpringContextUtil.getRequest();
        Map<String, String[]> maps = request.getParameterMap();
        resultPageUtil.setRecords(new ArrayList());
        resultPageUtil.setTotal(0);
        try{
            String currentPage = maps.get("currentPage") != null ? maps.get("currentPage")[0] : "1";
            if (StringUtils.isNotBlank(currentPage)) {
                resultPageUtil.setCurrentPage(Integer.valueOf(currentPage));
            }

            String pageSize = maps.get("pageSize") != null ? maps.get("pageSize")[0] : "1";
            if (StringUtils.isNotBlank(pageSize)) {
                resultPageUtil.setPageSize(Integer.valueOf(pageSize));
            }

            String searchType = maps.get("searchType") != null ? maps.get("searchType")[0] : "";
            if (StringUtils.isNotBlank(searchType)) {
                resultPageUtil.setSearchType(searchType);
            }

            String searchValue = maps.get("searchValue") != null ? maps.get("searchValue")[0] : "";
            if (StringUtils.isNotBlank(searchValue)) {
                resultPageUtil.setSearchType(searchValue);
            }

            if (maps.containsKey("_query")) {
                if (StringUtils.isNotEmpty(maps.get("_query")[0])) {
                    List<Map> list = JsonUtil.toList(maps.get("_query")[0], Map.class);
                    StringBuilder HQL = new StringBuilder();
                    AtomicInteger index = new AtomicInteger();
                    list.forEach(item -> {
//                        String mapKey = "param"+index.get();
//                        HQL.append(" ").append(item.get("connect")).append(" ").append(item.get("key")).append(" ")
//                                .append(item.get("symbol")).append(" :").append(mapKey);
                        HQL.append(" ").append(item.get("connect")).append(" ").append(item.get("key")).append(" ")
                                .append(item.get("symbol")).append("'").append(item.get("value").toString()).append("'");
//                        resultPageUtil.queryMap.put(mapKey, item.get("value").toString());
                        index.getAndIncrement();
                    });
                    resultPageUtil.setSearchHql(HQL.toString());
                }
//                resultPageUtil.setSearchHql(StringUtils.isEmpty(maps.get("_query")[0]) ? "" : maps.get("_query")[0]);
            }

            if (maps.containsKey("_sort")) {
                resultPageUtil.setSort( StringUtils.isNotBlank(maps.get("_sort")[0]) ? " ORDER BY " + maps.get("_sort")[0] : "");
            }

            if (maps.containsKey("_isPage")) {
                resultPageUtil.setPage(Boolean.parseBoolean(maps.get("_isPage")[0]));
            }

            String id = maps.get("id") != null ? maps.get("id")[0] : "1";
            if (StringUtils.isNotBlank(id)) {
                resultPageUtil.setId(Integer.valueOf(id));
            }

        }catch (Exception e) {
            log.error(e.toString());
            resultPageUtil.setCurrentPage(1);
            resultPageUtil.setPageSize(20);
        }

        return resultPageUtil;
    }

}
