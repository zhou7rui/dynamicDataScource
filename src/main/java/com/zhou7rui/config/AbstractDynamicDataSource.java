package com.zhou7rui.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

public abstract class AbstractDynamicDataSource<T extends DataSource> extends AbstractRoutingDataSource {

    public Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 默认的数据源KEY
     */
    protected static final String DEFAULT_DATASOURCE_KEY = "defaultDataSource";

    public abstract T createDataSource(String driverClassName, String url, String username,
                                       String password);


    private Map<Object, Object> targetDataSources;


    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        this.targetDataSources = targetDataSources;
        super.setTargetDataSources(targetDataSources);
        // afterPropertiesSet()方法调用时用来将targetDataSources的属性写入resolvedDataSources中的
        super.afterPropertiesSet();
    }

    /**
     * 设置系统当前使用的数据源
     */

    @Override
    protected String determineCurrentLookupKey() {

        //获取配置数据源信息
        Map<String, Object> configMap = DataSourceContextHolder.getDataSourceConfigMap();
        // 为空的话则返回 默认数据源
        if (configMap.isEmpty()) {
            return DEFAULT_DATASOURCE_KEY;
        }

        verifyAndInitDataSource();
        logger.info("切换数据源：{}", configMap.get(DataSourceContextHolder.DATASOURCE_KEY));
        return configMap.get(DataSourceContextHolder.DATASOURCE_KEY).toString();
    }


    /**
     * 判断是否需要初始化
     */
    private void verifyAndInitDataSource() {

        Map<String, Object> configMap = DataSourceContextHolder.getDataSourceConfigMap();

        if (configMap.isEmpty()) {
            return;
        }


        T dataSource = this.createDataSource(configMap.get(DataSourceContextHolder.DATASOURCE_DRIVER).toString(),
                configMap.get(DataSourceContextHolder.DATASOURCE_URL).toString(),
                configMap.get(DataSourceContextHolder.DATASOURCE_USERNAME).toString(),
                configMap.get(DataSourceContextHolder.DATASOURCE_PASSWORD).toString());

        //添加 数据源
        addTargetDataSource(configMap.get(DataSourceContextHolder.DATASOURCE_KEY).toString(), dataSource);

    }

    //添加数据源
    private void addTargetDataSource(String key, T dataSource) {
        this.targetDataSources.put(key, dataSource);
        super.setTargetDataSources(this.targetDataSources);
        // afterPropertiesSet()方法调用时用来将targetDataSources的属性写入resolvedDataSources中的
        super.afterPropertiesSet();
    }


}
