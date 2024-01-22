import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int[][] board;

    static boolean[]v;

    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        board = new int[n+1][n+1];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(bf.readLine(), " ");

            for(int j=1;j<=n;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        v = new boolean[n+1];

        for(int i=1;i < (1 << n); i ++){
            v = new boolean[n+1];

            for(int k = 0; k < n; k ++){
                if((i & (1 << k)) != 0){
                    v[k+1] = true;
                }
            }

            result = Math.min(result,make());
        }

        System.out.println(result);
        bf.close();
    }

    static int make(){
        int sum1 = 0;
        int sum2 = 0;

        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(i == j)
                    continue;

                if(v[i] && v[j]){
                    sum1 += board[i][j];
                }

                else if(!v[i] && !v[j]){
                    sum2 += board[i][j];
                }
            }
        }

        return Math.abs(sum1 - sum2);
    }

    static void print(){
        for(int i=1;i<=n;i++){
            if(v[i])
                System.out.print(1+" ");
            else
                System.out.print(0+" ");
        }
        System.out.println();
    }
}


