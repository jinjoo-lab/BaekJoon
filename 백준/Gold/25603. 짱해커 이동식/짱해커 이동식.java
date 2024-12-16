import java.util.*;
import java.io.*;

public class Main {

    static int n,k;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n];

        int min = 1;
        int max = 0;

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 0 ; i < n ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

            max = Math.max(max,arr[i]);
        }

        int result = 0;

        while(min <= max) {
            int mid = (min + max) / 2;

            if(go(mid)) {
                result = mid;
                max = mid - 1;
            }else {
                min = mid + 1;
            }
        }

        System.out.println(result);

        br.close();
    }

    static boolean go(int target) {

        int tmp = 0;

        for(int i = 0 ; i < n ; i++) {
            if(arr[i] <= target) {
                tmp = 0;
                continue;
            }

            tmp++;

            if(tmp >= k) {
                return false;
            }
        }

        return true;
    }
}
