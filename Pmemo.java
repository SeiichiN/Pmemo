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
                    String thisName = printOneData(selectName());
                    int editNo = Integer.parseInt(getUserInput("�C�����������ڔԍ� > "));
                    String newData = getUserInput("�V�����f�[�^> ");
                    if (dao.updateData(thisName, editNo, newData, TABLENAME) > 0) {
                        System.out.println("�X�V���܂����B");
                    } else {
                        System.out.println("�X�V�ł��܂���ł����B");
                    }
                    break;
                case 3:
                    // �f�[�^�̌���
                    printOneData(selectName());
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

    static int menu () {
        System.out.println("\n������I��ł�������");
        System.out.println("----------------------");
        System.out.println("1) �f�[�^�̓���");
        System.out.println("2) �f�[�^�̏C��");
        System.out.println("3) �f�[�^�̌���");
        System.out.println("4) �f�[�^�̍폜");
        System.out.println("5) �f�[�^�̈ꗗ");
        System.out.println("6) �I��");
        System.out.println("----------------------");
        int no = 0;
        do {
            try {
                no = Integer.parseInt(getUserInput("�ԍ��H> "));
            } catch (NumberFormatException ne) {
                System.out.println("�����𔼊p�œ��͂��Ă��������B");
            } finally {
            }
        } while (no < 1 || no > 6);
        return no;
    }

        /**
     * ���[�U�[�ɕ�����̓��͂����Ă��炢�A�����Ԃ�
     * 
     * @param: prompt -- ���[�U�[�ɕ\�����镶����
     *                   �i��j"���� > "
     *
     * @raturn: String -- ���p�p������z��B���{��̏ꍇ�͂ǂ��Ȃ邩�H
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
     * �f�[�^����͂���
     *    static�ϐ��� pmemo �Ƀf�[�^����́B
     */
    static void setPmemo() {
        System.out.println("----- �f�[�^�̓��� -----");
        System.out.println("*��͕K�{���ڂł��B");
        String name = null;
        do {
            name = getUserInput("*name�i�o�^���j> ");
            if (name != null) {pmemo.setName(name); }
        } while (name == null);
        String id = getUserInput(" id�i��������Ȃ�j> ");
        if (id != null) { pmemo.setId(id); } else { pmemo.setId("-");}
        String email = null;
        do {
            email = getUserInput("*Email�i�o�^���[���A�h���X�j> ");
            if (email != null) { pmemo.setEmail(email); }
        } while (email == null);
        String password = null;
        do {
            password = getUserInput("*password�i�o�^�p�X���[�h�j> ");
            if (password != null) { pmemo.setPassword(password); }
        } while (password == null);
        String other = getUserInput("other�i�����j> ");
        if (other != null) { pmemo.setOther(other); } else { pmemo.setOther("-");}
    }

    /**
     * �f�[�^�� name �őI������
     */
    static String selectName() {
        System.out.println("�I������f�[�^�� name �Ŏw�肵�Ă��������B�ihelp: �ꗗ�j");
        String name = null;
        do {
            name = getUserInput("name > ");
            ArrayList<String> nameList = new ArrayList<>();
            if ("help".equals(name)) {
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
        } while ("help".equals(name));
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
        return pmemo.getName();
    }

    /**
     * �폜����
     */
    static void deleteData() {
        try {
            System.out.println("\n===============|| �폜���� ||==============");
            String name = selectName();
            System.out.println("\n�폜�Ώۂ͂��̃f�[�^�ł��B");
            printOneData(name);
            String yesno = getUserInput("�폜���Ă���낵���ł����H (y/n) > ");
            if ("y".equals(yesno.toLowerCase())) {
                int ok = dao.deleteData(name, TABLENAME);
                System.out.println(ok + "���폜���܂����B");
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
    }
}
