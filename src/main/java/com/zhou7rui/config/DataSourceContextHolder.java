package com.zhou7rui.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataSourceContextHolder {


    /** 数据源的KEY */
    public static final String DATASOURCE_KEY = "DATASOURCE_KEY";
    /** 数据源的URL */
    public static final String DATASOURCE_URL = "DATASOURCE_URL";
    /** 数据源的驱动 */
    public static final String DATASOURCE_DRIVER = "DATASOURCE_DRIVER";
    /** 数据源的用户名 */
    public static final String DATASOURCE_USERNAME = "DATASOURCE_USERNAME";
    /** 数据源的密码 */
    public static final String DATASOURCE_PASSWORD = "DATASOURCE_PASSWORD";


    private static final  ThreadLocal<Map<String,Object>> CONTEXT_HOLDER = new ThreadLocal<>();


    public static Map<String,Object> getDataSourceConfigMap () {
        Map<String,Object>  dataSourceConfigMap = CONTEXT_HOLDER.get();
        if(dataSourceConfigMap == null){
            dataSourceConfigMap = new HashMap<>();
        }
        return dataSourceConfigMap;
    }

    public static void clearDataSourceConfig() {
        CONTEXT_HOLDER.remove();
    }


    public static void setDataSource(Map<String,Object> confMap) {
        CONTEXT_HOLDER.set(confMap);
    }


}
