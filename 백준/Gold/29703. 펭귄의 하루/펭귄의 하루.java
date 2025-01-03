import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static char[][] board;
    static int sx,sy,hx,hy;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static int[][] fDis;
    static int[][] hDis;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n+1][m+1];

        for(int i = 1 ; i <= n ; i++) {
            char[] line = br.readLine().toCharArray();

            for(int j = 1 ; j <= m ; j++) {
                board[i][j] = line[j-1];

                if(board[i][j] == 'S') {
                    sx = i;
                    sy = j;
                }

                if(board[i][j] == 'H') {
                    hx = i;
                    hy = j;
                }
            }
        }

        findFish();
        findHome();

        int min = -1;

        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= m ; j++) {
                if(board[i][j] == 'F' && fDis[i][j] != Integer.MAX_VALUE && hDis[i][j] != Integer.MAX_VALUE) {

                    if(min == -1)
                        min = fDis[i][j] + hDis[i][j];

                    else
                        min = Math.min(min, fDis[i][j] + hDis[i][j]);
                }
            }
        }

        System.out.println(min);

        br.close();
    }

    static void findHome() {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{hx,hy});
        hDis = new int[n+1][m+1];

        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= m ; j++) {
                hDis[i][j] = Integer.MAX_VALUE;
            }
        }
        
        hDis[hx][hy] = 0;

        while(!q.isEmpty()) {
            int[] cur = q.poll();


            for(int i = 0 ; i < 4 ; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(isOut(nx,ny) || board[nx][ny] == 'D')
                    continue;

                if(hDis[nx][ny] > hDis[cur[0]][cur[1]] + 1) {
                    hDis[nx][ny] = hDis[cur[0]][cur[1]] + 1;
                    q.add(new int[]{nx,ny});
                }
            }
        }
    }

    static void findFish() {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sx,sy});
        fDis = new int[n+1][m+1];

        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= m ; j++) {
                fDis[i][j] = Integer.MAX_VALUE;
            }
        }

        fDis[sx][sy] = 0;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for(int i = 0 ; i < 4 ; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(isOut(nx,ny) || board[nx][ny] == 'D')
                    continue;

                if(fDis[nx][ny] > fDis[cur[0]][cur[1]] + 1) {
                    fDis[nx][ny] = fDis[cur[0]][cur[1]] + 1;
                    q.add(new int[]{nx,ny});
                }
            }
        }
    }

    static void print(int[][] dis) {
        for(int i = 1 ; i <= n ; i++) {
            for(int j =1 ; j <= m ; j++) {
                System.out.print(dis[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean isOut(int x,int y) {
        if(x < 1 || x > n || y < 1 || y > m)
            return true;

        return false;
    }
}
