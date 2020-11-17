
package com.billies_works;

import java.util.ArrayList;

import com.billies_works.MainMenu;
import com.billies_works.DispPmemoEntity;
import com.billies_works.DisplayPmemoList;
import com.billies_works.util.ScanInputString;
import com.billies_works.CreatePmemoEntity;
import com.billies_works.model.PmemoEntity;
import com.billies_works.model.AddPmemoLogic;

public class Main {
    public static void main( String[] args) {
        while(true) {
            int menuNum = selectMainMenu();

            if (menuNum == 1) search();
            else if (menuNum == 2) list();
            else if (menuNum == 3) add();
            else if (menuNum == 0) System.exit(0);

            waitKey();
        }
    }

    private static int selectMainMenu() {
        MainMenu mainMenu = new MainMenu();
        return mainMenu.get();
    }

    private static void search() {
        DispPmemoEntity dispPmemoEntity = new DispPmemoEntity();
        dispPmemoEntity.run();
    }

    private static void list() {
        DisplayPmemoList displayPmemoList = new DisplayPmemoList();
        displayPmemoList.disp();
    }

    private static void add() {
        CreatePmemoEntity createPmemo = new CreatePmemoEntity();
        PmemoEntity pmemo = createPmemo.create();

        if (pmemo != null) {
            AddPmemoLogic addPmemoLogic = new AddPmemoLogic();
            if (addPmemoLogic.execute( pmemo)) {
                System.out.println("データを追加しました");
            } else {
                System.out.println("データの追加に失敗しました");
            }
        }
    }

    private static void waitKey() {
        String prompt = "Enterキーを押してください";
        ScanInputString scanInputString = new ScanInputString( prompt );
        String key = scanInputString.get();
    }
}

// 修正時刻: Sun Nov 15 14:23:00 2020
