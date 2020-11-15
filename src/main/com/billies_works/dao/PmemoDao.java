// PmemoDao.java
package com.billies_works.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.billies_works.model.PmemoEntity;

public class PmemoDao {
    private final String DB_NAME;
    private final String DB_USER;
    private final String DB_PASS;
    private final String TABLE_NAME;

    private final String JDBC_URL;
    
    public PmemoDao () {
        this.DB_NAME = "p_sample";
        this.DB_USER = "sasuke";
        this.DB_PASS = "sasuke";
        this.TABLE_NAME = "memo";
        this.JDBC_URL = "jdbc:mysql://localhost:3306/p_sample?serverTimezone=JST";
    }

    public PmemoDao (String dbname, String dbuser,
                     String dbpass, String tablename) {
        this.DB_NAME = dbname;
        this.DB_USER = dbuser;
        this.DB_PASS = dbpass;
        this.TABLE_NAME = tablename;
        this.JDBC_URL = "jdbc:mysql://localhost:3306/" +
            dbname + "?serverTimezone=JST";
    }

    /**
     * 一覧表示
     * @return:
     *   List<PmemoEntity> pmemoList
     */
    public List<PmemoEntity> findAll () {
        List<PmemoEntity> pmemoList = new ArrayList<>();

        try (Connection conn =
             DriverManager.getConnection( JDBC_URL, DB_USER, DB_PASS )) {

            String sql = "select * from " + TABLE_NAME;
            PreparedStatement pStmt = conn.prepareStatement( sql );
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                int no = rs.getInt("no");
                String name = rs.getString("name");
                String id = rs.getString("id");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String other = rs.getString("other");
                Date created_at = rs.getTimestamp("created_at");

                PmemoEntity pmemo = new PmemoEntity( name, id, email,
                                                     password, other );
                String dateString =
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(created_at);
                pmemo.setNo( no );
                pmemo.setCreated_at(dateString);
                pmemoList.add(pmemo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("データベースに接続できません。");
            return null;
        }
        return pmemoList;
    }

    /**
     * 1件のデータを追加する
     */
    public boolean addPmemo( PmemoEntity pmemo ) {
        try ( Connection conn =
              DriverManager.getConnection( JDBC_URL, DB_USER, DB_PASS )) {

            String sql = "insert into " + TABLE_NAME +
                " ( name, id, email, password, other ) values (?, ?, ?, ?, ?)";
            PreparedStatement pStmt = conn.prepareStatement( sql );
            pStmt.setString( 1, pmemo.getName() );
            pStmt.setString( 2, pmemo.getId() );
            pStmt.setString( 3, pmemo.getEmail() );
            pStmt.setString( 4, pmemo.getPassword() );
            pStmt.setString( 5, pmemo.getOther() );

            int result = pStmt.executeUpdate();
            if (result != 1) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("データの追加失敗。");
            return false;
        }
        return true;
    }

    /**
     * 検索処理(name)
     *
     * 登録名(name) で検索する。
     * 部分一致で検索するから、複数の候補が返ってくる可能性がある。
     */
    public List<PmemoEntity> searchPmemoName( String name ) {
        List<PmemoEntity> pmemoList = new ArrayList<>();

        try ( Connection conn =
              DriverManager.getConnection( JDBC_URL, DB_USER, DB_PASS )) {

            String sql = "select * from " + TABLE_NAME +
                " where name like ?";
            PreparedStatement pStmt = conn.prepareStatement( sql );

            String sqlText = "%" + name + "%";
            pStmt.setString( 1, sqlText );
            
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                int no = rs.getInt("no");
                name = rs.getString("name");
                String id = rs.getString("id");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String other = rs.getString("other");
                Date created_at = rs.getTimestamp("created_at");

                PmemoEntity pmemo = new PmemoEntity( name, id, email,
                                                     password, other );
                String dateString =
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(created_at);
                pmemo.setNo( no );
                pmemo.setCreated_at(dateString);
                pmemoList.add(pmemo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("データベースに接続できません。");
            return null;
        }

        return pmemoList;
    }

    /**
     * 検索処理(no)
     *
     * no で検索する。
     */
    public PmemoEntity searchPmemoNo( int no ) {
        PmemoEntity pmemo = null;
        
        try ( Connection conn =
              DriverManager.getConnection( JDBC_URL, DB_USER, DB_PASS )) {

            String sql = "select * from " + TABLE_NAME +
                " where no = ?";
            PreparedStatement pStmt = conn.prepareStatement( sql );

            pStmt.setInt( 1, no );
            
            ResultSet rs = pStmt.executeQuery();

            if (rs.next()) {
                // no = rs.getInt(no);
                String name = rs.getString("name");
                String id = rs.getString("id");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String other = rs.getString("other");
                Date created_at = rs.getTimestamp("created_at");

                pmemo = new PmemoEntity( name, id, email,
                                                     password, other );
                String dateString =
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(created_at);
                pmemo.setNo( no );
                pmemo.setCreated_at(dateString);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("データベースに接続できません。");
            return null;
        }

        return pmemo;
    }

    /**
     * 1件のデータを削除する
     */
    public boolean deletePmemoNo( int no ) {
        try ( Connection conn =
              DriverManager.getConnection( JDBC_URL, DB_USER, DB_PASS )) {

            String sql = "delete from " + TABLE_NAME +
                " where no = ?";
            PreparedStatement pStmt = conn.prepareStatement( sql );
            pStmt.setInt( 1, no );

            int result = pStmt.executeUpdate();
            if (result != 1) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("データの削除失敗。");
            return false;
        }
        return true;
    }

    /**
     * データを更新する
     */
    public boolean updatePmemo( PmemoEntity pmemo ) {
        try ( Connection conn =
              DriverManager.getConnection( JDBC_URL, DB_USER, DB_PASS )) {

            String sql = "UPDATE " + TABLE_NAME + " SET name = ?, id = ?," +
                " email = ?, password = ?, other = ? " +
                " WHERE no = ?";
            PreparedStatement pStmt = conn.prepareStatement( sql );
            pStmt.setString( 1, pmemo.getName() );
            pStmt.setString( 2, pmemo.getId() );
            pStmt.setString( 3, pmemo.getEmail() );
            pStmt.setString( 4, pmemo.getPassword() );
            pStmt.setString( 5, pmemo.getOther() );
            pStmt.setInt( 6, pmemo.getNo() );

            int result = pStmt.executeUpdate();
            if (result != 1) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("データの更新失敗。");
            return false;
        }
        return true;
    }

}

// 修正時刻: Sun Nov 15 13:32:36 2020
