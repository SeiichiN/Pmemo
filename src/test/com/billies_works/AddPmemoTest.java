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
            addPmemoLogic.setPmemo( pmemo );
            addPmemoLogic.execute();
        }
    }
}

// 修正時刻: Fri Nov 13 07:02:54 2020
