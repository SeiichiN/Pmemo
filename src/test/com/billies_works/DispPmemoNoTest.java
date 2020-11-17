
package com.billies_works;

import java.util.Scanner;

import com.billies_works.dao.PmemoDao;
import com.billies_works.model.PmemoEntity;
import com.billies_works.model.SearchPmemoNoLogic;
import com.billies_works.DispPmemoEntity;
import com.billies_works.SubMenu;
import com.billies_works.EditPmemoEntity;

public class DispPmemoNoTest {
    public static void main( String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("------ 検索処理 ------");
        System.out.print("noを入力 > ");
        int no = scanner.nextInt();

        SearchPmemoNoLogic searchNoLogic = new SearchPmemoNoLogic();
        PmemoEntity pmemo = searchNoLogic.execute( no );

        DispPmemoEntity dispPmemo = new DispPmemoEntity();
        dispPmemo.run();

        SubMenu subMenu = new SubMenu( pmemo );
        // int subMenuNum = subMenu.getNum();
        // System.out.println("選択番号 > " + subMenuNum);

        // EditPmemoEntity editPmemoEntity = new EditPmemoEntity( pmemo );
        // editPmemoEntity.edit();
    }
}

// 修正時刻: Mon Nov 16 09:28:34 2020
