/*
 * @Author: WZB 150590206+BJlaoma@users.noreply.github.com
 * @Date: 2024-10-13 21:10:57
 * @LastEditors: WZB 150590206+BJlaoma@users.noreply.github.com
 * @LastEditTime: 2024-10-13 23:03:59
 * @FilePath: \TrapMap\src\test\java\com\TrapMap\JDBCpropertiesTest.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package com.TripMap.test;

import com.TripMap.Config.JDBCproperties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;;

public class JDBCpropertiesTest {
    
    @Autowired
    static
    JDBCproperties jdbc;
    public static JDBCproperties TestAutowired(){
        return jdbc;
    }
    public static void main(String[] args) {

        JDBCproperties jdbc=TestAutowired();
        try (Connection connection = DriverManager.getConnection(jdbc.getUrl(), jdbc.getUsername(), jdbc.getPassword())) {
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
