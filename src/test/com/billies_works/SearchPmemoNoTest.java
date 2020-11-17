
package com.billies_works;

import java.util.Scanner;

import com.billies_works.dao.PmemoDao;
import com.billies_works.model.PmemoEntity;
import com.billies_works.model.SearchPmemoNoLogic;

public class SearchPmemoNoTest {
    public static void main( String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("------ 検索処理 ------");
        System.out.print("noを入力 > ");
        int no = scanner.nextInt();
        scanner.close();

        SearchPmemoNoLogic searchNoLogic = new SearchPmemoNoLogic();
        PmemoEntity pmemo = searchNoLogic.execute( no );

        if (pmemo != null)
            System.out.println( pmemo.toString() );
        else
            System.out.println( "該当なし。");
        
    }
}

// 修正時刻: Sat Nov 14 07:11:21 2020
