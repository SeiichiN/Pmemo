// ZenHanFormat.java
package com.billies_works.util;

import java.nio.charset.*;
import java.util.*;

public class ZenHanFormat {

    public String zhFormat (String target, int length) {

        // getByteLength -- utf-8の場合、一文字3バイトとなる。
        // byteDiff -- 全角文字数
        int byteDiff = (getByteLength(target, Charset.forName("UTF-8")) - target.length()) / 2;

        // 全角以外の文字数
        int hankakuNum = target.length() - byteDiff;
        // System.out.println("hankakuNum=" + hankakuNum + " byteDiff= " + byteDiff);

        // ZenSpace -- 全角文字に許されるスペース
        int ZenSpace = 0;
        // newLength -- 全角文字で考えた場合、lengthが10だとすると、5にならなければならない
        int newLength = length;
        if (byteDiff > 0) {
            ZenSpace = (length - hankakuNum) / 2;
            newLength = hankakuNum + ZenSpace;
        }
        
        // realMojiNUm -- 半角に換算した文字数（全角を2文字とカウントして）
        int realMojiNum = target.length() + byteDiff;
        String newTarget = target;
        // realMojiNum が length よりも少ない場合、半角スペースで埋める
        if (realMojiNum < length) {
            newTarget = target + repeat(" ", (length - realMojiNum));
        }
        // target文字列が newLength よりも大きい場合、切り捨てる
        if (target.length() > newLength) {
            newTarget = target.substring(0, newLength);
        }

        // System.out.println("length= " + length);
        // System.out.println("byteDiff= " + byteDiff);
        // System.out.println("hankakuNum= " + hankakuNum);
        // System.out.println("ZenSpace= " + ZenSpace);
        // System.out.println("realMojiNum= " + realMojiNum);
        // System.out.println("newLength= " + newLength);

        return newTarget;
    }

    private int getByteLength (String string, Charset charset) {
        return string.getBytes(charset).length;
    }

    private String repeat (String str, int n) {
        return String.join("", Collections.nCopies(n, str));
    }

    public static void main (String [] args) {
        String title = "ITソリューション";
        // String title = "Okinawa Times";
        ZenHanFormat zhf = new ZenHanFormat();
        String text = zhf.zhFormat (title, 20);
        System.out.println ("123456789|123456789|");
        System.out.println (text + "-|-");
    }
}

/**
 * [Java]半角・全角混合でも文字位置を合わせる
 *   https://qiita.com/Lilly008000/items/00876d8c61ce36bd5fba
 */
