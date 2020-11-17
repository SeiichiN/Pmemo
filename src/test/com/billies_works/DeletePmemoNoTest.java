package com.billies_works;

import java.util.Scanner;

import com.billies_works.model.DeletePmemoNoLogic;
import com.billies_works.model.SearchPmemoNoLogic;
import com.billies_works.model.PmemoEntity;

public class DeletePmemoNoTest {
    public static void main( String[] args) {

        System.out.println("------ 削除処理 ------");
        System.out.print("削除する no > ");
        Scanner scanner = new Scanner( System.in );
        int no = scanner.nextInt();

        SearchPmemoNoLogic searchPmemoNoLogic = new SearchPmemoNoLogic();
        PmemoEntity pmemo = searchPmemoNoLogic.execute( no );
        

        DeletePmemoNoLogic deletePmemoNoLogic = new DeletePmemoNoLogic();

        if (deletePmemoNoLogic.execute( pmemo )) {
            System.out.println("No:" + no + "のデータを削除しました。");
        } else {
            System.out.println("削除できませんでした。");
        }
        
    }
}

// 修正時刻: Tue Nov 17 20:50:55 2020
