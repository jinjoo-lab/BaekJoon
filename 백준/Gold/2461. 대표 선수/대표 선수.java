import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static int[][] score;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        score = new int[n][m];

        for(int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            for(int j = 0 ; j < m ; j++) {
                score[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0 ; i < n ; i++) {
            Arrays.sort(score[i]);
        }

        int result = Integer.MAX_VALUE;
        int[] curIdx = new int[n];

        while(true) {
            int minV = Integer.MAX_VALUE;
            int maxV = 0;
            int idx = 0;

            for(int i = 0 ; i < n ; i++) {
                if(minV > score[i][curIdx[i]]) {
                    minV = score[i][curIdx[i]];
                    idx = i;
                }

                maxV = Math.max(maxV, score[i][curIdx[i]]);
            }

            result = Math.min(result,(maxV - minV));
            curIdx[idx]++;

            if(curIdx[idx] >= m)
                break;
        }

        System.out.println(result);
        br.close();
    }
}
