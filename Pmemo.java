// Pmemo.java
package com.billies_works;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * Pmemo ���C��
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
                    // pmemo �Ƀf�[�^���Z�b�g����
                    setPmemo();
                    if (dao.insertData(pmemo, TABLENAME) > 0) {
                        System.out.println("�f�[�^�̒ǉ����ł��܂����B");
                    } else {
                        System.out.println("�f�[�^�̒ǉ��Ɏ��s���܂����B");
                    }
                    break;
                case 2:
                    editData();
                    break;
                case 3:
                    // �f�[�^�̌���
                    String thisData = selectName();
                    if (!("0".equals(thisData))) {
                        printOneData(thisData);
                    }
                    break;
                case 4:
                    // �f�[�^�̍폜
                    deleteData();
                    break;
                case 5:
                    // �f�[�^�̈ꗗ
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
     * ��ʃN���A
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
        System.out.println(">>>>> �p�X���[�h�Ǘ� Java�� <<<<<");
        System.out.println("������I��ł�������");
        System.out.println("----------------------");
        System.out.println("1) �f�[�^�̓���");
        System.out.println("2) �f�[�^�̏C��");
        System.out.println("3) �f�[�^�̌���");
        System.out.println("4) �f�[�^�̍폜");
        System.out.println("5) �f�[�^�̈ꗗ");
        System.out.println("6) �I��");
        System.out.println("----------------------");
        System.out.println("Copyright 2019 Seiichi Nukayama");
        System.out.println("http://www.billies-works.com/");
        int no = 0;
        do {
            try {
                no = Integer.parseInt(gi.get("\n�ԍ��H> "));
            } catch (NumberFormatException ne) {
                System.out.println("�����𔼊p�œ��͂��Ă��������B");
            } finally {
            }
        } while (no < 1 || no > 6);
        return no;
    }

    /**
     *
     */
    static void waitEnter() {
        System.out.println("Enter�L�[�������Ă�������...");
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
     * �f�[�^����͂���
     *    static�ϐ��� pmemo �Ƀf�[�^����́B
     */
    static void setPmemo() {
        System.out.println("----- �f�[�^�̓��� -----");
        System.out.println("*��͕K�{���ڂł��B");
        String name = null;
        do {
            name = gi.get("*name�i�o�^���j> ");
            if (name != null) {pmemo.setName(name); }
        } while (name == null);
        String id = gi.get(" id�i��������Ȃ�j> ");
        if (id != null) { pmemo.setId(id); } else { pmemo.setId("-");}
        String email = null;
        do {
            email = gi.get("*Email�i�o�^���[���A�h���X�j> ");
            if (email != null) { pmemo.setEmail(email); }
        } while (email == null);
        String password = null;
        do {
            password = gi.get("*password�i�o�^�p�X���[�h�j> ");
            if (password != null) { pmemo.setPassword(password); }
        } while (password == null);
        String other = gi.get("other�i�����j> ");
        if (other != null) { pmemo.setOther(other); } else { pmemo.setOther("-");}
    }

    /**
     *
     */
    static void editData() {
        try {
            String thisName = printOneData(selectName());
            int editNo = Integer.parseInt(gi.get("�C�����������ڔԍ��i0:����j > "));
            if (editNo != 0) {
                String newData = gi.get("�V�����f�[�^> ");
                if (dao.updateData(thisName, editNo, newData, TABLENAME) > 0) {
                    System.out.println("�X�V���܂����B");
                } else {
                    System.out.println("�X�V�ł��܂���ł����B");
                }
            } else {
                System.out.println("�������܂����B");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    /**
     * �f�[�^�� name �őI������
     *     0 ��I�������Ƃ��́A�����𒆎~���邱�Ƃɂ���B
     */
    static String selectName() {
        System.out.println("�I������f�[�^�� name �Ŏw�肵�Ă��������B�ilist: �ꗗ  0:���~�j");
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
     * ���[�U�ɂЂƂ̃f�[�^��I�������āA�����\������
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
     * �폜����
     */
    static void deleteData() {
        try {
            System.out.println("\n===============|| �폜���� ||==============");
            String name = selectName();
            if (!("0".equals(name))) {  
                System.out.println("\n�폜�Ώۂ͂��̃f�[�^�ł��B");
                printOneData(name);
                String yesno = gi.get("�폜���Ă���낵���ł����H (y/n) > ");
                if ("y".equals(yesno.toLowerCase())) {
                    int ok = dao.deleteData(name, TABLENAME);
                    System.out.println(ok + "���폜���܂����B");
                } else {
                    System.out.println("�������Ƃ��߂܂����B");
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    /**
     * �ꗗ�\��
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
