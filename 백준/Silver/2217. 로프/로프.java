import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        int[] roop = new int[n];
        for(int i=0;i<n;i++)
            roop[i] = Integer.parseInt(br.readLine());

        long max = 0;
        Arrays.sort(roop);

        for(int i=0;i<n;i++)
        {
            max = Math.max(max , roop[i] * (n - i));
        }

        System.out.println(max);

        br.close();
    }
}
