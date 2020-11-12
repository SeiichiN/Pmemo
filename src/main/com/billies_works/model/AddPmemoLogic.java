
package com.billies_works.model;

import com.billies_works.dao.PmemoDao;
import com.billies_works.command.Command;

public class AddPmemoLogic implements Command {
    private PmemoEntity pmemo;

    public void setPmemo (PmemoEntity pmemo) {
        this.pmemo = pmemo;
    }
    
    public void execute() {
        PmemoDao pmemoDao = new PmemoDao();
        if (! pmemoDao.addPmemo( pmemo )){
            System.out.println("データの追加に失敗しました。");
        }
    }
}

// 修正時刻: Thu Nov 12 15:45:58 2020
