
package com.billies_works.model;

import java.util.List;
import com.billies_works.dao.PmemoDao;
import com.billies_works.command.Command;

/**
 * 名前で検索する。
 *
 * 手順: (1) SearchPmemoNameLogic searchNoLogic = new SearchPmemoNoLogic();
 *       (2) searchNoLogic.set( no );
 *       (3) searchNoLogic.execute();
 *       (4) PmemoEntity pmemo = searchNoLogic.get();
 */
public class SearchPmemoNoLogic implements Command {
    private PmemoEntity pmemo = null;
    private int no;

    public PmemoEntity get() {
        return pmemo;
    }

    public void set( int no ) {
        this.no = no;
    }
    
    public void execute() {
        PmemoDao pmemoDao = new PmemoDao();
        pmemo = pmemoDao.searchPmemoNo( no );
    }
}

// 修正時刻: Fri Nov 13 13:21:25 2020
