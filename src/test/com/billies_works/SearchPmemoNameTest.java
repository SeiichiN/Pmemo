package com.billies_works;

import java.util.List;
import java.util.Scanner;

import com.billies_works.dao.PmemoDao;
import com.billies_works.model.PmemoEntity;
import com.billies_works.model.SearchPmemoNameLogic;

public class SearchPmemoNameTest {
    public static void main( String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("------ 検索処理 ------");
        System.out.print("登録名を入力 > ");
        String name = scanner.nextLine();


        SearchPmemoNameLogic searchNameLogic = new SearchPmemoNameLogic();
        List<PmemoEntity> pmemoList = searchNameLogic.execute( name );

        for (PmemoEntity pmemo : pmemoList) {
            System.out.println( pmemo.toString() );
        }
    }
}

// 修正時刻: Sun Nov 15 09:56:59 2020
