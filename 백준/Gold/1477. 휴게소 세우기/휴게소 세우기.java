import java.util.*;
import java.io.*;

public class Main {

    static int n,m,l;
    static int[] curP;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        curP = new int[n + 2];

        if(n != 0) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 1; i <= n; i++) {
                curP[i] = Integer.parseInt(st.nextToken());
            }
        }

        curP[n + 1] = l;

        Arrays.sort(curP);

        bs();

        br.close();
    }

    static void bs() {
        int left = 1;
        int right = l;
        int result = l;

        while(left <= right) {
            int mid = (left + right) / 2;

            if(go(mid)) {
                result = mid;
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }

        System.out.println(result);
    }

    static boolean go(int target) {
        int count = 0;

        for(int i = 1 ; i <= n + 1 ; i++) {
            int nextD = curP[i] - curP[i -1];

            count += nextD / target + (nextD % target == 0 ? -1 : 0);
        }

        return count <= m;
    }


    static void print(List<Integer> dis) {
        for(int next : dis) {
            System.out.print(next+" ");
        }
        System.out.println();
    }
}
