package com.billies_works;

import java.util.Scanner;

import com.billies_works.model.DeletePmemoNoLogic;

public class DeletePmemoNoTest {
    public static void main( String[] args) {

        System.out.println("------ 削除処理 ------");
        System.out.print("削除する no > ");
        Scanner scanner = new Scanner( System.in );
        int no = scanner.nextInt();

        DeletePmemoNoLogic deletePmemoNoLogic = new DeletePmemoNoLogic();
        deletePmemoNoLogic.set(no);
        deletePmemoNoLogic.execute();

        if (deletePmemoNoLogic.isSuccess()) {
            System.out.println("No:" + no + "のデータを削除しました。");
        } else {
            System.out.println("削除できませんでした。");
        }
        
    }
}

// 修正時刻: Fri Nov 13 14:16:31 2020
