
package com.billies_works.model;

import com.billies_works.dao.PmemoDao;
import com.billies_works.command.Command;

public class AddPmemoLogic implements Command {

    public boolean execute( PmemoEntity pmemo ) {
        PmemoDao pmemoDao = new PmemoDao();
        return pmemoDao.addPmemo( pmemo );
    }
    
    public void execute() {}
}

// 修正時刻: Sun Nov 15 14:23:56 2020
