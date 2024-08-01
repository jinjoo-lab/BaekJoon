import java.util.*;
import java.io.*;

public class Main {

    static String line;
    static int n;
    static char[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        line = br.readLine();
        arr = line.toCharArray();

        if(n % 2 == 0) {
            System.out.println("NOT POSSIBLE");
        } else {
            boolean tmpL = goLeft();
            boolean tmpR = goRight();

            if(tmpL && tmpR) {

                String tmpS2 = line.substring(0,n/2);
                String tmpS1 = line.substring(n/2 + 1);

                if(tmpS1.equals(tmpS2)) {
                    System.out.println(tmpS1);
                }
                else {
                    System.out.println("NOT UNIQUE");
                }
            }else if(tmpL) {
                System.out.println(line.substring(n/2 + 1));
            }else if(tmpR) {
                System.out.println(line.substring(0,n/2));
            }else {
                System.out.println("NOT POSSIBLE");
            }
        }


        br.close();
    }

    static boolean goLeft() {
        int count = 0;

        int idx = n / 2 + 1;
        for(int i = 0 ; i <= n / 2 ; i++) {

            if(count > 1)
                return false;

            if(arr[i] == arr[idx]) {
                idx++;

                if(idx >= n)
                    break;
            }else {
                count++;
            }
        }

        return count <= 1;
    }

    static boolean goRight() {
        int count = 0;

        int idx = 0 ;

        for(int i = n/2 ; i < n ; i++) {

            if(count > 1)
                return false;

            if(arr[i] == arr[idx]) {
                idx++;

                if(idx >= n / 2)
                    break;
            }else {
                count++;
            }
        }


        return count <= 1;
    }
}
