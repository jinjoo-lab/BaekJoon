import java.util.*;
import java.io.*;

public class Main {

    static int t,n,m;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        t = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        for(int tt = 1 ; tt <= t ; tt++) {
            st = new StringTokenizer(br.readLine()," ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            arr = new int[n+1];

            int[] result = new int[n+1];
            int j = 1;

            st = new StringTokenizer(br.readLine()," ");
            for(int i = 1 ; i <= n ; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            result[j] = arr[1];

            for(int i = 1 ; i <= n ; i++) {
                if(result[j] < arr[i]) {
                    result[j+1] = arr[i];
                    j += 1;
                }else {
                    int idx = find(result,j,arr[i]);
                    result[idx] = arr[i];
                }
            }

            sb.append("Case #").append(tt).append("\n");
            int rr = j >= m ? 1 : 0;
            sb.append(rr).append("\n");
        }


        System.out.print(sb);
        br.close();
    }

    static void print(int[] result) {
        for(int i = 1  ; i <= n ; i++) {
            System.out.print(result[i]+", ");
        }
        System.out.println();
    }

    static int find(int[] arr, int j, int target) {
        int l = 1;
        int r = j;

        while(l <= r) {
            int mid = (l + r) / 2;

            if(arr[mid] >= target) {
                r = mid - 1;
            }else {
                l = mid + 1;
            }
        }

        return l;
    }
}
