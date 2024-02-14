import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
7 3
0 0 7 7
2 2 4 1
3 5 1 5
7 7 7 7 7 7 7
7 7 7 7 7 7 7
7 7 1 1 1 1 7
7 7 1 1 1 5 7
7 7 1 1 1 1 7
7 7 1 1 1 1 7
7 7 7 7 7 7 7
* */


public class Main {

    static int n = 0;
    static int m = 0;

    static int[][] board;

    static int[] dx = {0,-1,-1};
    static int[] dy = {-1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][n];

        for(int i=1 ;i <= m ;i++){
            st = new StringTokenizer(br.readLine()," ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());

            for(int r = x ; r < x + l ; r++){
                for(int c = y ; c < y + l ; c++){
                    board[r][c] = f;
                }
            }
        }

        int[][] dp;
        int rad = 0;

        for(int i = 1 ; i <= 7 ; i++){
            for(int j = 1 ; j <= 7 ; j++){
                dp = new int[n + 1][n + 1];

                for(int r = 1 ; r <= n; r++){
                    for(int c = 1 ; c <= n ; c++){

                        if(board[r-1][c-1] != i && board[r-1][c-1] != j)
                            continue;

                        dp[r][c] = Math.min(dp[r-1][c-1],Math.min(dp[r-1][c],dp[r][c-1])) + 1;

                        rad = Math.max(rad,dp[r][c]);
                    }
                }
            }
        }

        System.out.println(rad * rad);
        br.close();
    }

    static void print(){
        for(int i=0 ;i < n ;i++){
            for(int j=0; j < n ;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
