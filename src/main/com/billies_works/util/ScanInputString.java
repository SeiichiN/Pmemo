
package com.billies_works.util;

import java.util.Scanner;
import java.util.NoSuchElementException;

public class ScanInputString {
    String prompt = null;

    public ScanInputString( String prompt) {
        this.prompt = prompt;
    }

    public String get() {
        Scanner scanner = null;
        String text = null;
        boolean isEmpty = true;

        while( isEmpty ) {
            System.out.print( prompt );
            try {
                scanner = new Scanner( System.in );
                text = scanner.nextLine();
                isEmpty = false;
            } catch (NoSuchElementException e) {
                e.printStackTrace();
                scanner = null;
            } 
        }
        return text;
    }
}

// 修正時刻: Sat Nov 14 19:17:08 2020
