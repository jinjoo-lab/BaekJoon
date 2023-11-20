import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n = 0;
    static int m = 0;
    static int[] pre;

    static long[] count;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        pre = new int[n+1];
        for(int i=1;i<=n;i++){
            int tmp = Integer.parseInt(st.nextToken());

            pre[i] = tmp % m;
        }

        count = new long[m+1];
        count[0] = 1;

        for(int i=1;i<=n;i++){
            pre[i] += pre[i-1];

            pre[i] = pre[i] % m;

            count[pre[i]] += 1;
        }

        long result = 0;
        for(int i=0;i<m;i++){
           if(count[i] >= 1){
               result += (count[i] * (count[i] - 1)) / 2;
           }
        }

        System.out.println(result);
        br.close();
    }

}
