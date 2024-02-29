
import java.util.*;
import java.io.*;

public class Solution {

    static int t = 0;
    static int n = 0;
    static int x = 0;

    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(st.nextToken());

        for(int a = 1;  a<= t ; a++){
            st = new StringTokenizer(br.readLine()," ");

            n = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());

            board = new int[n+1][n+1];

            for(int i = 1 ; i<=n ; i++){
                st = new StringTokenizer(br.readLine()," ");

                for(int j= 1 ; j<=n ; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int count = 0;

            for(int i = 1 ; i <= n ; i++){
                if(sero(i)) {
                    count += 1;
                }
            }

            for(int j = 1 ; j <= n ; j++){
                if(garo(j)){
                    count += 1;
                }
            }
            sb.append("#"+a+" "+count+"\n");
        }


        System.out.println(sb);
        br.close();
    }
    static boolean garo(int row){
        boolean[] isIt = new boolean[n+1];
        int i = row;

        for(int j = 1 ; j < n ; j++){

            int minus = Math.abs(board[i][j] - board[i ][j + 1]);

            if(minus == 0){
                continue;
            }

            else if(minus == 1){

                if(board[i][j+1] > board[i][j]){
                    int startNum = board[i][j];
                    int start = j;
                    int end = j - x + 1;

                    if(end < 1) return false;

                    for(int k = end ; k <= start ; k++){
                        if(isIt[k])
                            return false;

                        if(board[i][k] != startNum)
                            return false;

                        isIt[k] = true;
                    }
                }else if(board[i][j+1] < board[i][j]){
                    int startNum = board[i][j+1];
                    int start = j + 1;
                    int end = j + x;

                    if(end > n) return false;


                    for(int k = start ; k <= end ; k++){
                        if(isIt[k])
                            return false;

                        if(board[i][k] != startNum)
                            return false;

                        isIt[k] = true;
                    }
                }
            }

            else{
                return false;
            }
        }

        return true;
    }
    static boolean sero(int cal){
        boolean[] isIt = new boolean[n+1];
        int j = cal;

        for(int i = 1 ; i < n ; i++){

            int minus = Math.abs(board[i][j] - board[i + 1][j]);

            if(minus == 0){
                continue;
            }

            else if(minus == 1){
                if(board[i+1][j] > board[i][j]){
                    int startNum = board[i][j];
                    int start = i;
                    int end = i - x + 1;

                    if(end < 1) return false;

                    for(int k = end ; k <= start ; k++){
                        if(isIt[k])
                            return false;

                        if(board[k][j] != startNum)
                            return false;

                        isIt[k] = true;
                    }
                }else if(board[i+1][j] < board[i][j]){
                    int startNum = board[i+1][j];
                    int start = i + 1;
                    int end = i + x;

                    if(end > n) return false;


                    for(int k = start ; k <= end ; k++){
                        if(isIt[k])
                            return false;

                        if(board[k][j] != startNum)
                            return false;

                        isIt[k] = true;
                    }
                }
            }

            else{
                return false;
            }
        }

        return true;
    }
}
