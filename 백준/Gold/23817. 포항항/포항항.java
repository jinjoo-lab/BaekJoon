import java.util.*;
import java.io.*;

public class Main {

    static int result = -1;
    static int n,m;
    static int[][] board;

    static ArrayList<int[]> Points = new ArrayList<>();

    static int[][] dis = new int[22][22];

    static boolean[] v = new boolean[22];
    static int count = 2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];


        for(int i = 1 ; i <= n ; i++){
            char[] arr = br.readLine().toCharArray();

            for(int j = 1 ; j <= m ; j++){
                char tmpB = arr[j-1];

                if(tmpB == 'K'){
                    board[i][j] = count;
                    Points.add(new int[]{i,j});
                    count++;
                }else if(tmpB == 'X'){
                    board[i][j] = -1;
                }else if(tmpB == 'S'){
                    board[i][j] = 1;
                    Points.add(new int[]{i,j});
                }
            }
        }

        for(int[] curP : Points){
            bfs(curP[0],curP[1]);
        }


        v[1] = true;
        per(1,0,0);

        System.out.println(result);

        br.close();
    }

    static void printDis(){
        for(int i = 1 ; i < count ; i ++){
            for(int j = 1 ; j < count ; j++){
                System.out.print(dis[i][j]+" ");
            }
            System.out.println();
        }
    }

    static void bfs(int x,int y){
        boolean[][] v = new boolean[n+1][m+1];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x,y,0});
        v[x][y] = true;
        int curNum = board[x][y];

        while(!q.isEmpty()){
            int[] cur = q.poll();

            for(int i = 0 ; i < 4 ; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(isOut(nx,ny) || v[nx][ny])
                    continue;

                v[nx][ny] = true;

                if(board[nx][ny] == 0){
                    q.add(new int[]{nx,ny,cur[2] + 1});
                }else{
                    int nextNum = board[nx][ny];
                    dis[curNum][nextNum] = cur[2] + 1;
                    q.add(new int[]{nx,ny,cur[2] + 1});
                }
            }
        }
    }

    static void per(int idx,int depth,int curCount){

        if(depth == 5){
            if(result == -1){
                result = curCount;
            }else{
                result = Math.min(result,curCount);
            }
            return;
        }

        for(int i = 1 ; i < count ; i++){

            if(dis[idx][i] == 0)
                continue;

            if(!v[i]){
                v[i] = true;
                per(i,depth + 1,curCount + dis[idx][i]);
                v[i] = false;
            }
        }
    }


    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static boolean isOut(int x,int y){
        if(x < 1 || x > n || y < 1 || y > m)
            return true;

        if(board[x][y] == -1)
            return true;

        return false;
    }

}
