import java.io.*;
import java.util.*;

public class Main {
    static int[][] board;
    static int n = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n + 1][n + 1];
        int[][] adj = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                adj[i][j] = board[i][j];
            }
        }

        for (int k = 1; k <= n; k++) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                    if(i == k || k == j)
                        continue;

                    if (board[i][j] == board[i][k] + board[k][j]) {
                        adj[i][j] = 0;
                    }

                    else if(board[i][j] > board[i][k] + board[k][j])
                    {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        int result = 0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                result += adj[i][j];
            }
        }
        System.out.println(result / 2);

        br.close();
    }

    static void print(int[][] adj){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                System.out.print(adj[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
