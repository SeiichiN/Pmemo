
package com.billies_works;

import java.util.HashMap;
import java.util.Map;

import com.billies_works.util.GetConf;

public class GetConfTest {
    public static void main( String[] args ) {
        GetConf conf = new GetConf("pmemo.conf");
        Map<String, String> confList = conf.load();

        confList.forEach( (key, val) ->
                          System.out.println( key + " : " + val ));
    }
}

// 修正時刻: Sun Nov 15 16:30:09 2020
