/**
 * Java��C,C++��system("cls");�Ɠ����̋@�\����������
 *   https://qiita.com/Bim/items/c0b5ab527d105bc63d6b
 *
 * Windows �̏ꍇ
 *   var cc = new ConsoleControl("cmd", "/c", "cls");
 * Linux �̏ꍇ
 *   var cc = new ConsoleControl("/bin/bash", "-c", "clear");
 */
package com.billies_works;

import java.io.IOException;

public class ConsoleControl {
    private ProcessBuilder pb;

    /**
     * ConsoleControl�N���X�̃R���X�g���N�^�ł��B
     * �w�肵���R�}���h�����s����V�����v���Z�X�����s��������\�z���܂��B
     * @param command ���s����R�}���h
     */
    public ConsoleControl(String... command) {
        pb = new ProcessBuilder(command);
    }

    /**
     * �R�}���h�v�����v�g�̉�ʂ��N���A���郁�\�b�h�B
     */
    public void cls() throws IOException, InterruptedException {
        pb.inheritIO().start().waitFor();
        /*
         * // ProcesserBuild�̃R���X�g���N�^�����Ŏw�肵���O���R�}���h��
         * // �R�}���h�v�����v�g�Ŏ��s�ł���悤�ɕϊ�
         * ProcessBuilder pbInheritIO = pb.inheritIO();
         * // �O���R�}���h�Ŏ��s
         * Process pro = pbInheritIO.start();
         * // ���̃X���b�h�œ����Ă���v���Z�X���I���܂őҋ@
         * pro.waitFor();
         */
    }
}
