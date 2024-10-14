/*
 * @Author: WZB 150590206+BJlaoma@users.noreply.github.com
 * @Date: 2024-10-12 06:24:30
 * @LastEditors: WZB 150590206+BJlaoma@users.noreply.github.com
 * @LastEditTime: 2024-10-15 00:39:20
 * @FilePath: \TrapMap\src\main\java\com\TripMap\TripMapApplication.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package com.TripMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class TripMapApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripMapApplication.class, args);
	}

}
