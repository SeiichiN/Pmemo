package com.billies_works;

import java.util.List;

import com.billies_works.dao.PmemoDao;
import com.billies_works.model.PmemoEntity;
import com.billies_works.model.AddPmemoLogic;
import com.billies_works.CreatePmemoEntity;

public class AddPmemoTest {
    public static void main( String[] args) {

        CreatePmemoEntity createPmemo = new CreatePmemoEntity();
        PmemoEntity pmemo = createPmemo.create();

        if (pmemo != null) {
            AddPmemoLogic addPmemoLogic = new AddPmemoLogic();
            if (addPmemoLogic.execute( pmemo )) {
                System.out.println("データを追加しました");
            } else {
                System.out.println("データの追加に失敗しました");
            }

        }
    }
}

// 修正時刻: Sun Nov 15 14:17:40 2020
