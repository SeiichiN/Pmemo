package com.billies_works;

import java.util.List;

import com.billies_works.dao.PmemoDao;
import com.billies_works.model.PmemoEntity;
import com.billies_works.model.AddPmemoLogic;
import com.billies_works.CreatePmemoEntity;

public class AddPmemoTest {
    public static void main( String[] args) {
        // String name = "sample3";
        // String id = "yukiko";
        // String email = "yukkie@sanada.jp";
        // String password = "5678";
        // String other = "samui ne.";

        // PmemoEntity pmemo = new PmemoEntity( name, id, email,
        //                                      password, other );

        CreatePmemoEntity createPmemo = new CreatePmemoEntity();
        PmemoEntity pmemo = createPmemo.create();
        
        AddPmemoLogic addPmemoLogic = new AddPmemoLogic();
        addPmemoLogic.setPmemo( pmemo );
        addPmemoLogic.execute();
    }
}

// 修正時刻: Thu Nov 12 22:00:17 2020
