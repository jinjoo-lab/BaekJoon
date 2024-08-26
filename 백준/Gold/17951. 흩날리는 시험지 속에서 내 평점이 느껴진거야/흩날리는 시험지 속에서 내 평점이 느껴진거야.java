import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static int[] score;

    static int max = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        score = new int[n];
        st = new StringTokenizer(br.readLine()," ");

        for(int i = 0 ; i < n ; i++) {
            score[i] = Integer.parseInt(st.nextToken());

            max += score[i];
        }

        int l = 0;
        int r = max;
        int mid = 0;

        while(l <= r) {
            mid = (l + r) / 2;

            int tmpC = go(mid);

            if(tmpC >= m) {
                l = mid + 1;
            }else {
                r = mid - 1;
            }
        }

        System.out.println(r);

        br.close();
    }

    static int go(int target) {
        int cur = 0;
        int gCount = 0;

        for(int i = 0 ; i < n ; i++) {
            cur += score[i];

            if(cur >= target) {
                gCount++;
                cur = 0;
            }
        }
        
        return gCount;
    }
}
