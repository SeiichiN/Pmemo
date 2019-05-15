// GetUserInput.java ???????????????????????
package com.billies_works;

import java.io.*;

/**
* @param: prompt -- ????????????
*                   ???"?? > "
*
* @raturn: String -- ??????????????????????
*/
public class GetUserInput {
    public String get (String prompt) {
        String inputLine = null;
        System.out.print (prompt + " ");
        try {
            BufferedReader is = new BufferedReader(
                new InputStreamReader (System.in));
            inputLine = is.readLine();
            if (inputLine.length() == 0) return null;
        } catch (IOException e) {
            System.out.println ("IOException: " + e);
        }
        return inputLine.toLowerCase ();
    }
}
