package com.billies_works;

import java.util.List;

import com.billies_works.model.GetPmemoListLogic;
import com.billies_works.model.PmemoEntity;
import com.billies_works.util.ZenHanFormat;

public class DisplayPmemoList {
    public DisplayPmemoList() {}
    
    public void disp() {
        GetPmemoListLogic getPmemoListLogic = new GetPmemoListLogic();
        List<PmemoEntity> pmemoList = getPmemoListLogic.get();
        // pmemoList.forEach(ele -> System.out.println(ele.toString()));
        
        ZenHanFormat zhf = new ZenHanFormat("win");

        String headline = "-- name -- + - id - + ------ Email  ------ +" +
            " --- password --- + ----- other ------ + ------ date -------";
        System.out.println(headline);
        int i = 0;
        for ( PmemoEntity item : pmemoList )  {
            i++;
            String itemName = zhf.zhFormat(item.getName(), 10);
            String itemId = zhf.zhFormat(item.getId(), 6);
            String itemEmail = zhf.zhFormat(item.getEmail(), 20);
            String itemPassword = zhf.zhFormat(item.getPassword(), 16);
            String itemOther = zhf.zhFormat(item.getOther(), 18);
            System.out.print(itemName + " | ");
            System.out.print(itemId + " | ");
            System.out.print(itemEmail + " | ");
            System.out.print(itemPassword + " | ");
            System.out.print(itemOther + " | ");
            System.out.printf("%14s", item.getCreated_at());
                
            System.out.println();
            if (i % 30 == 0) {
                waitEnter();
                System.out.println(headline);
            }
        }
    }

    /**
     *
     */
    static void waitEnter() {
        System.out.println("Enterキーを押してください...");
        try {
            int c = 0;
            do {
                c = 0;
                c = System.in.read();
            } while (c != 10);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

// 修正時刻: Tue Nov 17 12:27:25 2020
