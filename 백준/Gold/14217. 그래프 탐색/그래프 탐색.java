
import java.io.*;
import java.util.*;

public class Main {
    static int n = 0,m = 0;
    static int[][] board;

    static int[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][n+1];


        for(int i = 1 ; i<= m ; i++){
            st = new StringTokenizer(br.readLine()," ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            board[x][y] = 1;
            board[y][x] = 1;
        }

        st = new StringTokenizer(br.readLine()," ");
        int k = Integer.parseInt(st.nextToken());

        for(int i = 1 ; i<= k ;i++){
            st = new StringTokenizer(br.readLine()," ");
            int w = Integer.parseInt(st.nextToken());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            if(w == 1){
                board[v1][v2] = 1;
                board[v2][v1] = 1;
            }else{
                board[v1][v2] = 0;
                board[v2][v1] = 0;
            }
            bfs();
        }

        br.close();
    }

    static void bfs(){
        Queue<Integer> q = new ArrayDeque<>();
        v = new int[n+1];
        q.add(1);
        v[1] = 1;

        while(!q.isEmpty()){
            int cur = q.poll();

            for(int i = 1 ; i <= n ; i++){
                if(board[cur][i] == 1 && v[i] == 0){
                    v[i] = v[cur] + 1;
                    q.add(i);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1 ; i <=n ;i++){
            sb.append(v[i]-1+" ");
        }sb.append("\n");

        System.out.print(sb);
    }
}
