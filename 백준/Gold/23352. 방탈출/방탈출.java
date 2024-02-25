import java.io.*;
import java.util.*;

public class Main {
    static int n = 0,m = 0;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static int[][] board;
    static int[][] v;

    static int maxD = -1;
    static int maxV  = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];

        for(int i = 1 ; i<=n ; i++){
            st = new StringTokenizer(br.readLine()," ");

            for(int j=1 ;j <= m ;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1 ; i <= n ; i++){
            for(int j=1 ;j <=m;j++){
                if(board[i][j] >= 1){
                    bfs(i,j);
                }
            }
        }
        System.out.println(maxV);

        br.close();
    }

    static void bfs(int x,int y){

        v = new int[n+1][m+1];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y});

        v[x][y] = 1;
        int maxDis = 0;
        int maxValue = 0;

        while(!q.isEmpty()){
            int[] cur = q.poll();

            if(maxDis < v[cur[0]][cur[1]]){
                maxDis = v[cur[0]][cur[1]];
                maxValue = board[cur[0]][cur[1]];
            }else if(maxDis == v[cur[0]][cur[1]]){
                maxValue = Math.max(maxValue,board[cur[0]][cur[1]]);
            }

            for(int i = 0 ; i < 4 ; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx < 1 || ny < 1 || nx > n || ny > m)
                    continue;

                if(board[nx][ny] == 0)
                    continue;

                if(v[nx][ny] == 0){
                    v[nx][ny] = v[cur[0]][cur[1]] + 1;
                    q.add(new int[]{nx,ny});
                }
            }
        }

        if(maxD < maxDis){
            maxD = maxDis;
            maxV = board[x][y] + maxValue;
        }else if(maxD == maxDis){
            maxV = Math.max(maxV, board[x][y] + maxValue);
        }

    }
    
}