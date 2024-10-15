/*
 * @Author: WZB 150590206+BJlaoma@users.noreply.github.com
 * @Date: 2024-10-13 16:43:16
 * @LastEditors: WZB 150590206+BJlaoma@users.noreply.github.com
 * @LastEditTime: 2024-10-13 21:11:07
 * @FilePath: \TrapMap\src\test\java\com\TrapMap\JDBCTest.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */

package com.TrapMap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://110.64.89.20:3306/tripmap";
        String url2 = "jdbc:mysql://localhost:3306/tripmap";
        String user = "root";
        String password = "1qaz2wsx3jkl";

        try (Connection connection = DriverManager.getConnection(url2, user, password)) {
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}