import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[] arr;
    static int[] sum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());

        arr = new int[n+1];
        sum = new int[n+1];

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 1 ; i <= n ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        sum[1] = arr[1];

        for(int i = 2 ; i <= n ; i++) {
            sum[i] = arr[i] + sum[i - 2];
        }


        int max = sum[n - 1];

        for(int i= 1 ; i <= n ; i++) {

            int tmpV = 0;

            if(i % 2 == 1) {
                // my turn
                tmpV = i - 2 < 0 ? 0 : sum[i-2];
                tmpV += sum[n] - sum[i-1];
            }else {
                tmpV = sum[i-1];
                tmpV += sum[n-2] - sum[i-2];
            }

            max = Math.max(max,tmpV);
        }

        System.out.println(max);
        br.close();
    }

    static void print() {
        for(int i = 1 ; i <= n ; i++) {
            System.out.print(sum[i]+" ");
        }
        System.out.println();
    }
}
