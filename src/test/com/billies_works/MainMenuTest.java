
package com.billies_works;

import com.billies_works.MainMenu;

public class MainMenuTest {
    public static void main( String[] args ) {
        MainMenu mainMenu = new MainMenu();
        int menuNum = mainMenu.get();
        System.out.println("選択した番号: " + menuNum);
    }
}

// 修正時刻: Sun Nov 15 08:41:53 2020
