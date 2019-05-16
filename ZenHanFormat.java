// ZenHanFormat.java
package com.billies_works;

import java.nio.charset.*;

public class ZenHanFormat {

    public String zhFormat (String target, int length) {

        // getByteLength -- utf-8の場合、一文字3バイトとなる。
        int byteDiff = (getByteLength(target, Charset.forName("UTF-8")) - target.length()) / 2;
        int byteNum = getByteLength(target, Charset.forName("UTF-8"));
        // System.out.println("target.length= " + target.length());
        /*
        System.out.println("byteDiff= " + byteDiff);
        System.out.println("byteDiff/2= " +( byteDiff / 2));
        System.out.println("byteDiff%2= " + (byteDiff % 2));
        */

        String returnText = null;
        // int size = length - byteDiff;  // org
        if ((target.length() + byteDiff) >= length) {
            length = length - byteDiff;  // ((byteDiff / 2); // + (byteDiff % 2)); 
        //    System.out.println("%." + length + "s : " + target );
            returnText = String.format("%." + length + "s", target);
        } else {
            returnText = String.format("%-" + length + "s", target);
        }
        // sizeがマイナスのときって、あるんやろか？  added by Seiichi Nukayama
        // if (size < 0) { size = size * -1; }
        // if (size == 0) { size = 1; }
        // もし target文字列が size より大きければ、切り詰める  added by Seiichi Nukayama
        // if (size < target.length()) {
        //     target = target.substring(0, size);
        // }
        return returnText;
    }

    private int getByteLength (String string, Charset charset) {
        return string.getBytes(charset).length;
    }

    public static void main (String [] args) {
        String title = "123456中山寺";
        // String title = "Okinawa Times";
        ZenHanFormat zhf = new ZenHanFormat();
        String text = zhf.zhFormat (title, 10);
        System.out.println (text);
    }
}

/**
 * [Java]半角・全角混合でも文字位置を合わせる
 *   https://qiita.com/Lilly008000/items/00876d8c61ce36bd5fba
 */
