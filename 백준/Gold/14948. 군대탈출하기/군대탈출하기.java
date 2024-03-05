
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

import java.util.StringTokenizer;

public class Main {
    static int n = 0;
    static int m = 0;
    static int[][] board;

    static int result;
    static int max;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];

        for(int i = 1 ; i <= n ; i++){
            st = new StringTokenizer(br.readLine()," ");

            for(int j = 1 ; j <= m ; j++){
                board[i][j] = Integer.parseInt(st.nextToken());

                max = Math.max(max,board[i][j]);
                min = Math.min(min,board[i][j]);
            }
        }

        result = max;

        bs();
        System.out.println(result);

        br.close();
    }

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static class Node{
        int x;
        int y;

        int l;
        boolean isBreak;

        Node(int x,int y,int l,boolean isBreak){
            this.x = x;
            this.y = y;
            this.l = l;
            this.isBreak = isBreak;
        }
    }
    static boolean search(int pos){

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> x.l - y.l
        );

        pq.add(new Node(1,1,board[1][1],false));
        int[][][] dis = new int[n+1][m+1][2];

        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= m ; j++){
                for(int k = 0 ; k < 2 ; k++){
                    dis[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        dis[1][1][0] = board[1][1];

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(cur.x == n && cur.y == m){
                result = Math.min(result,dis[n][m][cur.isBreak ? 1 : 0]);
                return true;
            }

            int tmp = cur.isBreak ? 1 : 0;

            for(int i = 0 ; i < 4 ; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(isOut(nx,ny))
                    continue;

                if(board[nx][ny] > pos){
                    if(cur.isBreak)
                        continue;

                    boolean tmpBreak = true;
                    int n2x = nx + dx[i];
                    int n2y = ny + dy[i];

                    if(isOut(n2x,n2y))
                        continue;

                    if(board[n2x][n2y] > pos)
                        continue;

                    if(dis[nx][ny][1] > cur.l) {
                        int secondMax = Math.max(board[n2x][n2y], cur.l);

                        if (dis[n2x][n2y][1] > secondMax) {
                            dis[nx][ny][1] = cur.l;
                            dis[n2x][n2y][1] = secondMax;
                            pq.add(new Node(n2x, n2y, secondMax, tmpBreak));
                        }
                    }

                }else{
                    int maxV = Math.max(cur.l,board[nx][ny]);

                    if(dis[nx][ny][tmp] > maxV){
                        dis[nx][ny][tmp] = maxV;
                        pq.add(new Node(nx,ny,maxV,cur.isBreak));
                    }
                }
            }
        }

        return false;
    }

    static boolean isOut(int x,int y){
        if(x < 1 || x > n || y < 1 || y > m)
            return true;

        return false;
    }

    static void print(int[][] dis) {
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= m ; j++){
                System.out.print(dis[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    static void bs(){
        int l = min;
        int r = max;

        while(l <= r){
            int mid = (l + r) / 2;

            if(search(mid)){
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
    }

}
