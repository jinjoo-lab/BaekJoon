import java.util.*;
import java.io.*;

public class Main {

    static int tt;
    static int n,m,k;
    static int[] arr = new int[100_001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        tt = Integer.parseInt(st.nextToken());

        for(int t = 1 ; t <= tt ; t++) {
            st = new StringTokenizer(br.readLine()," ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine()," ");

            for(int i = 1 ; i <= n ; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int tmpResult = go();
            sb.append(tmpResult+"\n");
        }

        System.out.print(sb);

        br.close();
    }

    static int go() {
        int result = 0;
        long tmpResult = 0;
        int i = 1;
        int j = m;

        for(int tmp = 1 ; tmp <= m ; tmp++) {
            tmpResult += arr[tmp];
        }
        i += 1;

        if(tmpResult < k)
            result++;

        if(m == n)
            return result;

        while(i <= n) {
            tmpResult -= arr[i-1];

            j = j + 1;
            if(j > n)
                j = 1;

            tmpResult += arr[j];

            if(tmpResult < k) {
                result++;
            }

            i++;
        }

        return result;
    }

    static void print() {
        for(int i = 1 ; i <= n ; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
}
