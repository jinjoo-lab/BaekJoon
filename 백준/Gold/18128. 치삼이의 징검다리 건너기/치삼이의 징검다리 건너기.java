import java.util.*;
import java.io.*;

public class Main {

    static int[] wx = {0,0,-1,1};
    static int[] wy = {1,-1,0,0};
    static int n,m;
    static int[][] board;

    static int[][] wv;

    static Queue<int[]> water = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][n+1];
        wv = new int[n+1][n+1];

        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= n ; j++){
                wv[i][j] = -1;
            }
        }

        for(int i = 1 ; i <= m ; i++){
            st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            wv[x][y] = 0;
            water.add(new int[]{x,y});
        }

        for(int i = 1 ; i <= n ; i++){
            char[] arr = br.readLine().toCharArray();

            for(int j = 1 ; j <= n ; j++){
                board[i][j] = arr[j-1] - '0';
            }
        }

        waterGo();
        wv[n][n] = 0;
        int result = bfs();

        System.out.println(result);
        br.close();
    }

    static int[] dx = {0,0,-1,1,1,1,-1,-1};
    static int[] dy = {1,-1,0,0,1,-1,1,-1};

    static int bfs(){
        int[][] v = new int[n+1][n+1];
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= n ; j++){
                v[i][j] = -1;
            }
        }

        PriorityQueue<int[]> q = new PriorityQueue<>(
                (x,y) -> x[2] - y[2]
        );

        v[1][1] = 0;
        q.add(new int[]{1,1,0});
        int result = - 1;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(cur[0] == n && cur[1] == n){
                result = cur[2];
                break;
            }

            int curDay = cur[2];

            for(int i = 0 ; i < 8 ; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(isOut(nx,ny) || board[nx][ny] == 0)
                    continue;

                int nextDay = Math.max(cur[2],wv[nx][ny]);

                if(v[nx][ny] == -1 || v[nx][ny] > nextDay){
                    v[nx][ny] = nextDay;
                    q.add(new int[]{nx,ny,v[nx][ny]});
                }
            }
        }


        return result;
    }

    static void print(int[][] arr){
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= n ; j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void waterGo(){
        while(!water.isEmpty()){
            int[] cur = water.poll();

            for(int i = 0 ; i < 4 ; i++){
                int nx = cur[0] + wx[i];
                int ny = cur[1] + wy[i];

                if(isOut(nx,ny) || wv[nx][ny] != -1)
                    continue;

                wv[nx][ny] = wv[cur[0]][cur[1]] + 1;
                water.add(new int[]{nx,ny});
            }
        }
    }

    static boolean isOut(int x,int y){
        if(x < 1 || x > n || y < 1 || y > n)
            return true;

        return false;
    }
}
