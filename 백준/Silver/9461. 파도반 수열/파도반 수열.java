import java.io.*;

public class Main {
    static int n = 0;
    static long[] dp = new long[101];
    public static void main(String[] args) throws IOException {
        dp[1] = 1; dp[2] = 1; dp[3] = 1;
        dp[4] = 2; dp[5] = 2;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        for(int k=1;k<=n;k++)
        {
            int num = Integer.parseInt(br.readLine());
            long result = find(num);
            bw.write(result+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static long find(int num)
    {
        if(num<=5)
        {
            return dp[num];
        }
        else{
            for(int i=6;i<=num;i++)
            {
                dp[i] = dp[i-1] + dp[i-5];
            }
            return dp[num];
        }
    }
}
