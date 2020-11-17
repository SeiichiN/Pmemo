
package com.billies_works;

import java.util.ArrayList;
import java.util.List;

import com.billies_works.model.PmemoEntity;
import com.billies_works.util.ClearConsole;
import com.billies_works.util.ScanInputString;
import com.billies_works.util.ScanInputInt;
import com.billies_works.model.SearchPmemoNameLogic;
import com.billies_works.model.SearchPmemoNoLogic;
import com.billies_works.DispPmemoEntity;
import com.billies_works.SubMenu;


/**
 * 1件表示するクラス
 * 
 */
public class DispPmemoEntity {

    public DispPmemoEntity() {}

    public void run() {
        PmemoEntity pmemo = selectPmemo();
        if (pmemo != null) {
            cls();
            disp( pmemo );
            SubMenu subMenu = new SubMenu( pmemo );
            subMenu.run();
        }
    }

    private PmemoEntity selectPmemo() {
        PmemoEntity pmemo = null;

        System.out.println("------------ 検索処理 ------------");
        ScanInputString scanInputString = new ScanInputString("登録名を入力 > ");
        String name = scanInputString.get();
        cls();
        
        // nameで検索。Listで返ってくる。
        List<PmemoEntity> pmemoList = null;
        SearchPmemoNameLogic searchNameLogic = new SearchPmemoNameLogic();
        pmemoList = searchNameLogic.execute( name );

        if (pmemoList.size() > 0) {
            pmemoList.forEach( ele -> disp(ele) );
        
            pmemo = selectOne( pmemoList );
        } else {
            System.out.println("該当するデータはありません");
            return null;
        }
        return pmemo;
        
    }

    private PmemoEntity selectOne( List<PmemoEntity> pmemoList ) {
        if (pmemoList.size() == 1) {
            return pmemoList.get(0);
        } else {
            String prompt = "Noを指定してください > ";
            ScanInputInt scanInputInt = new ScanInputInt( prompt );
            int no = scanInputInt.get();
            SearchPmemoNoLogic searchNoLogic = new SearchPmemoNoLogic();
            return searchNoLogic.execute( no );
        }
    }

    private void cls() {
        // 画面クリア
        ClearConsole clsConsole = new ClearConsole();
        clsConsole.clear();
    }

    private void disp( PmemoEntity pmemo ) {
        
        System.out.println();
        System.out.println("------------ Pmemo ------------");
        System.out.println("   No       : " + pmemo.getNo());
        System.out.println("1) 登録名   : " + pmemo.getName());
        System.out.println("2) ID       : " + pmemo.getId());
        System.out.println("3) E-Mail   : " + pmemo.getEmail());
        System.out.println("4) Password : " + pmemo.getPassword());
        System.out.println("5) Other    : " + pmemo.getOther());
        System.out.println("   登録日時 : " + pmemo.getCreated_at());
        System.out.println("-------------------------------");
    }
}

// 修正時刻: Sun Nov 15 12:26:15 2020
