import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];

        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            for(int j = 1 ; j <= m ; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;

        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= m ; j++) {
                if(board[i][j] == 1) {
                    find(i,j);
                    //print();
                    count++;
                }
            }
        }

        System.out.println(count);

        br.close();
    }

    static void print() {
        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= m ; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void find(int x,int y) {

        board[x][y] = 0;

        int last = y;

        for(int j = y + 1 ; j <= m ; j++) {
            if(board[x][j] == 1) {
                board[x][j] = 0;
                last = j;
            }
        }

        for(int i = x + 1 ; i <= n ; i++) {
            for(int j = last ; j <= m ; j++) {
                if(board[i][j] == 1) {
                    board[i][j] = 0;
                    last = j;
                }
            }
        }
    }
}
