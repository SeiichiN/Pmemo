// PmemoEntity.java
package com.billies_works;

import java.sql.*;

public class PmemoEntity {
    private String name;
    private String id;
    private String email;
    private String password;
    private String other;
    private String created_at;

    public String getName()     { return name; }
    public String getId()       { return id; }
    public String getEmail()    { return email; }
    public String getPassword() { return password; }
    public String getOther()    { return other; }
    public String getCreated_at() { return created_at; }
    
    public void setName(String name)   { this.name = name; }
    public void setId(String id)       { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setOther(String other) { this.other = other; }
    public void setCreated_at(String created_at) {this.created_at = created_at; }

    public String toString() {
        return "1) 登録名: " + name + "\n"
            + "2) ID: "+ id + "\n"
            + "3) メールアドレス: " + email + "\n"
            + "4) パスワード: " + password + "\n"
            + "5) メモ: " + other + "\n"
            + "作成日時: " + created_at;
    }
}
