
package com.billies_works;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * サブメニュー
 */
public class Submenu {
    public Submenu() {}

    public int getNum() {
        Scanner scanner = null;
        int menuNum = 99;
        boolean isNum = false;

        System.out.println("----------- Submenu -----------");
        System.out.println("1:訂正  3:削除  8:戻る  0:終了");
        while (! isNum) {
            System.out.print  ("番号 > ");
            try {
                scanner = new Scanner(System.in);
                menuNum = scanner.nextInt();
                if (menuNum == 1 || menuNum == 3 ||
                    menuNum == 8 || menuNum == 0 ) {
                    isNum = true;
                } else {
                    System.out.println("番号は 1, 3, 8, 0 です。");
                }
            } catch (InputMismatchException e) {
                System.out.println("数字を入力してください。");
                scanner = null;
            } 
        }
        return menuNum;
    }
}

// 修正時刻: Sat Nov 14 10:32:36 2020
