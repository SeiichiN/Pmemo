
package com.billies_works.model;

import java.util.List;
import com.billies_works.dao.PmemoDao;
import com.billies_works.command.Command;

public class GetPmemoListLogic implements Command {
    
    public List<PmemoEntity> get() {
        PmemoDao pmemoDao = new PmemoDao();
        return pmemoDao.findAll();
    }

    public void execute() {}
}

// 修正時刻: Sun Nov 15 13:52:39 2020
