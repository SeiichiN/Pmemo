
package com.billies_works;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.HashSet;

import com.billies_works.util.ScanInputInt;

/**
 * サブメニュー
 */
public class Submenu {
    public Submenu() {}

    public int getNum() {
        Scanner scanner = null;
        int menuNum = 99;
        boolean isNum = false;
        HashSet<Integer> numSet = new HashSet<> ();
        numSet.add(1);
        numSet.add(3);
        numSet.add(8);
        numSet.add(0);

        System.out.println("----------- Submenu -----------");
        System.out.println("1:訂正  3:削除  8:戻る  0:終了");

        ScanInputInt scanInputInt = new ScanInputInt("番号 > ");
        menuNum = scanInputInt.get( numSet );
        
        return menuNum;
    }
}

// 修正時刻: Sat Nov 14 17:05:04 2020
