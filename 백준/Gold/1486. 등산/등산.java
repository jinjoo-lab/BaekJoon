import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;
    static int m = 0;
    static int k = 0;
    static int d = 0;

    static int[][] board;

    static int[][] tmpDis;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        board = new int[n + 1][m + 1];
        tmpDis = new int[n+1][m+1];

        for(int i = 0 ; i < n ; i++){
            char[] arr = br.readLine().toCharArray();

            for(int j = 0 ; j < m ; j++){
                if(arr[j] >= 'A' && arr[j] <= 'Z'){
                    board[i][j] = arr[j] - 'A';
                }

                else{
                    board[i][j] = arr[j] - 'a' + 26;
                }
            }
        }

        bfs();
        bfs2();

        int result = board[0][0];

        for(int i = 0 ; i <  n ; i++){
            for(int j = 0; j < m ; j++){

                if(tmpDis[i][j] <= d){
                    result = Math.max(result,board[i][j]);
                }
            }
        }

        System.out.println(result);
        br.close();
    }

    static void bfs(){
        int result = 0;
        Queue<int[]> q = new ArrayDeque<>();
        int[][] dis = new int[n][m];
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                dis[i][j] = d + 1;
            }
        }

        dis[0][0] = 0;
        q.add(new int[]{0,0,0});

        while(!q.isEmpty()){
            int[] cur = q.poll();

            if(dis[cur[0]][cur[1]] < cur[2])
                continue;

            dis[cur[0]][cur[1]] = cur[2];

            for(int i = 0 ; i < 4 ; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;

                if(Math.abs(board[nx][ny] - board[cur[0]][cur[1]]) > k)
                    continue;

                if(board[nx][ny] > board[cur[0]][cur[1]]){
                    int tmpDis = Math.abs(board[nx][ny] - board[cur[0]][cur[1]]);
                    tmpDis = tmpDis * tmpDis;

                    int next = dis[cur[0]][cur[1]] + tmpDis;

                    if(next > d)
                        continue;

                    if(dis[nx][ny] > next){
                        dis[nx][ny] = next;
                        q.add(new int[]{nx,ny,dis[nx][ny]});
                    }

                }else{

                    if(dis[cur[0]][cur[1]] + 1 > d)
                        continue;

                    if(dis[nx][ny] > dis[cur[0]][cur[1]] + 1) {
                        dis[nx][ny] = dis[cur[0]][cur[1]] + 1;
                        q.add(new int[]{nx,ny,dis[nx][ny]});
                    }
                }
            }
        }

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                tmpDis[i][j] += dis[i][j];
            }
        }
    }

    static void bfs2(){
        int result = 0;

        Queue<int[]> q = new ArrayDeque<>();
        int[][] dis = new int[n][m];
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                dis[i][j] = d + 1;
            }
        }

        dis[0][0] = 0;
        q.add(new int[]{0,0,0});

        while(!q.isEmpty()){
            int[] cur = q.poll();

            if(dis[cur[0]][cur[1]] < cur[2])
                continue;

            dis[cur[0]][cur[1]] = cur[2];

            for(int i = 0 ; i < 4 ; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;

                if(Math.abs(board[nx][ny] - board[cur[0]][cur[1]]) > k)
                    continue;

                if(board[nx][ny] < board[cur[0]][cur[1]]){
                    int tmpDis = Math.abs(board[nx][ny] - board[cur[0]][cur[1]]);
                    tmpDis = tmpDis * tmpDis;

                    int next = dis[cur[0]][cur[1]] + tmpDis;

                    if(next > d)
                        continue;

                    if(dis[nx][ny] > next){
                        dis[nx][ny] = next;
                        q.add(new int[]{nx,ny,dis[nx][ny]});
                    }

                }else{

                    if(dis[cur[0]][cur[1]] + 1 > d)
                        continue;

                    if(dis[nx][ny] > dis[cur[0]][cur[1]] + 1) {
                        dis[nx][ny] = dis[cur[0]][cur[1]] + 1;
                        q.add(new int[]{nx,ny,dis[nx][ny]});
                    }
                }
            }
        }

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                tmpDis[i][j] += dis[i][j];
            }
        }
    }
}
