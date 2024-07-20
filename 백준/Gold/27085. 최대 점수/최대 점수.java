import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static long[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new long[n+1];


        st = new StringTokenizer(br.readLine()," ");
        for(int i = 1; i <= n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        for(int i = m  + 1 ; i <= n ; i++) {
            arr[i] += arr[i-1];
        }

        for(int i = m - 1 ; i >= 1 ; i--) {
            arr[i] += arr[i+1];
        }

        int l = m;
        int r = m;

        long lMax = 0;
        long rMax = 0;

        while(true) {

            boolean keep = false;

            while (l > 1) {
                if(rMax + arr[l - 1] < 0) {
                    break;
                }
                l--;

                if(lMax < arr[l]) {
                    lMax = arr[l];
                    keep = true;
                }
            }

            while (r < n) {
                if(lMax + arr[r + 1] < 0) {
                    break;
                }
                r++;

                if(rMax < arr[r]) {
                    rMax = arr[r];
                    keep = true;
                }
            }

            if(!keep)
                break;
        }

        System.out.println(lMax + rMax);

        br.close();
    }

    static void print() {
        for(int i = 1 ; i <= n ; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
}
