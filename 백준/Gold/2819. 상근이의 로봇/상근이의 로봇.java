import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static int[] x;
    static int[] y;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        x = new int[n];
        y = new int[n];

        for(int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int cx = Integer.parseInt(st.nextToken());
            int cy = Integer.parseInt(st.nextToken());

            x[i] = cx;
            y[i] = cy;
        }

        Arrays.sort(x);
        Arrays.sort(y);

        int curX = 0;
        int curY = 0;

        long result = 0;
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < n ; i++) {
            result += Math.abs(x[i]) + Math.abs(y[i]);
        }

        char[] order = br.readLine().toCharArray();

        for(int i = 0 , size = order.length ; i < size ; i++) {
            char curOrder = order[i];

            if(curOrder == 'S') {
                int upperY = upper(curY,y);
                result += upperY;
                result -= (n - upperY);
                curY += 1;
            }else if(curOrder == 'J') {
                int lowerY = lower(curY,y);
                result -= lowerY;
                result += (n - lowerY);
                curY -= 1;
            }else if(curOrder == 'I') {
                int upperX = upper(curX,x);
                result += upperX;
                result -= (n - upperX);
                curX += 1;
            }else if(curOrder == 'Z') {
                int lowerX = lower(curX,x);
                result -= lowerX;
                result += (n - lowerX);
                curX -= 1;
            }

            sb.append(result).append("\n");
        }

        System.out.println(sb);

        br.close();
    }

    static int lower(int target,int[] arr) {
        int l = 0;
        int r = n;

        while(l < r) {
            int mid = (l + r) / 2;

            if(arr[mid] < target) {
                l = mid + 1;
            }else {
                r = mid;
            }
        }

        return l;
    }

    static int upper(int target, int[] arr) {
        int l = 0;
        int r = n;

        while(l < r) {
            int mid = (l + r) / 2;

            if(arr[mid] <= target) {
                l = mid + 1;
            }else {
                r = mid;
            }
        }

        return l;
    }
}
