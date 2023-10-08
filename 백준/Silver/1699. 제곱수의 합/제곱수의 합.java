import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[] dp;

    static ArrayList<Integer> pow = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());

        dp = new int[n + 1];
        dp[1] = 1;
        pow.add(1);

        for(int i=2;i<=n;i++){
            if(isIt(i)){
                dp[i] = 1;
                pow.add(i);
            }

            else{
                int idx = 0;

                for(int j : pow){
                    if(dp[j] == 1){
                        idx = j;

                        if(dp[i] == 0)
                            dp[i] = dp[idx] + dp[i - idx];

                        else{
                            dp[i] = Math.min(dp[i], dp[idx] + dp[i - idx]);
                        }
                    }
                }

            }

        }

        System.out.println(dp[n]);
        br.close();
    }

    static boolean isIt(int cur){
        double sqrt = Math.sqrt((double)cur);
        int sqrtInt = (int)sqrt;

        if(sqrt == sqrtInt)
            return true;

        return false;
    }
}
