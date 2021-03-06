// GetUserInput.java ユーザーからの入力を受け取る
package com.billies_works.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
* @param: prompt -- 画面に表示する文字列
*                   例） 入力>
*
* @raturn: String -- ユーザーが入力した文字列
*
* 使い方: String text = GetUserInput.get("あなたの年齢は？> ");
*/
public class GetUserInput {
    public static String get (String prompt) {
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
        return inputLine;
    }
}

// 修正時刻: Thu Nov 12 21:41:01 2020
