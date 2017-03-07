package com.katey2658.wechatserver.config.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;

/**
 * 关于应用dao层相关依赖
 * Created by 11456 on 2017/3/6.
 */
@Configuration
@PropertySource("classpath:jdbc.properties")
@ComponentScan(basePackages = {"com.katey2658.wechatserver.dao"})
public class RootDaoConfig {

    /**
     * Spring的Enviroment,可以用这里检索属性
     */
    @Autowired
    private Environment env;

    /**
     * 获得数据库源，配置数据库连接池
     * @return
     */
    @Bean
    public DataSource getDataSource(){
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(env.getProperty("jdbc.driver",String.class));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        dataSource.setUser(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        dataSource.setMaxPoolSize(20);
        dataSource.setMinPoolSize(10);
        dataSource.setAutoCommitOnClose(false);
        dataSource.setCheckoutTimeout(1000);
        //尝试重新连接次数
        dataSource.setAcquireIncrement(2);
        //dataSource.set
        return dataSource;
    }

    /**
     * 获得MyBatis工厂Bean
     * @param dataSource
     * @return
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean getSqlSessionFactory(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean factoryBean=new SqlSessionFactoryBean();
        //设置连接池
        factoryBean.setDataSource(getDataSource());
        //设置配置文件
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/*.xml"));
        //设置Mybatis全局配置
        factoryBean.setConfiguration(getConfiguration());
        //扫描entity包，使用别名
        factoryBean.setTypeAliasesPackage("com.katey2658.wechatserver.entity");
        return factoryBean;
    }

    /**
     * Mybatis的配置文件
     * @return
     */
    @Bean
    public org.apache.ibatis.session.Configuration getConfiguration(){
        org.apache.ibatis.session.Configuration configuration=new org.apache.ibatis.session.Configuration();
        //使用jdbc的generatedKey获取数据库自增主键
        configuration.setUseGeneratedKeys(true);
        //使用列名替换别名
        configuration.setUseColumnLabel(true);
        //使用驼峰写法
        configuration.setMapUnderscoreToCamelCase(true);
        return configuration;
    }

    /**
     * 动态扫描接口包，实现动态实现dao接口，注入到容器中
     * @return
     */
    @Bean
    public MapperScannerConfigurer getMapperScannerConfigurer(){
        MapperScannerConfigurer configurer=new MapperScannerConfigurer();
        //注入工厂Bean，注意这里直接写名字
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        //设置扫描接口
        configurer.setBasePackage("com.katey2658.wechatserver.dao");
        return configurer;
    }
}
