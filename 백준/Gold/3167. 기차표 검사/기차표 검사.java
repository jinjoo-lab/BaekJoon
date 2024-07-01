import java.util.*;
import java.io.*;

public class Main {
    static int n,k;

    static int[][] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n+1][2];
        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int out = Integer.parseInt(st.nextToken());
            int in = Integer.parseInt(st.nextToken());

            arr[i][0] = out;
            arr[i][1] = in;

        }

        System.out.println(getMin()+" "+getMax());

        br.close();
    }

    static int getMax() {
        int result = 0;

        int idx = 1;

        int cIn = arr[1][1];
        int nIn = 0;

        for(int i = 2 ; i <= n ; i++) {

            int curOut = arr[i][0];
            int curIn = arr[i][1];

            if(nIn >= curOut) {
                result += curOut;
                nIn -= curOut;
            }else {
                result += nIn;
                curOut -= nIn;
                nIn = 0;
                cIn -= curOut;
            }

            int nIdx = getIdx(idx);

            if(i == nIdx) {
                cIn += nIn;
                cIn += curIn;
                nIn = 0;
                idx++;
            }else {
                nIn += curIn;
            }
        }

        return result;
    }

    static int getMin() {
        int result = 0;

        int idx = 1;

        int cIn = arr[1][1];
        int nIn = 0;

        for(int i = 2 ; i <= n ; i++) {
            int curOut = arr[i][0];
            int curIn = arr[i][1];

            if(cIn >= curOut) {
                cIn -= curOut;
            }else {
                curOut -= cIn;
                cIn = 0;
                result += curOut;
                nIn -= curOut;
            }

            int nIdx = getIdx(idx);

            if(i == nIdx) {
                cIn += nIn;
                cIn += curIn;
                nIn = 0;
                idx++;
            }else {
                nIn += curIn;
            }
        }

        return result;
    }


    static int getIdx(int idx) {
        return (k * idx) + 1;
    }
}
