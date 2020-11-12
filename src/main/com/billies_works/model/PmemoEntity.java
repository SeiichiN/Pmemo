// PmemoEntity.java
package com.billies_works.model;

import java.sql.*;

public class PmemoEntity {
    private int no;
    private String name;
    private String id;
    private String email;
    private String password;
    private String other;
    private String created_at;

    public PmemoEntity() {}
    
    public PmemoEntity( String name, String id, String email,
                 String password, String other) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.password = password;
        this.other = other;
    }

    public int getNo()          { return no; }
    public String getName()     { return name; }
    public String getId()       { return id; }
    public String getEmail()    { return email; }
    public String getPassword() { return password; }
    public String getOther()    { return other; }
    public String getCreated_at() { return created_at; }

    public void setNo( int no )        { this.no = no; }
    public void setName(String name)   { this.name = name; }
    public void setId(String id)       { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setOther(String other) { this.other = other; }
    public void setCreated_at(String created_at) {this.created_at = created_at; }

    public String toString() {
        return
            "No: " + getNo() + "\n"
            + "1) 登録名: " + getName() + "\n"
            + "2) ID: "+ getId() + "\n"
            + "3) メールアドレス: " + getEmail() + "\n"
            + "4) パスワード: " + getPassword() + "\n"
            + "5) メモ: " + getOther() + "\n"
            + "作成日時: " + getCreated_at();
    }
}

// 修正時刻: Thu Nov 12 15:24:28 2020
