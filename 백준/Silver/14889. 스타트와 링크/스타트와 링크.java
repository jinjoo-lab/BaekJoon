import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int[][] board;

    static int[] visit;

    static int result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        board = new int[n+1][n+1];
        visit = new int[n+1];

        for(int i=1;i<=n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");

            for(int j=1;j<=n;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divide(1,n,n/2);
        System.out.println(result);
        br.close();
    }
    static void print(){
        for(int i=1;i<=n;i++){
            System.out.print(visit[i]+" ");
        }
        System.out.println();
    }
    static void divide(int start,int n,int r){
        if(r == 0){
            int tmp = score();

            if(result == -1)
                result = tmp;

            else{
                result = Math.min(result, tmp);
            }

            return ;
        }

        for(int i=start;i<=n;i++){
            if(visit[i] == 0){
                visit[i] = 1;
                divide(i+1,n,r-1);
                visit[i] = 0;
            }
        }
    }

    static int score(){
        int one = 0;
        int two = 0;

        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(visit[i] == 1 && visit[j] == 1)
                    one += board[i][j];

                if(visit[i] == 0 && visit[j] == 0)
                    two += board[i][j];
            }
        }

        return Math.abs(one - two);
    }


}