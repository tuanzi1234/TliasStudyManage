package com.example.pojo;

import java.util.Objects;

/**
 * 登录信息
 */
public class LoginInfo {
    private Integer  id;
    private String  username;
    private String name;
    private String token;

    public LoginInfo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        LoginInfo loginInfo = (LoginInfo) object;
        return Objects.equals(id, loginInfo.id) && Objects.equals(username, loginInfo.username) && Objects.equals(name, loginInfo.name) && Objects.equals(token, loginInfo.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, name, token);
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
