import java.util.*;
import java.io.*;

public class Main {

    static int tt;
    static int n,m;
    static int[][] board;
    static int[] rank;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        StringBuilder sb = new StringBuilder();

        tt = Integer.parseInt(st.nextToken());

        for(int t = 1 ; t <= tt ; t++) {
            st = new StringTokenizer(br.readLine()," ");
            n = Integer.parseInt(st.nextToken());

            board = new int[n+1][n+1];
            rank = new int[501];

            st = new StringTokenizer(br.readLine()," ");

            for(int i = 1 ; i <= n ; i++) {
                int tmp = Integer.parseInt(st.nextToken());

                rank[i] = tmp;
            }

            for(int i = 1 ; i <= n ; i++) {
                int tmp = rank[i];

                for(int j = 1 ; j <= n ; j++) {
                    if(j == tmp || board[j][tmp] == 1)
                        continue;

                    board[tmp][j] = 1;
                }
            }

            st = new StringTokenizer(br.readLine()," ");
            m = Integer.parseInt(st.nextToken());

            for(int i = 1 ; i <= m ; i++) {
                st = new StringTokenizer(br.readLine()," ");

                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());

                if(board[v1][v2] == 1) {
                    board[v1][v2] = 0;
                    board[v2][v1] = 1;
                }else if(board[v2][v1] == 1) {
                    board[v2][v1] = 0;
                    board[v1][v2] = 1;
                }
            }

            sb.append(makeResult());
        }

        System.out.print(sb);

        br.close();
    }

    static String makeResult() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (x,y) -> y[1] - x[1]
        );

        boolean[] isIt = new boolean[n+1];

        for(int i = 1 ; i <= n ; i++) {
            int count = 0;

            for(int j = 1 ; j <= n ; j++) {

                if(i == j || board[j][i] == 1)
                    continue;

                if(board[i][j] == 1)
                    count++;
            }

            if(isIt[count]) {
                return "IMPOSSIBLE\n";
            }

            isIt[count] =  true;

            pq.add(new int[]{i,count});
        }

        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()) {
            sb.append(pq.poll()[0]+" ");
        }sb.append("\n");

        return sb.toString();
    }

    static void print() {
        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
