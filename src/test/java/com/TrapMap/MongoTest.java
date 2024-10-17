/*
 * @Author: WZB 150590206+BJlaoma@users.noreply.github.com
 * @Date: 2024-10-14 20:17:29
 * @LastEditors: WZB 150590206+BJlaoma@users.noreply.github.com
 * @LastEditTime: 2024-10-15 00:23:53
 * @FilePath: \TripMap\src\test\java\com\TrapMap\MongoTest.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package com.TrapMap;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;


public class MongoTest {

    
    public static void main(String[] args) {
        String uri = "mongodb://localhost:27017";

        try (com.mongodb.client.MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("journeymap");
            System.out.println("Connection successful to database: " + database.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}