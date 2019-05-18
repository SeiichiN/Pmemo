// ZenHanFormat.java
package com.billies_works;

import java.nio.charset.*;

public class ZenHanFormat {

    public String zhFormat (String target, int length) {

        // getByteLength -- utf-8�̏ꍇ�A�ꕶ��3�o�C�g�ƂȂ�B
        // byteDiff -- �S�p������
        int byteDiff = (getByteLength(target, Charset.forName("UTF-8")) - target.length()) / 2;

        // �S�p�ȊO�̕�����
        int hankakuNum = target.length() - byteDiff;
        // System.out.println("hankakuNum=" + hankakuNum + " byteDiff= " + byteDiff);

        // �S�p�����ɋ������X�y�[�X
        int ZenSpace = 0;
        if (byteDiff > 0) {
            ZenSpace = (length - hankakuNum) / 2;
        }
        // length ��ݒ肵�Ȃ���
        int newLength = hankakuNum + ZenSpace;
        if (byteDiff == 0 && newLength != length) { newLength = length; }
        if ((hankakuNum == 0) && (byteDiff * 2 < length)) {
             newLength = length / 2;
             target = target + "�@�@�@�@�@�@�@�@�@�@";
        }
        // target������ newLength �����傫���ꍇ�A�؂�̂Ă�
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
        String title = "�͂Ă�";
        // String title = "Okinawa Times";
        ZenHanFormat zhf = new ZenHanFormat();
        String text = zhf.zhFormat (title, 10);
        System.out.println (text + " | ");
    }
}

/**
 * [Java]���p�E�S�p�����ł������ʒu�����킹��
 *   https://qiita.com/Lilly008000/items/00876d8c61ce36bd5fba
 */
