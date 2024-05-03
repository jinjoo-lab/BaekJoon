import java.util.*;
import java.io.*;

public class Main {
    static int n;

    static int[][] board;
    static int[][] sum;
    static int[][] sum2;

    static HashMap<Integer,Integer> map = new HashMap<>();


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());

        board = new int[n+1][n+1];
        sum = new int[n+1][n+1];
        sum2 = new int[n+2][n+2];

        for(int i = 1 ; i <= n ; i++){
            st = new StringTokenizer(br.readLine()," ");
            for(int j = 1 ; j <= n ; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= n ; j++){
                sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + board[i][j];
            }
        }

        for(int i = 1 ; i <= n ; i++){
            for(int j = n ; j >= 1 ; j--){
                sum2[i][j] = sum2[i-1][j] + sum2[i][j+1] - sum2[i-1][j+1] + board[i][j];
            }
        }

        int count = 0;

        for(int x = 1 ; x <= n ; x++){
            for(int y = 1 ; y <= n ; y++){
                count += findF(x,y);
                count += findS(x,y);
            }
        }


        System.out.println(count);
        br.close();
    }

    static int findF(int x,int y){
        int result = 0;
        map.clear();

        for(int sx = 1 ; sx <= x; sx++){
            for(int sy = 1 ; sy <= y ; sy++){
                int fSum = sum[x][y] - sum[sx -1][y] - sum[x][sy -1] + sum[sx-1][sy-1];

                int plus = map.containsKey(fSum) ? map.get(fSum) + 1 : 1;
                map.put(fSum,plus);
            }
        }

        for(int sx = x + 1 ;  sx <= n ; sx++){
            for(int sy = y + 1 ; sy <= n ; sy++){
                int fSum = sum[sx][sy] - sum[x][sy] - sum[sx][y] + sum[x][y];
                result += (map.get(fSum) == null ? 0 : map.get(fSum));
            }
        }

        return result;
    }

    static int findS(int x,int y){
        int result = 0;
        map.clear();

        for(int sx = x + 1 ; sx <= n ; sx++){
            for(int sy = y - 1 ; sy >= 1 ; sy--){
                int fSum = sum2[sx][sy] - sum2[x][sy] - sum2[sx][y] + sum2[x][y];

                int plus = map.containsKey(fSum) ? map.get(fSum) + 1 : 1;
                map.put(fSum,plus);
            }
        }

        for(int sx = x ; sx >= 1; sx--){
            for(int sy = y ; sy <= n ; sy++){
                int fSum = sum2[x][y] - sum2[sx -1][y] - sum2[x][sy + 1] + sum2[sx - 1][sy + 1];
                result += (map.get(fSum) == null ? 0 : map.get(fSum));
            }
        }


        return result;
    }

    static void print(int[][] sum){
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= n ; j++){
                System.out.print(sum[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
