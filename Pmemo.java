// Pmemo.java
package com.billies_works;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * Pmemo メイン
 */
public class Pmemo {

    static PmemoEntity pmemo = new PmemoEntity();
    static PmemoDao dao = null;
    static String TABLENAME = null;
    static GetUserInput gi = new GetUserInput();

    public static void main (String [] args) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        Map<String, String> confList = new HashMap<String, String>();

        confList = new GetConf("pmemo.conf").load();

        final String USERNAME = confList.get("username");
        final String PASSWORD = confList.get("password");
        final String CONNECTURL = confList.get("url");
        TABLENAME = confList.get("tablename");

        try {
            conn = DriverManager.getConnection(CONNECTURL, USERNAME, PASSWORD);
            dao = new PmemoDao(conn);

            while(true) {
                int menuNo = menu();
                switch (menuNo) {
                case 1:
                    // pmemo にデータをセットする
                    setPmemo();
                    if (dao.insertData(pmemo, TABLENAME) > 0) {
                        System.out.println("データの追加ができました。");
                    } else {
                        System.out.println("データの追加に失敗しました。");
                    }
                    break;
                case 2:
                    editData();
                    break;
                case 3:
                    // データの検索
                    String thisData = selectName();
                    if (!("0".equals(thisData))) {
                        printOneData(thisData);
                    }
                    break;
                case 4:
                    // データの削除
                    deleteData();
                    break;
                case 5:
                    // データの一覧
                    listAll();
                    break;
                case 6:
                    closeConnect(conn);
                    System.exit(0);
                    break;
                default:
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            closeConnect(conn);
        }
    }

    static void closeConnect(Connection conn){
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException se) {
            System.err.println("Error CloseSqlFunc...");
            se.printStackTrace();
        }
    }

