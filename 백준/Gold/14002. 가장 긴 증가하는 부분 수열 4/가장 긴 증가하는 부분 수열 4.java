import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int[] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        board = new int[n+1];
        int[] past = new int[n+1];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<=n;i++)
        {
            board[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n+1];
        dp[1] = 1;
        past[1] = 1;

        int maxL = 1;
        for(int i=2;i<=n;i++){
            int max = 1;
            int idx = i;

            for(int j=i-1;j>=1;j--){

                if(board[i] > board[j])
                {
                    if(dp[j]+1 > max){
                        idx = j;
                    }
                    max = Math.max(max , dp[j] + 1);
                }

            }
            past[i] = idx;
            dp[i] = max;
            maxL = Math.max(maxL,dp[i]);
        }


        System.out.println(maxL);

        int maxIdx = 1;

        for(int i=1;i<=n;i++){
            if(dp[maxIdx] < dp[i]){
                maxIdx = i;
            }
        }
        Stack<Integer> stack = new Stack();
        while(true){
            stack.push(board[maxIdx]);

            if(maxIdx == past[maxIdx])
                break;

            maxIdx = past[maxIdx];

        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop()+" ");
        }
        sb.append("\n");
        System.out.println(sb);
        br.close();
    }
}