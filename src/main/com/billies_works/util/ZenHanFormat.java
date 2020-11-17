// ZenHanFormat.java
package com.billies_works.util;

import java.nio.charset.*;
import java.util.*;

public class ZenHanFormat {
    private String encode = null;
    private Mode mode;

    public ZenHanFormat () {
        this.encode = "UTF-8";
        this.mode = Mode.UTF8;
    }
    public ZenHanFormat ( String mode ){
        if (mode.toLowerCase().equals("win")) {
            this.mode = Mode.WIN;
            this.encode = "windows-31j";
        }
    }

    /**
     * 全角文字をスペースの中でうまく表示させる
     *
     * @param:
     *   String target -- 表示させたい文字列
     *   int length -- 表示したいスペース(半角桁数)
     */
    public String zhFormat (String target, int length) {

        if (target == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                sb.append(" ");
            }
            return sb.toString();
        }

        // getByteLength -- utf-8の場合、1文字3バイト * 文字数
        //                  Shift_JISの場合、1文字2バイト * 文字数
        // target.length() -- 全角半角関係なしの文字列。例えば、「寿限無」なら3、「jugemu」なら6
        // byteDiff -- 全角文字数
        int byteDiff = 0;
        if (mode == Mode.WIN) {
            byteDiff = getByteLength(target, Charset.forName( encode )) - target.length();
        } else if (mode == Mode.UTF8){
            byteDiff = (getByteLength(target, Charset.forName( encode )) - target.length()) / 2;
        }

        // System.out.println("target: " + target);
        // System.out.println("target.length(): " + target.length());
        // System.out.println("全角文字数: " + getByteLength( target, Charset.forName("windows-31j")));
        // System.out.println("byteDiff: " + byteDiff);
        // System.out.println();

        // 全角以外の文字数
        int hankakuNum = target.length() - byteDiff;
        // System.out.println("hankakuNum=" + hankakuNum + " byteDiff= " + byteDiff);

        // ZenSpace -- 全角文字に許されるスペース
        int ZenSpace = 0;
        // newLength -- 全角文字で考えた場合、length(与えられたスペース)が10だとすると、
        // 5にならなければならない
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

    enum Mode {
        UTF8,
        WIN;
    }
}

/**
 * [Java]半角・全角混合でも文字位置を合わせる
 *   https://qiita.com/Lilly008000/items/00876d8c61ce36bd5fba
 */

// 修正時刻: Tue Nov 17 12:25:56 2020
