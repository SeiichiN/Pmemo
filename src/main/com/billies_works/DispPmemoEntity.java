
package com.billies_works;

import com.billies_works.model.PmemoEntity;
import com.billies_works.util.GetUserInput;
import com.billies_works.util.ClearConsole;


/**
 * 1件表示するクラス
 * 
 */
public class DispPmemoEntity {

    public DispPmemoEntity() {}

    public void disp( PmemoEntity pmemo ) {
        
        // 画面クリア
        ClearConsole clsConsole = new ClearConsole();
        clsConsole.clear();
        
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

// 修正時刻: Sat Nov 14 17:13:45 2020
