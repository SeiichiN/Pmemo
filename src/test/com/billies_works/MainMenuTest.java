
package com.billies_works;

import com.billies_works.MainMenu;

public class MainMenuTest {
    public static void main( String[] args ) {
        MainMenu mainMenu = new MainMenu();
        int menuNum = mainMenu.get();
        System.out.println("選択した番号:ddd> " + menuNum);
    }
}

// 修正時刻: Fri Nov 20 12:15:39 2020
