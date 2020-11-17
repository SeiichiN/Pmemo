
package com.billies_works;

import java.util.List;

import com.billies_works.model.PmemoEntity;
import com.billies_works.model.GetPmemoListLogic;

public class DispPmemoListTest {
    public static void main( String[] args) {

        GetPmemoListLogic getPmemoListLogic = new GetPmemoListLogic();
        List<PmemoEntity> pmemoList = getPmemoListLogic.get();

        pmemoList.forEach( pmemo ->
                           System.out.println( pmemo.toString()) );
    }
}

// 修正時刻: Mon Nov 16 10:18:47 2020
