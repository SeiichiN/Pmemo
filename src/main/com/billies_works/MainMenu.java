
package com.billies_works;

import java.util.Scanner;
import java.util.HashSet;

import com.billies_works.util.ClearConsole;
import com.billies_works.util.ScanInputInt;

/**
 * メインメニューを表示。
 * 数字を入力して処理を選択する。
 */
public class MainMenu {
    public MainMenu() {}

    /**
     * メインメニューを表示
     * @return: int -- 処理の番号
     */
    public int get() {
        disp();
        return getMenuNum();
    }

    private int getMenuNum() {
        HashSet<Integer> menuNums = new HashSet<>();
        menuNums.add(1);
        menuNums.add(2);
        menuNums.add(3);
        menuNums.add(0);
        ScanInputInt scanInputInt = new ScanInputInt("番号 > ");
        return scanInputInt.get( menuNums );
    }
    
    private void disp() {
        ClearConsole clearConsole = new ClearConsole();
        clearConsole.clear();
        System.out.println("------------ Pmemo ------------");
        System.out.println("| 1) 登録名による検索         |");
        System.out.println("| 2) 一覧表示                 |");
        System.out.println("| 3) 新規登録                 |");
        System.out.println("| 0) 終了                     |");
        System.out.println("-------------------------------");
    }
}

// 修正時刻: Sun Nov 15 09:27:50 2020
