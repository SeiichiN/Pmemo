// GetConf.java
package com.billies_works.util;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * settei.conf の内容を Map<String, String> の形で読み取る
 * 使い方:
 *   Map<String, String> mapList = new GetConf("settei.conf").load();
 *   String username = mapList.get("username");
 */
public class GetConf {

    String filename = null;

    public GetConf(String fname) {
        this.filename = fname;
    }
    
    public Map<String, String> load () {
        Map<String, String> confList = new HashMap<>();
        
        try {
            File file = new File(filename);
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            String line = null;
            while (! (line = reader.readLine()).equals("----")) {
                String[] token = line.split(" ");
                confList.put(token[0], token[1]);
            }
        } catch (FileNotFoundException fe) {
            System.out.println("ファイルがありません。");
            System.exit(1);
        } catch (IOException ie) {
            ie.printStackTrace();
            System.out.println("IOエラーです");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" 一般エラーです");
        }
        // System.out.println("confList.size: " + confList.size());
        return confList;
    }
}

// 修正時刻: Sun Nov 15 17:07:08 2020
