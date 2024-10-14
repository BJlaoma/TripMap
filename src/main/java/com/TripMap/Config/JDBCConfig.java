/*
 * @Author: WZB 150590206+BJlaoma@users.noreply.github.com
 * @Date: 2024-10-12 19:34:25
 * @LastEditors: WZB 150590206+BJlaoma@users.noreply.github.com
 * @LastEditTime: 2024-10-12 19:37:43
 * @FilePath: \TrapMap\src\main\java\com\TripMap\Config\JDBCConfig.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
*/ 
package com.TripMap.Config;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import com.alibaba.druid.pool.DruidDataSource;

//@Configuration//配置类
//@EnableConfigurationProperties(JDBCproperties.class)
//@PropertySource("classpath:jdbc.properties")

public class JDBCConfig { 
@Bean
public DataSource dataSource(JDBCproperties jdbCproperties){
    DruidDataSource dataSource=new DruidDataSource();
    dataSource.setDriverClassName(jdbCproperties.getDriver());
    dataSource.setUrl(jdbCproperties.getUrl());
    dataSource.setUsername(jdbCproperties.getUsername());
    dataSource.setPassword(jdbCproperties.getPassword());
    return dataSource; 
}
}
