/* PSUEDOCODE 
 * 
 * Take in array of Strings 
 * Create NameComp1 and Name Comp2 
 *      NameComp1 compares strings based on the first character 
 *      NameComp2 compare strings based on the second character
 * 
 * Iterate through array of Strings using NameComp2 followed by NameComp1
 *      This will result in an array that is sorted by the first character and for the same 
 *      first character, sorted by the second character 
 *
 * Return sorted array 
 * Print array 
 * 
 */

import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.*;


public class sortofsorting {

    static class NameComp1 implements Comparator<String> {

        public int compare(String s1, String s2) {
            String s1char1 = "" + s1.charAt(0);
            String s2char1 = "" + s2.charAt(0);
            return s1char1.compareTo(s2char1);
        }
    
        public boolean equals(Object obj) {
            return this == obj;
        }
        
    }
    
    static class NameComp2 implements Comparator<String> {

        public int compare(String s1, String s2) {
            String s1char2 = "" + s1.charAt(1);
            String s2char2 = "" + s2.charAt(1);
            return s1char2.compareTo(s2char2);
        }
    
        public boolean equals(Object obj) {
            return this == obj;
        }
        
    }
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        NameComp1 comp1 = new NameComp1();
        NameComp2 comp2 = new NameComp2();

        int n = sc.nextInt();
        sc.nextLine();
        while (n != 0) {
            String[] arr = new String[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextLine();
            }

            List<String> lst = Arrays.asList(arr);
            Collections.sort(lst,comp2);
            Collections.sort(lst,comp1);
            
            for (String i : lst) {
                System.out.println(i);
            }

            System.out.println();
            n = Integer.parseInt(sc.nextLine());
        }

        sc.close();

    }
 }