
package com.billies_works;

import java.util.HashSet;

import com.billies_works.model.PmemoEntity;
import com.billies_works.util.ScanInputInt;
import com.billies_works.util.ScanInputString;
import com.billies_works.model.UpdatePmemoLogic;

public class EditPmemoEntity {
    PmemoEntity pmemo = null;
    String[] itemName = {" ", "登録名", "ID", "E-Mail", "Password", "Other"};
    
    public EditPmemoEntity( PmemoEntity pmemo ) {
        this.pmemo = pmemo;
    }

    public void edit() {
        int num = getEditNum();
        System.out.println( "(旧) " + itemName[num] + " : " + getBeforeData(num));
        String prompt = "(新) " + itemName[num] + " > ";
        ScanInputString scanInputString = new ScanInputString( prompt );
        String newData = scanInputString.get();
        if (setNewData( num, newData )) {
            // System.out.println( pmemo.toString() );
            UpdatePmemoLogic updatePmemoLogic = new UpdatePmemoLogic();
            if (updatePmemoLogic.execute( pmemo )) {
                System.out.println("更新しました");
            } else {
                System.out.println("更新に失敗しました");
            }
        } else {
            System.out.println("新データのセット失敗");
        }
    }

    private int getEditNum() {
        System.out.println("------------ 訂正処理 ------------");
        String prompt = "訂正する項目の番号 > ";

        HashSet<Integer> numSet = new HashSet<>();
        numSet.add(1);
        numSet.add(2);
        numSet.add(3);
        numSet.add(4);
        numSet.add(5);
        
        ScanInputInt scanInputInt = new ScanInputInt( prompt );
        return scanInputInt.get( numSet );
    }

    private String getBeforeData( int num ) {
        if (num == 1) return pmemo.getName();
        if (num == 2) return pmemo.getId();
        if (num == 3) return pmemo.getEmail();
        if (num == 4) return pmemo.getPassword();
        if (num == 5) return pmemo.getOther();
        return null;
    }

    private boolean setNewData( int num, String newData ) {
        if (num == 1) pmemo.setName( newData );
        else if (num == 2) pmemo.setId( newData );
        else if (num == 3) pmemo.setEmail( newData );
        else if (num == 4) pmemo.setPassword( newData );
        else if (num == 5) pmemo.setOther( newData );
        else return false;
        return true;
    }

}



// 修正時刻: Sat Nov 14 23:48:13 2020
