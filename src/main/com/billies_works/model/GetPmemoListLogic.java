
package com.billies_works.model;

import java.util.List;
import com.billies_works.dao.PmemoDao;
import com.billies_works.command.GetListCommand;

public class GetPmemoListLogic implements GetListCommand {
    
    public List<PmemoEntity> execute() {
        PmemoDao pmemoDao = new PmemoDao();
        return pmemoDao.findAll();
    }
}

// 修正時刻: Mon Nov 30 18:29:42 2020










