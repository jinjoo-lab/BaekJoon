
import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n = 0;
    static int[][] board;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int idx = 1;

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");

            n = Integer.parseInt(st.nextToken());

            if(n == 0)
                break;

            board = new int[n+1][n+1];

            for(int i = 1 ; i <= n ; i++){
                st = new StringTokenizer(br.readLine()," ");
                for(int j = 1 ; j <= n ; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int tmpResult = go();

            sb.append("Problem "+idx+": "+tmpResult+"\n");
            idx++;
        }

        System.out.print(sb);

        br.close();
    }

    static int go(){

        int result = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (x,y) -> x[2] - y[2]
        );

        pq.add(new int[]{1,1,board[1][1]});

        int[][] dis = new int[n+1][n+1];
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j<= n ; j++){
                dis[i][j] = Integer.MAX_VALUE;
            }
        }

        dis[1][1] = board[1][1];

        while(!pq.isEmpty()){
            int[] cur = pq.poll();

            if(cur[0] == n && cur[1] == n){
                result = cur[2];
                break;
            }

            for(int i = 0 ; i < 4 ; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > n)
                    continue;

                if(dis[nx][ny] > dis[cur[0]][cur[1]] + board[nx][ny]){
                    dis[nx][ny] = dis[cur[0]][cur[1]] + board[nx][ny];
                    pq.add(new int[]{nx,ny,dis[nx][ny]});
                }
            }
        }

        return result;
    }
}
