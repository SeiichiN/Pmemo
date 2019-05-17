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

    // memo�e�[�u����1�����̃f�[�^��}������
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
     * �f�[�^�̏C��
     * @param:
     *   String name -- �C������f�[�^�̃L�[
     *   int editNo  -- �C������f�[�^�̔ԍ� 1..5
     *   String newText -- �V�����f�[�^
     *   String tableName -- �e�[�u����
     * @return:
     *   int stmt.executeUpdate -- SQL�����s�����s���i0:���s, 1:�P�s�C���j
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

    // name ���L�[�ɂ��� memo�e�[�u������f�[�^��ǂݍ���
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

    // name �̈ꗗ�\��
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

    // �폜����
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

    // �ꗗ�\��
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
