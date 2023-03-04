import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static long[] num = new long[1000001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        num[1] = 1;
        num[2] = 2;

        for(int i=3;i<=n;i++)
        {
            num[i] = (num[i-1]%15746 + num[i-2]%15746)%15746;
        }
        System.out.println(num[n]);
        br.close();
    }
}
