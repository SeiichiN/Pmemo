// ZenHanFormat.java
package com.billies_works;

import java.nio.charset.*;

public class ZenHanFormat {

    public String zhFormat (String target, int length) {

        // getByteLength -- utf-8の場合、一文字3バイトとなる。
        // byteDiff -- 全角文字数
        int byteDiff = (getByteLength(target, Charset.forName("UTF-8")) - target.length()) / 2;

        // 全角以外の文字数
        int hankakuNum = target.length() - byteDiff;
        // System.out.println("hankakuNum=" + hankakuNum + " byteDiff= " + byteDiff);

        // 全角文字に許されるスペース
        int ZenSpace = 0;
        if (byteDiff > 0) {
            ZenSpace = (length - hankakuNum) / 2;
        }
        // length を設定しなおす
        int newLength = hankakuNum + ZenSpace;
        if (byteDiff == 0 && newLength != length) { newLength = length; }
        if ((hankakuNum == 0) && (byteDiff * 2 < length)) {
             newLength = length / 2;
             target = target + "　　　　　　　　　　";
        }
        // target文字列が newLength よりも大きい場合、切り捨てる
        if (target.length() > newLength) {
            target = target.substring(0, newLength);
        }
        
        // System.out.println("newLength= " + newLength);
        // System.out.println("target= " + target);

        String returnText = String.format("%-" + newLength + "s", target);

        return returnText;
    }

    private int getByteLength (String string, Charset charset) {
        return string.getBytes(charset).length;
    }

    public static void main (String [] args) {
        String title = "はてな";
        // String title = "Okinawa Times";
        ZenHanFormat zhf = new ZenHanFormat();
        String text = zhf.zhFormat (title, 10);
        System.out.println (text + " | ");
    }
}

/**
 * [Java]半角・全角混合でも文字位置を合わせる
 *   https://qiita.com/Lilly008000/items/00876d8c61ce36bd5fba
 */
