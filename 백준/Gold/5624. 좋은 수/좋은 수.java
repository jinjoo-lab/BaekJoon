import java.util.*;
import java.io.*;


public class Main {

    static int n;
    static int[] arr;
    static boolean[] v = new boolean[400_001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n+1];

        for(int i = 1 ; i <= n ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;

        for(int i = 1; i <= n ; i++) {

            int curNum = arr[i];

            for(int j = 1 ; j < i ; j++) {
                if(v[arr[i] - arr[j] + 200_000]) {
                    result++;
                    break;
                }
            }

            for(int j = 1 ; j <= i ; j++) {
                v[arr[i] + arr[j] + 200_000] = true;
            }
        }


        System.out.println(result);
        br.close();
    }

}

