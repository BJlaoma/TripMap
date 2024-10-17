package com.mongodbObject;

public class User {
    private String name;
    private String password;
    private int age;
    private String email;

    // 无参构造函数
    public User() {}

    // 带参构造函数
    public User(String name, String password, int age, String email) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.email = email;
    }

    // Getter 和 Setter 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}