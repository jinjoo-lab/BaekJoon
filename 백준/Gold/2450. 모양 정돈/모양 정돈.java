import java.util.*;
import java.io.*;
public class Main {
    static int n = 0;
    static int[] board;

    static int[][] sum = {
            {1,2,3},{1,3,2},
            {2,1,3},{2,3,1},
            {3,1,2},{3,2,1}
    };

    static int[] num = new int[4];

    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(st.nextToken());

        board = new int[n+1];
        st = new StringTokenizer(bf.readLine(), " ");

        for(int i=1;i<=n;i++){
            board[i] = Integer.parseInt(st.nextToken());

            num[board[i]] += 1;
        }


        for(int i=0;i<6;i++) {
            makeTmp(i);
        }

        System.out.println(result);
        bf.close();
    }

    static void makeTmp(int cur){
        int[][] dif = new int[4][4];

        int tmpResult = 0;
        int idx = 1;

        for(int i=1;i<=num[sum[cur][0]];i++){
            dif[sum[cur][0]][board[idx]] += 1;
            idx ++;
        }

        for(int i=1;i<=num[sum[cur][1]];i++){
            dif[sum[cur][1]][board[idx]] += 1;
            idx++;
        }

        for(int i=1;i<=num[sum[cur][2]];i++){
            dif[sum[cur][2]][board[idx]] += 1;
            idx++;
        }

        tmpResult += dif[sum[cur][0]][sum[cur][1]] +  dif[sum[cur][0]][sum[cur][2]];
        tmpResult += Math.max(dif[sum[cur][1]][sum[cur][2]] ,  dif[sum[cur][2]][sum[cur][1]]);

        result = Math.min(result,tmpResult);
    }

    static void print(int[] tmp){
        for(int i=1;i<=n;i++){
            System.out.print(tmp[i]+" ");
        }
        System.out.println();
    }
}
