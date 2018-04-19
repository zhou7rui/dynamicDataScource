package com.zhou7rui.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.util.List;


@ConfigurationProperties("spring.datasource.druid")
public class DynamicDataSource extends AbstractDynamicDataSource<DruidDataSource> {


    private int initialSize;

    private int maxActive;

    private int minIdle;

    private int maxWait;


    // 配置监控统计拦截的filters
    private String filters; // 监控统计："stat" 防SQL注入："wall" 组合使用： "stat,wall"
    private List<Filter> filterList;

    @Override
    public DruidDataSource createDataSource(String driverClassName, String url, String username, String password) {
        logger.info("create datasource .. ");
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);

        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setMinIdle(minIdle);
        druidDataSource.setMaxWait(maxWait);
//        druidDataSource.setTimeBetweenEvictionRunsMillis(defaultDataSource.getTimeBetweenEvictionRunsMillis());
//        druidDataSource.setMinEvictableIdleTimeMillis(defaultDataSource.getMinEvictableIdleTimeMillis());
//        druidDataSource.setPoolPreparedStatements(defaultDataSource.isPoolPreparedStatements());


        if (StringUtils.isEmpty(filters))
            try {
                druidDataSource.setFilters(filters);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        addFilterList(druidDataSource);
        return druidDataSource;
    }


    private void addFilterList(DruidDataSource ds) {
        if (filterList != null) {
            List<Filter> targetList = ds.getProxyFilters();
            for (Filter add : filterList) {
                boolean found = false;
                for (Filter target : targetList) {
                    if (add.getClass().equals(target.getClass())) {
                        found = true;
                        break;
                    }
                }
                if (!found)
                    targetList.add(add);
            }
        }
    }


    public int getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }


}
