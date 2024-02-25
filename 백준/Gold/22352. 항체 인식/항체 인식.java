import java.io.*;
import java.util.*;

public class Main {
    static int n = 0,m = 0;
    static int[][] board;
    static int[][] result;

    static boolean[][] v;

    static boolean shot = false;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        v = new boolean[n+1][m+1];
        board = new int[n+1][m+1];
        result = new int[n+1][m+1];

        for(int i = 1 ; i <= n ; i++){
            st = new StringTokenizer(br.readLine()," ");

            for(int j = 1 ; j <= m ; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1 ; i <= n ; i++){
            st = new StringTokenizer(br.readLine()," ");

            for(int j = 1 ; j <= m ; j++){
                result[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean tmp = true;

        loop : for(int i=1 ; i<=n ; i++){
            for(int j=1;j<=m;j++){
                if(!v[i][j]){
                   tmp =  bfs(i,j);
                   if(tmp == false){
                       break loop;
                   }
                }
            }
        }

        if(tmp){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }

        br.close();
    }

    static boolean bfs(int x,int y){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y});
        v[x][y] = true;
        boolean change = false;

        if(board[x][y] != result[x][y]){
            change = true;
        }

        while(!q.isEmpty()){
            int[] cur = q.poll();

            for(int i=0;i<4;i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx < 1 || ny < 1 || nx > n || ny > m)
                    continue;

                if(v[nx][ny])
                    continue;

                if(board[nx][ny] == board[x][y]){
                    v[nx][ny] = true;

                    if(result[nx][ny] == result[x][y]){

                        if(result[nx][ny] != board[nx][ny]){
                            change = true;
                        }

                        q.add(new int[]{nx,ny});
                    }

                    else{
                        return false;
                    }
                }
            }
        }

        if(change){
            if(shot)
                return false;
            
            shot = change;
        }

        return true;
    }

}
