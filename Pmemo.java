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
                    printOneData(selectName());
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
            ConsoleControl cc = new ConsoleControl("/bin/bash", "-c", "clear");
            cc.cls();
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (InterruptedException re) {
            re.printStackTrace();
        }
    }
    
    static int menu () {
        clearConsole();
        System.out.println("\n処理を選んでください");
        System.out.println("----------------------");
        System.out.println("1) データの入力");
        System.out.println("2) データの修正");
        System.out.println("3) データの検索");
        System.out.println("4) データの削除");
        System.out.println("5) データの一覧");
        System.out.println("6) 終了");
        System.out.println("----------------------");
        int no = 0;
        do {
            try {
                no = Integer.parseInt(getUserInput("番号？> "));
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
     * ユーザーに文字列の入力をしてもらい、それを返す
     * 
     * @param: prompt -- ユーザーに表示する文字列
     *                   （例）"入力 > "
     *
     * @raturn: String -- 半角英数字を想定。日本語の場合はどうなるか？
     */
    static String getUserInput (String prompt) {
        String inputLine = null;
        System.out.print (prompt + " ");
        try {
            BufferedReader is = new BufferedReader(
                new InputStreamReader (System.in));
            inputLine = is.readLine();
            if (inputLine.length() == 0) return null;
        } catch (IOException e) {
            System.out.println ("IOException: " + e);
        }
        return inputLine.toLowerCase ();
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
            name = getUserInput("*name（登録名）> ");
            if (name != null) {pmemo.setName(name); }
        } while (name == null);
        String id = getUserInput(" id（もしあるなら）> ");
        if (id != null) { pmemo.setId(id); } else { pmemo.setId("-");}
        String email = null;
        do {
            email = getUserInput("*Email（登録メールアドレス）> ");
            if (email != null) { pmemo.setEmail(email); }
        } while (email == null);
        String password = null;
        do {
            password = getUserInput("*password（登録パスワード）> ");
            if (password != null) { pmemo.setPassword(password); }
        } while (password == null);
        String other = getUserInput("other（メモ）> ");
        if (other != null) { pmemo.setOther(other); } else { pmemo.setOther("-");}
    }

    /**
     *
     */
    static void editData() {
        try {
            String thisName = printOneData(selectName());
            int editNo = Integer.parseInt(getUserInput("修正したい項目番号（0:取消） > "));
            if (editNo != 0) {
                String newData = getUserInput("新しいデータ> ");
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
     */
    static String selectName() {
        System.out.println("選択するデータを name で指定してください。（list: 一覧）");
        String name = null;
        do {
            name = getUserInput("name > ");
            ArrayList<String> nameList = new ArrayList<>();
            if ("list".equals(name)) {
                try {
                    nameList = dao.nameList(TABLENAME);
                } catch (SQLException se) {
                    se.printStackTrace();
                }

                System.out.println("------------< LIST >------------");
                for (String nameOne : nameList) {
                    System.out.println(nameOne);
                }
                System.out.println("--------------------------------");
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
/*
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
*/
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
            System.out.println("\n削除対象はこのデータです。");
            printOneData(name);
            String yesno = getUserInput("削除してもよろしいですか？ (y/n) > ");
            if ("y".equals(yesno.toLowerCase())) {
                int ok = dao.deleteData(name, TABLENAME);
                System.out.println(ok + "件削除しました。");
            } else {
                System.out.println("処理をとりやめました。");
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

        System.out.println("-- name -- + - id - + ------ Email  ------ + --- password --- + ---------- other ---------- + ------ date -------");
        try {
            pmemoList = dao.listAll(TABLENAME);
            for (PmemoEntity item : pmemoList) {
                System.out.printf("%10s | ", item.getName());
                System.out.printf("%6s | ", item.getId());
                System.out.printf("%20s | ", item.getEmail());
                System.out.printf("%16s | ", item.getPassword());
                System.out.printf("%18s | ", item.getOther());
                System.out.printf("%14s", item.getCreated_at());
                System.out.println();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        waitEnter();
    }
}