    /**
     * 画面クリア
     */
    static void clearConsole() {
        try {
            // ConsoleControl cc = new ConsoleControl("/bin/bash", "-c", "clear");  // for Linux
            ConsoleControl cc = new ConsoleControl("cmd", "/c", "cls");   // for Win
            cc.cls();
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (InterruptedException re) {
            re.printStackTrace();
        }
    }
    
    static int menu () {
        clearConsole();
        System.out.println(">>>>> パスワード管理 Java版 <<<<<");
        System.out.println("処理を選んでください");
        System.out.println("----------------------");
        System.out.println("1) データの入力");
        System.out.println("2) データの修正");
        System.out.println("3) データの検索");
        System.out.println("4) データの削除");
        System.out.println("5) データの一覧");
        System.out.println("6) 終了");
        System.out.println("----------------------");
        System.out.println("Copyright 2019 Seiichi Nukayama");
        System.out.println("http://www.billies-works.com/");
        int no = 0;
        do {
            try {
                no = Integer.parseInt(gi.get("\n番号？> "));
            } catch (NumberFormatException ne) {
                System.out.println("数字を半角で入力してください。");
            } finally {
            }
        } while (no < 1 || no > 6);
        return no;
    }

    /**
     *
     */
    static void waitEnter() {
        System.out.println("Enterキーを押してください...");
        try {
            int c = 0;
            do {
                c = 0;
                c = System.in.read();
            } while (c != 10);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * データを入力する
     *    static変数の pmemo にデータを入力。
     */
    static void setPmemo() {
        System.out.println("----- データの入力 -----");
        System.out.println("*印は必須項目です。");
        String name = null;
        do {
            name = gi.get("*name（登録名）> ");
            if (name != null) {pmemo.setName(name); }
        } while (name == null);
        String id = gi.get(" id（もしあるなら）> ");
        if (id != null) { pmemo.setId(id); } else { pmemo.setId("-");}
        String email = null;
        do {
            email = gi.get("*Email（登録メールアドレス）> ");
            if (email != null) { pmemo.setEmail(email); }
        } while (email == null);
        String password = null;
        do {
            password = gi.get("*password（登録パスワード）> ");
            if (password != null) { pmemo.setPassword(password); }
        } while (password == null);
        String other = gi.get("other（メモ）> ");
        if (other != null) { pmemo.setOther(other); } else { pmemo.setOther("-");}
    }

    /**
     *
     */
    static void editData() {
        try {
            String thisName = printOneData(selectName());
            int editNo = Integer.parseInt(gi.get("修正したい項目番号（0:取消） > "));
            if (editNo != 0) {
                String newData = gi.get("新しいデータ> ");
                if (dao.updateData(thisName, editNo, newData, TABLENAME) > 0) {
                    System.out.println("更新しました。");
                } else {
                    System.out.println("更新できませんでした。");
                }
            } else {
                System.out.println("取り消しました。");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    /**
     * データを name で選択する
     *     0 を選択したときは、処理を中止することにする。
     */
    static String selectName() {
        System.out.println("選択するデータを name で指定してください。（list: 一覧  0:中止）");
        String name = null;
        do {
            name = gi.get("name (list : 0)> ");
            if ("list".equals(name)) {
                ArrayList<String> nameList = new ArrayList<>();
                try {
                    nameList = dao.nameList(TABLENAME);
                } catch (SQLException se) {
                    se.printStackTrace();
                }

                int i = 0;
                System.out.println("------------< LIST >------------");
                for (String nameOne : nameList) {
                    System.out.printf("%-20s", nameOne);
                    i++;
                    if (i % 3 == 0) { System.out.println(); }
                }
                System.out.println("\n--------------------------------");
            }
        } while ("list".equals(name));
        return name;
    }

    /**
     * ユーザにひとつのデータを選択させて、それを表示する
     */
    static String printOneData(String hereIt) throws SQLException {
        // String hereIt = selectName();
        pmemo = dao.selectOne(hereIt, TABLENAME);
        if (pmemo != null) {
            System.out.println("===================================");
            System.out.println(pmemo.toString());
            System.out.println("===================================");
            waitEnter();
        }
        return pmemo.getName();
    }

    /**
     * 削除処理
     */
    static void deleteData() {
        try {
            System.out.println("\n===============|| 削除処理 ||==============");
            String name = selectName();
            if (!("0".equals(name))) {  
                System.out.println("\n削除対象はこのデータです。");
                printOneData(name);
                String yesno = gi.get("削除してもよろしいですか？ (y/n) > ");
                if ("y".equals(yesno.toLowerCase())) {
                    int ok = dao.deleteData(name, TABLENAME);
                    System.out.println(ok + "件削除しました。");
                } else {
                    System.out.println("処理をとりやめました。");
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    /**
     * 一覧表示
     */
    static void listAll() {
        ArrayList<PmemoEntity> pmemoList = new ArrayList<PmemoEntity>();
        ZenHanFormat zhf = new ZenHanFormat();

        String headline = "-- name -- + - id - + ------ Email  ------ + --- password --- + ----- other ------ + ------ date -------";
        System.out.println(headline);
        try {
            pmemoList = dao.listAll(TABLENAME);
            int i = 0;
            for (PmemoEntity item : pmemoList) {
                i++;
                String itemName = zhf.zhFormat((item.getName()), 10);
                String itemId = zhf.zhFormat((item.getId()), 6);
                String itemEmail = zhf.zhFormat((item.getEmail()), 20);
                String itemPassword = zhf.zhFormat((item.getPassword()), 16);
                String itemOther = zhf.zhFormat((item.getOther()), 18);
                System.out.print(itemName + " | ");
                System.out.print(itemId + " | ");
                System.out.print(itemEmail + " | ");
                System.out.print(itemPassword + " | ");
                System.out.print(itemOther + " | ");
                System.out.printf("%14s", item.getCreated_at());
                
                System.out.println();
                if (i % 30 == 0) {
                    waitEnter();
                    System.out.println(headline);
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        waitEnter();
    }
}
