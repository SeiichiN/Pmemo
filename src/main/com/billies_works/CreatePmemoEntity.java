
package com.billies_works;

import com.billies_works.model.PmemoEntity;
import com.billies_works.util.GetUserInput;
import com.billies_works.util.ClearConsole;

public class CreatePmemoEntity {
    private PmemoEntity pmemo;

    public CreatePmemoEntity() {}

    public PmemoEntity create() {
        // 画面クリア
        ClearConsole clsConsole = new ClearConsole();
        clsConsole.clear();
        
        System.out.println("------ データの入力 ------");
        System.out.println("*印は必須項目です。 (0:中止)");
        String name = null;

        while (name == null) {
            name = GetUserInput.get("*name (登録名) or '0'> ");
        }
        if ("0".equals(name)) return null;

        String id = GetUserInput.get(" id (もしあるなら) > ");
        if (id == null) id = "-";

        String email = null;
        while (email == null) {
            email = GetUserInput.get("*Email (登録メールアドレス) > ");
        }

        String password = null;
        while (password == null) {
            password = GetUserInput.get("*password (登録パスワード) > ");
        }

        String other = GetUserInput.get("その他 (メモ) > ");
        if (other == null) other = "-";

        pmemo = new PmemoEntity( name, id, email, password, other );

        System.out.println("------ 確認 ------");
        System.out.println( pmemo.toString() );
        String yesno = GetUserInput.get("よろしいですか？ (y/n) ").toLowerCase();
        if ("n".equals(yesno)) return null;

        return pmemo;
    }
}

// 修正時刻: Fri Nov 13 06:28:35 2020
