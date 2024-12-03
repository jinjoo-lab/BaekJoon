import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static int[] arr;
    static int[] sum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n+2];
        sum = new int[n+2];

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 1 ; i <= n ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1 ; i <= m ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            sum[s] += v;
            sum[e + 1] -= v;
        }

        for(int i = 1 ; i <= n + 1 ; i++) {
            sum[i] += sum[i-1];
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 1 ; i <= n ; i++) {
            arr[i] += sum[i];
            sb.append(arr[i]+" ");
        }
        System.out.println(sb);

        br.close();
    }
}
