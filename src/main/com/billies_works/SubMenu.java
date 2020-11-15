
package com.billies_works;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.HashSet;

import com.billies_works.util.ScanInputInt;
import com.billies_works.util.ScanInputString;
import com.billies_works.model.PmemoEntity;
import com.billies_works.EditPmemoEntity;
import com.billies_works.model.DeletePmemoNoLogic;

/**
 * サブメニュー
 */
public class SubMenu {
    private PmemoEntity pmemo = null;
    
    public SubMenu( PmemoEntity pmemo ) {
        this.pmemo = pmemo;
    }

    public void run() {
        int subMenuNum = getNum();
        if (subMenuNum == 1) edit();
        else if (subMenuNum == 3) delete();
        else if (subMenuNum == 0) System.exit(0);
    }

    private int getNum() {
        Scanner scanner = null;
        int menuNum = 99;
        boolean isNum = false;
        HashSet<Integer> numSet = new HashSet<> ();
        numSet.add(1);
        numSet.add(3);
        numSet.add(8);
        numSet.add(0);

        System.out.println("----------- Submenu -----------");
        System.out.println("1:訂正  3:削除  8:戻る  0:終了");

        ScanInputInt scanInputInt = new ScanInputInt("番号 > ");
        menuNum = scanInputInt.get( numSet );
        
        return menuNum;
    }

    private void edit() {
        EditPmemoEntity editPmemoEntity = new EditPmemoEntity( pmemo );
        editPmemoEntity.edit();
    }

    private void delete() {
        if (isDeleteOK()) {
            DeletePmemoNoLogic deletePmemoNoLogic = new DeletePmemoNoLogic();
            deletePmemoNoLogic.execute( pmemo );
        }
    }

    private boolean isDeleteOK() {
        System.out.println("登録名:" + pmemo.getName() + "のデータを削除します。");
        String prompt = "よろしいですか？ (y/n) > ";
        ScanInputString scanInputString = new ScanInputString( prompt );
        String yesno = scanInputString.get().toLowerCase();
        if ("y".equals(yesno)) return true;
        return false;
    }
}

// 修正時刻: Sun Nov 15 12:50:50 2020
