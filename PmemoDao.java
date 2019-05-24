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
        int result = 0;

        try {
            String query = "insert into " + tableName
                + " (name, id, email, password, other) values (?,?,?,?,?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, pmemo.getName());
            stmt.setString(2, pmemo.getId());
            stmt.setString(3, pmemo.getEmail());
            stmt.setString(4, pmemo.getPassword());
            stmt.setString(5, pmemo.getOther());
            result =  stmt.executeUpdate();
        }
        finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return result;
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
        int result = 0;

        try {
            String query = "update " + tableName
                + " set " + item[editNo] + " = ?, created_at = now() where name = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, newText);
            stmt.setString(2, name);
            result = stmt.executeUpdate();
        }
        finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return result;
        
    }

    // name をキーにして memoテーブルからデータを読み込む
    public PmemoEntity selectOne (String name, String tableName) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet result = null;
        PmemoEntity pmemo = new PmemoEntity();

        try {
            String query = "select * from " + tableName + " where name=?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            result = stmt.executeQuery();

            result.next();
            pmemo.setName(name);
            pmemo.setId(result.getString("id"));
            pmemo.setEmail(result.getString("email"));
            pmemo.setPassword(result.getString("password"));
            pmemo.setOther(result.getString("other"));
            Date date = result.getTimestamp("created_at");
            String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            pmemo.setCreated_at(dateString);
        }
        finally {
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return pmemo;
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
        }
        finally {
            if (stmt != null) { stmt.close(); }
        }
        return result;
    }

    // 一覧表示
    public ArrayList<PmemoEntity> listAll (String tableName) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet result = null;
        ArrayList<PmemoEntity> pmemoList = new ArrayList<PmemoEntity>();

        try {
            String query = "select * from " + tableName;
            stmt = conn.prepareStatement(query);
            result = stmt.executeQuery();

            while (result.next()) {
                PmemoEntity pmemo = new PmemoEntity();
                pmemo.setName(result.getString("name"));
                pmemo.setId(result.getString("id"));
                pmemo.setEmail(result.getString("email"));
                pmemo.setPassword(result.getString("password"));
                pmemo.setOther(result.getString("other"));
                Date date = result.getTimestamp("created_at");
                String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                pmemo.setCreated_at(dateString);
                pmemoList.add(pmemo);
            } 
        }
        finally {
            if (result != null) { result.close(); }
            if (stmt != null) { stmt.close(); }
        }
        return pmemoList;
    }
}

/*
MySQL timestamp to Java date conversion
https://stackoverflow.com/questions/3473421/mysql-timestamp-to-java-date-conversion

 */
