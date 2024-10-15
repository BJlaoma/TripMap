/*
 * @Author: WZB 150590206+BJlaoma@users.noreply.github.com
 * @Date: 2024-10-16 00:54:03
 * @LastEditors: WZB 150590206+BJlaoma@users.noreply.github.com
 * @LastEditTime: 2024-10-16 01:41:23
 * @FilePath: \TripMap\src\main\java\com\TripMap\pojo\Pair.java
 */
package com.TripMap.pojo;

import lombok.Data;

@Data
public class Pair<T1,T2> {
    T1 first;
    T2 second;
    public Pair(T1 first,T2 second){
        this.first=first;
        this.second=second;
    }
}
