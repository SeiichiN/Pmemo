// GetConf.java
package com.billies_works.util;

import java.io.*;
import java.util.*;

/**
 * settei.conf の内容を Map<String, String> の形で読み取る
 * 使い方:
 *   Map<String, String> mapList = new GetConf("settei.conf").load();
 *   String username = mapList.get("username");
 */
public class GetConf {

    String filename = null;
    Map<String, String> confList = new HashMap<>();

    public GetConf(String fname) {
        this.filename = fname;
    }
    
    public Map<String, String> load () {
        try {
            File file = new File(filename);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line = null;
            while ((line = reader.readLine()) != null) {
                addMap(line);
            }
        } catch (FileNotFoundException fe) {
            System.out.println("ファイルがありません。");
            System.exit(1);
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return confList;
    }

    void addMap(String line) {
        String[] token = line.split(" ");
        confList.put(token[0], token[1]);
    }
}
