package com.billies_works;

import java.util.List;

import com.billies_works.model.GetPmemoListLogic;
import com.billies_works.model.PmemoEntity;

public class DisplayPmemoList {
    public DisplayPmemoList() {}
    
    public void disp() {
        GetPmemoListLogic getPmemoListLogic = new GetPmemoListLogic();
        List<PmemoEntity> pmemoList = getPmemoListLogic.get();

        pmemoList.forEach( pmemo -> {
                System.out.println( pmemo.toString() );
            });
    }

}

// 修正時刻: Sun Nov 15 13:55:39 2020
