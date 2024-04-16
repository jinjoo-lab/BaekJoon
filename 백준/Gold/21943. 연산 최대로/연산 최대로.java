import java.util.*;
import java.io.*;

public class Main {
    static int[] op;
    static int n;
    static int[] board;

    static boolean[] v;
    static int[] perBoard;
    static int p,q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        board = new int[n+1];
        perBoard = new int[n+n+1];
        v = new boolean[n+1];
        op = new int[n+1];

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 1 ; i <= n ; i++){
            board[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine()," ");
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        per(1);
        System.out.println(result);
        br.close();
    }
    static int cal(int v1,int v2,int op){
        if(op == 1)
            return v1 + v2;

        return v1 * v2;
    }

    static int calAll(){
        int len = 2*n - 1;
        int[][] dp = new int[len + 1][len + 1];

        for(int i = 1 ; i <= len ; i+=2){
            dp[i][i] = perBoard[i];
        }

        for(int i = 1 ; i < len ; i+=2){
            dp[i][i+2] = cal(perBoard[i],perBoard[i+2],perBoard[i+1]);
        }

        for(int k = 4; k <= len ; k+= 2){
            for(int i = 1 ; i <= len - k ; i+= 2){

                if(i + k > len)
                    continue;

                for(int j = i ; j < i+k; j+= 2){
                    dp[i][i+k] = Math.max(dp[i][i+k],cal(dp[i][j],dp[j+2][i+k],perBoard[j+1]));
                }
            }
        }

        return dp[1][len];
    }

    static int calTem(){

        int tmpResult = perBoard[1];

        for(int i = 3 ; i <= 2*n -1 ; i+=2){
            tmpResult = cal(tmpResult,perBoard[i],perBoard[i-1]);
        }

        return tmpResult;
    }

    static int result = 0;
    static void comb(int depth,int cnt){
        if(depth > 2*n - 2) {
            if (cnt == p) {
                int num = perBoard[1];
                int tmpResult = 1;

                for(int i = 2 ; i <= 2*n - 2 ; i+=2){
                    if(perBoard[i] == 0){
                        tmpResult *= num;
                        num = perBoard[i+1];
                    }else{
                        num += perBoard[i+1];
                    }
                }

                tmpResult *= num;
                result = Math.max(result,tmpResult);
            }
            return;
        }

        perBoard[depth] = 1;
        comb(depth + 2,cnt + 1);
        perBoard[depth] = 0;
        comb(depth + 2, cnt);

    }

    static void per(int idx){
        if(idx > 2*n - 1){
            comb(2,0);
            return;
        }

        for(int i = 1 ; i <= n ; i++){
            if(!v[i]){
                v[i] = true;
                perBoard[idx] = board[i];
                per(idx + 2);
                v[i] = false;
            }
        }
    }
}
