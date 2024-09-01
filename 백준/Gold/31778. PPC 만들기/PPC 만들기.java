import java.util.*;
import java.io.*;

public class Main {
    static int n,m;
    static String line;
    static char[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        line = br.readLine();
        arr = line.toCharArray();

        int l = 0;
        int r = n - 1;

        while(m > 0 && l < r) {
            while(l < n && arr[l] == 'P') {
                l++;
            }

            while(r > 0 && arr[r] == 'C') {
                r--;
            }

            if(l < r) {
                m--;
                arr[l] = 'P';
                arr[r] = 'C';
            }
        }

        long tmpA = 0;
        long tmpB = 0;
        long tmpC = 0;

        for(char tmp : arr) {
            if(tmp == 'P') {
                tmpB += tmpA;
                tmpA++;
            } else {
                tmpC += tmpB;
            }
        }


        System.out.println(tmpC);
        br.close();
    }
}
