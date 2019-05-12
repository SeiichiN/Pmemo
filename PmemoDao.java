// PmemoDao.java
package com.billies_works;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class PmemoDao {
    private Connection conn;

    public PmemoDao (Connection connect) {
        this.conn = connect;
    }

    // memoテーブルに1件分のデータを挿入する
    public int insertData(PmemoEntity pmemo, String tableName) throws SQLException {
        PreparedStatement stmt = null;

        try {
            String query = "insert into " + tableName
                + " (name, id, email, password, other) values (?,?,?,?,?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, pmemo.getName());
            stmt.setString(2, pmemo.getId());
            stmt.setString(3, pmemo.getEmail());
            stmt.setString(4, pmemo.getPassword());
            stmt.setString(5, pmemo.getOther());
            return stmt.executeUpdate();
        }
        finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    /**
     * データの修正
     * @param:
     *   String name -- 修正するデータのキー
     *   int editNo  -- 修正するデータの番号 1..5
     *   String newText -- 新しいデータ
     *   String tableName -- テーブル名
     * @return:
     *   int stmt.executeUpdate -- SQLを実行した行数（0:失敗, 1:１行修正）
     */
    public int updateData(String name, int editNo, String newText, String tableName)
        throws SQLException {
        
        String[] item = {"", "name", "id", "email", "password", "other"};
        PreparedStatement stmt = null;

        try {
            String query = "update " + tableName
                + " set " + item[editNo] + " = ?, created_at = now() where name = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, newText);
            stmt.setString(2, name);
            return stmt.executeUpdate();
        }
        finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        
    }

    // name をキーにして memoテーブルからデータを読み込む
    public PmemoEntity selectOne (String name, String tableName) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {
            String query = "select * from " + tableName + " where name=?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            result = stmt.executeQuery();

            PmemoEntity pmemo = new PmemoEntity();

            result.next();
            pmemo.setName(name);
            pmemo.setId(result.getString("id"));
            pmemo.setEmail(result.getString("email"));
            pmemo.setPassword(result.getString("password"));
            pmemo.setOther(result.getString("other"));
            Date date = result.getTimestamp("created_at");
            String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            pmemo.setCreated_at(dateString);
            return pmemo;
        }
        finally {
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    // name の一覧表示
    public ArrayList<String> nameList (String tableName) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet result = null;
        ArrayList<String> name = new ArrayList<>();

        try {
            String query = "select name from " + tableName;
            stmt = conn.prepareStatement(query);
            result = stmt.executeQuery();

            while (result.next()) {
                name.add(result.getString("name"));
            }
            return name;
        }
        finally {
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    // 削除処理
    public int deleteData (String name, String tableName) throws SQLException {
        PreparedStatement stmt = null;
        int result = 0;

        try {
            String query = "delete from " + tableName + " where name = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            result = stmt.executeUpdate();
            return result;
        }
        finally {
            if (stmt != null) { stmt.close(); }
        }
    }
}

/*
MySQL timestamp to Java date conversion
https://stackoverflow.com/questions/3473421/mysql-timestamp-to-java-date-conversion

 */
