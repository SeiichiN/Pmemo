
package com.billies_works.model;

import com.billies_works.model.PmemoEntity;
import com.billies_works.dao.PmemoDao;
import com.billies_works.command.Command;

/**
 * Noで削除する
 *
 */
public class DeletePmemoNoLogic implements Command {

    public boolean execute( PmemoEntity pmemo ) {
        PmemoDao pmemoDao = new PmemoDao();
        int no = pmemo.getNo();
        if (pmemoDao.deletePmemoNo( no )) {
            return true;
        }
        return false;
    }
    
    public void execute() {}
}

// 修正時刻: Sun Nov 15 12:37:42 2020
