
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
        searchNoLogic.set( no );
        searchNoLogic.execute();
        PmemoEntity pmemo = searchNoLogic.get();

        // PmemoDao pmemoDao = new PmemoDao();
        // PmemoEntity pmemo = pmemoDao.searchPmemoNo( no);

        System.out.println( pmemo.toString() );

        
    }
}

// 修正時刻: Fri Nov 13 13:58:53 2020
