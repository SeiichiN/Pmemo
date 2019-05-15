// ZenHanFormat.java
package com.billies_works;

import java.nio.charset.*;

public class ZenHanFormat {

    public String zhFormat (String target, int length) {
        int byteDiff = (getByteLength(target, Charset.forName("UTF-8")) - target.length()) / 2;
        int size = length - byteDiff;
        // size���}�C�i�X�̂Ƃ����āA������납�H  added by Seiichi Nukayama
        if (size < 0) { size = size * -1; }
        // ���� target������ size ���傫����΁A�؂�l�߂�  added by Seiichi Nukayama
        if (size < target.length()) {
            target = target.substring(0, size);
        }
        return String.format("%-" + size + "s", target);
    }

    private int getByteLength (String string, Charset charset) {
        return string.getBytes(charset).length;
    }
}

/**
 * [Java]���p�E�S�p�����ł������ʒu�����킹��
 *   https://qiita.com/Lilly008000/items/00876d8c61ce36bd5fba
 */
