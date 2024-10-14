/*
 * @Author: WZB 150590206+BJlaoma@users.noreply.github.com
 * @Date: 2024-10-15 00:15:10
 * @LastEditors: WZB 150590206+BJlaoma@users.noreply.github.com
 * @LastEditTime: 2024-10-15 00:15:14
 * @FilePath: \TripMap\src\main\java\com\TripMap\Config\MongoConfig.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package com.TripMap.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;

@Configuration
public class MongoConfig {

    /*
     * Factory bean that creates the com.mongodb.client.MongoClient instance
     */
     public @Bean MongoClientFactoryBean mongo() {
          MongoClientFactoryBean mongo = new MongoClientFactoryBean();
          mongo.setHost("localhost");
          return mongo;
     }
}