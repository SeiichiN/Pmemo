package com.billies_works.command;

import java.util.List;

import com.billies_works.model.PmemoEntity;

public interface GetListCommand {
    List<PmemoEntity> execute();
}

// 修正時刻: Mon Nov 30 18:29:02 2020
