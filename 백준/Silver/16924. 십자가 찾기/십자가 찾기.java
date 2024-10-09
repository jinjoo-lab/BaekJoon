import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];

        for(int i = 1 ; i <= n ; i++) {
            char[] tmp = br.readLine().toCharArray();

            for(int j = 1 ; j <= m ; j++) {
                if(tmp[j-1] == '*') {
                    board[i][j] = 1;
                }
            }
        }

        boolean[][] v = new boolean[n+1][m+1];
        Queue<int[]> q = new ArrayDeque<>();

        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= m ; j++) {

                if(board[i][j] == 1) {
                    int tmpSize = getSize(i,j);

                    if(tmpSize >= 1) {
                        q.add(new int[]{i,j,tmpSize});
                    }

                    for(int k = 1; k <= tmpSize ; k++) {

                        v[i][j] = true;

                        for(int a = 0; a < 4 ; a++) {
                            int nx = i + dx[a] * k;
                            int ny = j + dy[a] * k;

                            v[nx][ny] = true;
                        }
                    }
                }
            }
        }

        boolean re = true;

        loop : for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= m ; j++) {
                if(board[i][j] == 1) {
                    if(!v[i][j]) {
                        re = false;
                        break loop;
                    }
                }
            }
        }

        if(re) {
            System.out.println(q.size());
            for(int[] next : q) {
                System.out.println(next[0]+" "+next[1]+" "+next[2]);
            }
        }else {
            System.out.println(-1);
        }

        br.close();
    }

    static int getSize(int sx,int sy) {
        int idx = 0;

        loop : while(true) {
            for (int i = 0; i < 4; i++) {
                int nx = sx + dx[i] * (idx + 1);
                int ny = sy + dy[i] * (idx + 1);

                if (isOut(nx, ny) || board[nx][ny] == 0) {
                    break loop;
                }
            }

            idx+= 1;
        }

        return idx;
    }


    static boolean isOut(int x,int y) {
        if(x < 1 || x > n || y < 1 || y > m)
            return true;

        return false;
    }
}
