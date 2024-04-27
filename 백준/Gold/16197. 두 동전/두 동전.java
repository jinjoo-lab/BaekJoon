import java.util.*;
import java.io.*;

public class Main {

    static int n,m;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static char[][] board;

    static boolean[][][][] v;
    static int rx,ry,bx,by;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n+1][m+1];
        v = new boolean[n+1][m+1][n+1][m+1];

        for(int i = 1 ; i <= n ; i++){
            char[] arr = br.readLine().toCharArray();

            for(int j = 1 ;  j <= m ; j++){
                board[i][j] = arr[j-1];

                if(board[i][j] == 'o'){
                    if(rx == 0){
                        rx = i;
                        ry = j;
                    }else{
                        bx = i;
                        by = j;
                    }
                }
            }
        }

        System.out.println(bfs());


        br.close();
    }

    static int bfs(){
        v[rx][ry][bx][by] = true;
        v[bx][by][rx][ry] = true;

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(rx,ry,bx,by,0));

        while(!q.isEmpty()){
            Node cur = q.poll();

            if(cur.c >= 10)
                continue;

            for(int i = 0 ; i < 4 ; i++){
                int nrx = cur.rx + dx[i];
                int nry = cur.ry + dy[i];
                int nbx = cur.bx + dx[i];
                int nby = cur.by + dy[i];

                boolean redOut = isOut(nrx,nry);
                boolean blueOut = isOut(nbx,nby);

                if(redOut && blueOut){
                    continue;
                }

                if(redOut || blueOut){
                    return cur.c + 1;
                }

                if(board[nrx][nry] == '#'){
                    nrx -= dx[i];
                    nry -= dy[i];
                }

                if(board[nbx][nby] == '#'){
                    nbx -= dx[i];
                    nby -= dy[i];
                }

                if(nrx == nbx && nry == nby){
                    nbx -= dx[i];
                    nby -= dy[i];
                }

                if(!v[nbx][nby][nrx][nry]){
                    v[nbx][nby][nrx][nry] = true;
                    v[nrx][nry][nbx][nby] = true;
                    q.add(new Node(nbx,nby,nrx,nry,cur.c+1));
                }

            }
        }

        return -1;
    }

    static boolean isOut(int x,int y){
        if(x < 1 || x > n || y < 1 || y > m)
            return true;

        return false;
    }

    static class Node{
        int rx;
        int ry;
        int bx;
        int by;

        int c;

        Node(int rx,int ry,int bx,int by,int c){
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.c = c;
        }
    }
}
