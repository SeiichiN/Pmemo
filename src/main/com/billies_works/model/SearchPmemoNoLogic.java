
package com.billies_works.model;

import java.util.List;
import com.billies_works.dao.PmemoDao;
import com.billies_works.command.Command;

/**
 * Noで検索する。
 *
 * 手順: (1) SearchPmemoNoLogic searchNoLogic = new SearchPmemoNoLogic();
 *       (2) searchNoLogic.set( no );
 *       (3) searchNoLogic.execute();
 *       (4) PmemoEntity pmemo = searchNoLogic.get();
 */
public class SearchPmemoNoLogic implements Command {
    private PmemoEntity pmemo = null;
    private int no;

    public PmemoEntity execute( int no ) {
        this.no = no;
        PmemoDao pmemoDao = new PmemoDao();
        pmemo = pmemoDao.searchPmemoNo( no );
        return pmemo;
    }

    public void execute() { }
}

// 修正時刻: Sat Nov 14 07:12:02 2020
