
package com.billies_works.model;

import java.util.List;
import com.billies_works.dao.PmemoDao;
import com.billies_works.command.Command;

public class GetPmemoListLogic implements Command {
    private List<PmemoEntity> pmemoList;

    public List<PmemoEntity> getPmemoList() {
        return pmemoList;
    }
    
    public void execute() {
        PmemoDao pmemoDao = new PmemoDao();
        pmemoList = pmemoDao.findAll();
    }
}

// 修正時刻: Thu Nov 12 15:48:51 2020
