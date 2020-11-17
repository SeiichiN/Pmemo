
package com.billies_works.model;

import com.billies_works.dao.PmemoDao;
import com.billies_works.command.Command;

public class UpdatePmemoLogic implements Command {

    public boolean execute( PmemoEntity pmemo ) {
        PmemoDao pmemoDao = new PmemoDao();
        if (pmemoDao.updatePmemo( pmemo ))
            return true;
        else
            return false;
    }
    
    public void execute() {}
}

// 修正時刻: Sat Nov 14 23:42:41 2020
