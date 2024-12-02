import java.util.*;
import java.io.*;

public class Main {

    static int n,m,t;
    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];

        for(int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            for(int j = 0 ; j < m ; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1 ; i <= t ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());


            turn(x,d,k);
        }

        int sum = 0;
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                sum += board[i][j];
            }
        }

        System.out.println(sum);

        br.close();
    }

    static void turn(int x,int d,int k) {
        go(x,d,k);
        erase();
    }
    static void erase() {
        boolean find = false;
        boolean[][] v = new boolean[n+1][m+1];

        int sum = 0;
        int count = 0;

        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {

                if(board[i][j] == 0)
                    continue;

                sum += board[i][j];
                count += 1;

                int v1 = j - 1 < 0 ? m - 1 : j - 1;
                int v2 = j + 1 == m ? 0 : j + 1;

                if(board[i][j] == board[i][v1] || board[i][j] == board[i][v2]) {
                    v[i][j] = true;
                    find = true;
                }

                if(i + 1 < n && board[i][j] == board[i+1][j]) {
                    v[i][j] = true;
                    find = true;
                }

                if(i - 1 >= 0 && board[i][j] == board[i-1][j]) {
                    v[i][j] = true;
                    find = true;
                }
            }
        }

        if(find) {
            for(int i = 0 ; i < n ; i++) {
                for(int j = 0 ; j < m ; j++) {
                    if(v[i][j]) {
                        board[i][j] = 0;
                    }
                }
            }
        }else {
            if(count != 0) {
                double tt = (double) sum / count;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (board[i][j] == 0)
                            continue;

                        if ((double)board[i][j] > tt)
                            board[i][j] -= 1;
                        else if ((double)board[i][j] < tt)
                            board[i][j] += 1;
                    }
                }
            }
        }
    }

    static void go(int x, int d, int k) {
        int[][] tmp = new int[n+1][m+1];

        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                tmp[i][j] = board[i][j];
            }
        }

        for(int i = x - 1; i < n ; i+=x) {
            if(d == 0) {
                for(int j = 0 ; j < m ; j++) {
                    int next = (j + k) % m;
                    tmp[i][next] = board[i][j];
                }
            }else {
                int tt = m - k%m;
                for(int j = 0 ; j < m ; j++) {
                    int next = (j + tt) % m;
                    tmp[i][next] = board[i][j];
                }
            }
        }

        board = tmp;
    }

    static void print(int[][] board) {
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
