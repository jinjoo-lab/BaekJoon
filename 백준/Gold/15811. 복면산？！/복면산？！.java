import java.util.*;
import java.io.*;

public class Main {

    static char[] a;
    static char[] b;
    static char[] c;

    static int[] w;

    static boolean[] alpha;
    static boolean[] count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        a = st.nextToken().toCharArray();
        b = st.nextToken().toCharArray();
        c = st.nextToken().toCharArray();

        w = new int[91];
        count = new boolean[10];

        alpha = new boolean[92];
        for(int i = 0 ; i < a.length ; i++){
            alpha[a[i]] = true;
        }

        for(int i = 0 ; i < b.length ; i++){
            alpha[b[i]] = true;
        }

        for(int i = 0 ; i < c.length ; i++){
            alpha[c[i]] = true;
        }

        go(65);
        System.out.println(result);

        br.close();
    }

    static int make(char[] arr) {
        int len = arr.length;

        int v = 0;
        int cur = 1;
        for(int i = len - 1 ; i >= 0 ; i--) {
            v += cur * w[arr[i]];
            cur *= 10;
        }


        return v;
    }

    static String result = "NO";

    static void go(int idx) {

        if(idx > 90) {
            int tmpA = make(a);
            int tmpB = make(b);
            int tmpC = make(c);

            if(tmpC == tmpA + tmpB) {
                result = "YES";
            }
            return;
        }

        if(!alpha[idx]) {
            go(idx + 1);
        }else {
            for (int i = 0; i <= 9; i++) {
                if (!count[i]) {
                    count[i] = true;
                    w[idx] = i;
                    go(idx + 1);
                    w[idx] = 0;
                    count[i] = false;
                }
            }
        }
    }
}
