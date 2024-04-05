import java.util.*;
import java.io.*;

public class Main {

    static int n,h,d,sx,sy;
    static char[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        board = new char[n+1][n+1];

        for(int i = 1 ; i <= n ; i++){
            char[] arr = br.readLine().toCharArray();

            for(int j = 1 ; j <= n ; j++){
                board[i][j] = arr[j-1];

                if(board[i][j] == 'S'){
                    sx = i;
                    sy = j;
                }
            }
        }

        int result = search();
        System.out.println(result);
        br.close();
    }

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static int search(){

        int result = -1;

        int[][] v = new int[n+1][n+1];

        v[sx][sy]= h;

        Queue<Point> pq = new ArrayDeque<>();

        pq.add(new Point(sx,sy,h,0,0));

        loop : while(!pq.isEmpty()) {
            Point cur = pq.poll();

            for(int i = 0 ; i < 4 ; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(isOut(nx,ny))
                    continue;

                if(board[nx][ny] == 'E') {
                    result = cur.c + 1;
                    break loop;
                }

                if(board[nx][ny] == '.' || board[nx][ny] == 'S'){

                    int tmpV = cur.h + cur.d - 1;

                    if(tmpV == 0){
                        continue;
                    }

                    if(v[nx][ny] < tmpV){
                        v[nx][ny] = tmpV;
                        int tmpD = cur.d - 1 ;
                        int tmpH = tmpD < 0 ? cur.h - 1 : cur.h;

                        if(tmpD < 0)
                            tmpD = 0;

                        if(tmpH == 0)
                            continue;

                        pq.add(new Point(nx,ny,tmpH,tmpD,cur.c + 1));
                    }

                }else if(board[nx][ny] == 'U'){

                    int tmpV = cur.h + d - 1;

                    if(tmpV == 0){
                        continue;
                    }

                    if(v[nx][ny] < tmpV){
                        v[nx][ny] = tmpV;

                        int tmpD = d - 1;
                        int tmpH = cur.h;

                        pq.add(new Point(nx,ny,tmpH,tmpD,cur.c + 1));
                    }
                }
            }
        }

        return result;
    }

    static boolean isOut(int x,int y){
        if(x < 1 || x > n || y < 1 || y > n)
            return true;

        return false;
    }

    static class Point{
        int x;
        int y;
        int h;
        int d;

        int c;

        Point(int x,int y,int h,int d,int c){
            this.x = x;
            this.y = y;
            this.h = h;
            this.d = d;
            this.c = c;
        }
    }
}