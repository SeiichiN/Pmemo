
package com.billies_works.model;

import java.util.List;
import com.billies_works.dao.PmemoDao;
import com.billies_works.command.Command;

/**
 * Noで削除する
 *
 * 手順: (1) DeletePmemoNameLogic deleteNoLogic = new DeletePmemoNoLogic();
 *       (2) deleteNoLogic.set( no );
 *       (3) deleteNoLogic.execute();
 *       (4) if (deleteNoLogic.isSuccess()) System.out.println("削除成功");
 */
public class DeletePmemoNoLogic implements Command {
    private boolean success = false;
    private int no;

    public void set( int no ) {
        this.no = no;
    }
    
    public void execute() {
        PmemoDao pmemoDao = new PmemoDao();
        if (pmemoDao.deletePmemoNo( no )) {
            success = true;
        } else {
            success = false;
        }
    }

    public boolean isSuccess() {
        return success;
    }
}

// 修正時刻: Fri Nov 13 14:22:38 2020
