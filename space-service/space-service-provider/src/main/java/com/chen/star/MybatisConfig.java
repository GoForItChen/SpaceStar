package com.chen.star;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


@Configuration
@ConditionalOnClass(SqlSessionFactory.class)
public class MybatisConfig {

    /**
     * 数据源1
     *
     * @return
     */
    @Bean(name = "center")
    @ConfigurationProperties(prefix = "spring.datasource.center")
    public DataSource master()
    {
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "dynamicDataSource")
    public DynamicDataSource DynamicDataSource(DataSource center)
    {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setMaster(center);
        List<DataSource> list = new ArrayList<>();
        dynamicDataSource.setSlaves(list);
        return dynamicDataSource;
    }

    @Bean("transactionManager")
    public PlatformTransactionManager transactionManager(DynamicDataSource DynamicDataSource)
    {
        return new DataSourceTransactionManager(DynamicDataSource);
    }
}
