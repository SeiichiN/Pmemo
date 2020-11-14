
package com.billies_works.util;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.HashSet;

/**
 * 整数を入力するユーティリティ
 *
 * コンストラクタ引数: prompt -- 入力プロンプト文字列
 *                              ex."番号 > "
 *
 * メソッド:
 *   get( HashSet<Integer> numSet ) --
 *     入力期待値を集合(HashSet)で指定できる
 *   get() --
 *     入力期待値はなし。どんな数字でもよい。
 */
public class ScanInputInt {
    String prompt = null;
    
    public ScanInputInt( String prompt ) {
        this.prompt = prompt;
    }

    /**
     * @param:
     *   numSet -- int の集合
     */
    public int get( HashSet<Integer> numSet) {
        Scanner scanner = null;
        int num = 99;
        boolean isNum = false;

        while (! isNum) {
            System.out.print( prompt );
            try {
                scanner = new Scanner( System.in );
                num = scanner.nextInt();
                if (numSet.contains( num ))
                    isNum = true;
                else
                    System.out.println("その番号は含まれません");
            } catch (InputMismatchException e) {
                System.out.println("数字を入力してください");
                scanner = null;
            }
        }
        return num;
    }

    public int get() {
        Scanner scanner = null;
        int num = 99;
        boolean isNum = false;

        while (! isNum) {
            System.out.print( prompt );
            try {
                scanner = new Scanner( System.in );
                num = scanner.nextInt();
                isNum = true;
            } catch (InputMismatchException e) {
                System.out.println("数字を入力してください");
                scanner = null;
            }
        }
        return num;
    }
}
    

// 修正時刻: Sat Nov 14 18:31:09 2020
