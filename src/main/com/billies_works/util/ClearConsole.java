package com.billies_works.util;

import java.io.IOException;

public class ClearConsole {

    public ClearConsole() {}
    
    public void clear() {
        try {
            ConsoleControl cc = null;
            PlatformUtils pu = new PlatformUtils();
            if (pu.isLinux()) {
                cc = new ConsoleControl("/bin/bash", "-c", "clear");  // for Linux
            } else if (pu.isWindows()) {
                cc = new ConsoleControl("cmd", "/c", "cls");   // for Win
            }
            cc.cls();
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (InterruptedException re) {
            re.printStackTrace();
        }
    }

    /**
     * JavaでC,C++のsystem("cls");と同等の機能を実装する
     *   https://qiita.com/Bim/items/c0b5ab527d105bc63d6b
     *
     * Windows の場合
     *   var cc = new ConsoleControl("cmd", "/c", "cls");
     * Linux の場合
     *   var cc = new ConsoleControl("/bin/bash", "-c", "clear");
     *
     * cc.cls();
     */
    class ConsoleControl {
        private ProcessBuilder pb;

        /**
         * ConsoleControlクラスのコンストラクタです。
         * 指定したコマンドを実行する新しいプロセスを実行する環境を構築します。
         * @param command 実行するコマンド
         */
        public ConsoleControl(String... command) {
            pb = new ProcessBuilder(command);
        }

        /**
         * コマンドプロンプトの画面をクリアするメソッド。
         */
        public void cls() throws IOException, InterruptedException {
            pb.inheritIO().start().waitFor();
            /*
             * // ProcesserBuildのコンストラクタ引数で指定した外部コマンドを
             * // コマンドプロンプトで実行できるように変換
             * ProcessBuilder pbInheritIO = pb.inheritIO();
             * // 外部コマンドで実行
             * Process pro = pbInheritIO.start();
             * // 他のスレッドで動いているプロセスが終わるまで待機
             * pro.waitFor();
             */
        }
    }
}




// 修正時刻: Sun Nov 15 11:13:59 2020
