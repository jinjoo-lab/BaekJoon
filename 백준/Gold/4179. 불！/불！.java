
import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static char[][] board;

    static Queue<Node> fireQ = new ArrayDeque<>();

    static int[][] fv;
    static int[][] jv;

    static int sx,sy;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n+1][m+1];
        fv = new int[n+1][m+1];
        jv = new int[n+1][m+1];

        for(int i = 1 ; i <= n ; i++){
            char[] arr = br.readLine().toCharArray();

            for(int j = 1 ; j <= m ; j++){
                board[i][j] = arr[j-1];

                if(board[i][j] == 'F'){
                    fv[i][j] = 1;
                    fireQ.add(new Node(i,j));
                }

                if(board[i][j] == 'J'){
                    sx = i;
                    sy = j;
                }
            }
        }

        fire();
        int result = jMove();

        if(result == -1){
            System.out.println("IMPOSSIBLE");
        }else{
            System.out.println(result);
        }

        br.close();
    }

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static int jMove(){
        jv[sx][sy] = 1;
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(sx,sy));

        int result = -1;

        loop : while(!q.isEmpty()){
            Node cur = q.poll();


            for(int i = 0 ; i < 4 ; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(isOut(nx,ny)) {
                    result = jv[cur.x][cur.y];
                    break loop;
                }

                if(board[nx][ny] == 'F' || board[nx][ny] == '#')
                    continue;

                if(jv[nx][ny] != 0)
                    continue;

                if(fv[nx][ny] > jv[cur.x][cur.y] + 1 || fv[nx][ny] == 0){
                    jv[nx][ny] = jv[cur.x][cur.y] + 1;
                    q.add(new Node(nx,ny));
                }
            }
        }

        return result;
    }

    static void fire(){
        while(!fireQ.isEmpty()){
            Node cur = fireQ.poll();

            for(int i = 0 ; i < 4 ; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(isOut(nx,ny) || board[nx][ny] =='#')
                    continue;

                if(fv[nx][ny] != 0)
                    continue;

                fv[nx][ny] = fv[cur.x][cur.y] + 1;
                fireQ.add(new Node(nx,ny));
            }
        }

    }

    static void print(int[][] v){
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= m ; j++){
                System.out.print(v[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean isOut(int x,int y){
        if(x < 1 || x > n || y < 1 || y > m)
            return true;

        return false;
    }

    static class Node{
        int x;
        int y;

        Node(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
}
