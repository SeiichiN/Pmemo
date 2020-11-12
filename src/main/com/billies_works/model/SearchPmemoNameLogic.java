
package com.billies_works.model;

import java.util.List;
import com.billies_works.dao.PmemoDao;
import com.billies_works.command.Command;

/**
 * 名前で検索する。
 *
 * 手順: (1) SearchPmemoNameLogic searchNameLogic = new SearchPmemoNameLogic();
 *       (2) searchNameLogic.set( name );
 *       (3) searchNameLogic.execute();
 *       (4) List<PmemoEntity> pmemoList = searchNameLogic.get();
 */
public class SearchPmemoNameLogic implements Command {
    private List<PmemoEntity> pmemoList;
    private String name;

    public List<PmemoEntity> get() {
        return pmemoList;
    }

    public void set( String name ) {
        this.name = name;
    }
    
    public void execute() {
        PmemoDao pmemoDao = new PmemoDao();
        pmemoList = pmemoDao.searchPmemoName( name );
    }
}

// 修正時刻: Fri Nov 13 08:18:04 2020
