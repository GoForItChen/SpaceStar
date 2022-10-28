package com.chen.star.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.chen.star.config.database.DynamicDataSource;
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
public class MybatisPlusConfig {

    /**
     * 数据源1
     *
     * @return
     */
    @Bean(name = "master")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource master()
    {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 数据源2
     *
     * @return
     */
    @Bean(name = "slave1")
    @ConfigurationProperties(prefix = "spring.datasource.slave1")
    public DataSource slave1()
    {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 数据源3
     *
     * @return
     */
//    @Bean(name = "slave2")
//    @ConfigurationProperties(prefix = "spring.datasource.slave2")
//    public DataSource slave2()
//    {
//        return DruidDataSourceBuilder.create().build();
//    }

    @Primary
    @Bean(name = "dynamicDataSource")
    public DynamicDataSource DynamicDataSource(DataSource master, DataSource slave1)
    {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setMaster(master);
        List<DataSource> list = new ArrayList<>();
        list.add(slave1);
//        list.add(slave2);
        dynamicDataSource.setSlaves(list);
        return dynamicDataSource;
    }

    @Bean("transactionManager")
    public PlatformTransactionManager transactionManager(DynamicDataSource DynamicDataSource)
    {
        return new DataSourceTransactionManager(DynamicDataSource);
    }
}
