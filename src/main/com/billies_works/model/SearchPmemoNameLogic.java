
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

    public List<PmemoEntity> execute( String name ) {
        this.name = name;
        PmemoDao pmemoDao = new PmemoDao();
        pmemoList = pmemoDao.searchPmemoName( name );
        return pmemoList;
    }
    
    public void execute() {}
}

// 修正時刻: Sat Nov 14 07:08:06 2020
