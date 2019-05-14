// ZenHanFormat.java
package com.billies_works;

import java.nio.charset.*;

public class ZenHanFormat {

    public String zhFormat (String target, int length) {
        int byteDiff = (getByteLength(target, Charset.forName("UTF-8")) - target.length()) / 2;
        System.out.println("byteDiff= " + byteDiff + " target= " + target + "length= " + length);
        int size = length - byteDiff;
        if (size < 0) { size = size * -1; }
        return String.format("%-" + size + "s", target);
    }

    private int getByteLength (String string, Charset charset) {
        return string.getBytes(charset).length;
    }
}

/**
 * [Java]半角・全角混合でも文字位置を合わせる
 *   https://qiita.com/Lilly008000/items/00876d8c61ce36bd5fba
 */
