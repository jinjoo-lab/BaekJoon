import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        board = new int[n+1][n+1];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine(), " ");

            for(int j=1;j<=n;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        search();

        br.close();
    }

    static void search(){
        int[][] dis = new int[n+1][n+1];

        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(board[i][j] == 1)
                    dis[i][j] = 1;

            }
        }

        for(int k=1;k<=n;k++){
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    if(dis[i][k] == 1 && dis[k][j] == 1){
                        dis[i][j] = 1;
                    }
                }
            }
        }

        for(int i= 1;i<=n;i++){
            for(int j=1;j<=n;j++){
                System.out.print(dis[i][j]+ " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}