import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine()) != null){
            n = Integer.parseInt(line);

            int sum = 0;
            int[][] board = new int[n+1][2];
            for(int i=1;i<=n;i++){
                StringTokenizer st = new StringTokenizer(br.readLine()," ");
                board[i][0] = Integer.parseInt(st.nextToken());
                board[i][1] = Integer.parseInt(st.nextToken());

                sum += (board[i][0] * board[i][1]);
            }

            int[] dp = new int[50001];
            dp[0] = 1;

            if(sum%2 == 1){
                sb.append(0+"\n");
                continue;
            }

            for(int i=1;i<=n;i++){
                for(int j=sum/2;j>=board[i][0];j--){
                    if(j - board[i][0] >= 0){
                        if(dp[j - board[i][0]] == 1){
                            int idx = 1;

                            for(int k=0;k<=board[i][1];k++){
                                if(j + k*board[i][0] <= sum/2){
                                    dp[j+ k*board[i][0]] = 1;
                                }
                            }
                        }

                    }
                }
            }

            if(dp[sum/2] == 1){
                sb.append(1+"\n");
            }
            else{
                sb.append(0+"\n");
            }
        }

        System.out.print(sb);
        br.close();
    }
}
