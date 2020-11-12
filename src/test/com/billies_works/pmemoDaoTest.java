package com.billies_works;

import java.util.List;

import com.billies_works.dao.PmemoDao;
import com.billies_works.model.PmemoEntity;
import com.billies_works.model.GetPmemoListLogic;

public class pmemoDaoTest {
    public static void main( String[] args) {
        GetPmemoListLogic getPmemoListLogic = new GetPmemoListLogic();
        getPmemoListLogic.execute();
        List<PmemoEntity> pmemoList = getPmemoListLogic.getPmemoList();

        for (PmemoEntity pmemo : pmemoList) {
            System.out.println( pmemo.toString() );
        }
    }
}

// 修正時刻: Thu Nov 12 20:29:11 2020
