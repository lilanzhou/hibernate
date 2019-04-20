package com.ryan.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2019:04:19
 *
 * @Author : Lilanzhou
 * ���� :
 */
public class User implements Serializable {

    private String username;
    private String password;
    public User(){

    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
