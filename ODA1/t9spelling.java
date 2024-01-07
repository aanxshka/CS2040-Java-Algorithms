//Anushka Ashirgade A0259232E

/* PSEUDOCODE 
 * 
 * Create an array of strings of size 256 to accomodate all the ASCII characters 
 * Use ASCII values to assign the corresponding key sequence to each index
 * Take in number of cases 
 *      Take in set of inputs using scanner 
 *      For each input 
 *          Process each characters and combine it with the result string 
 *      Print the final appended result string for the respective case      
 * 
*/

import java.util.Scanner;
import java.util.Arrays;

class t9spelling {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] arr = new String[256];
        Arrays.fill(arr, ""); //initialise all elements to an empty string

        arr['a'] = "2";
        arr['b'] = "22"; 
        arr['c'] = "222";
        arr['d'] = "3";
        arr['e'] = "33";
        arr['f'] = "333";
        arr['g'] = "4";
        arr['h'] = "44";
        arr['i'] = "444";
        arr['j'] = "5";
        arr['k'] = "55";
        arr['l'] = "555";
        arr['m'] = "6";
        arr['n'] = "66";
        arr['o'] = "666";
        arr['p'] = "7";
        arr['q'] = "77";
        arr['r'] = "777";
        arr['s'] = "7777";
        arr['t'] = "8";
        arr['u'] = "88";
        arr['v'] = "888";
        arr['w'] = "9";
        arr['x'] = "99";
        arr['y'] = "999";
        arr['z'] = "9999";

        int n = sc.nextInt(); //reads no. of test cases and consumes newline character

        sc.nextLine(); //consumes newline character 

        for (int i = 1; i <= n; i++) {
            String input = sc.nextLine();
            String res = "";
            char prev = ' ';

            for (char letter : input.toCharArray()) {
                if (!arr[letter].isEmpty() && !arr[prev].isEmpty()
                && arr[letter].charAt(0) == arr[prev].charAt(0)) { 
                    res += " ";
                    res += arr[letter];
                } else if (letter == ' ') {
                    if (prev == ' ') {
                        res += " ";
                        res += "0";
                    } else {
                        res += "0";
                    }
                } else {
                    res += arr[letter];
                }
                prev = letter;


                //res += arr[letter];
                //prev = letter;
            }
            System.out.println("Case #" + i + ": " + res);
        }
        sc.close();

    }

}
