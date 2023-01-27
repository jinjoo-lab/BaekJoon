import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        primeNumber(m, n);
    }
    static void primeNumber(int m, int n) {
        int[] arr = new int[n+1];
        StringBuilder sb = new StringBuilder();
        
        for (int i = 2; i <= n; i++) {
            arr[i] = i;
        }
        
        for (int i = 2; i <= n; i++) {
            if (arr[i] == 0) continue;  
            for (int j = i+i; j <= n; j += i) {
                arr[j] = 0;
            }
        }
        for (int i = m; i <= n; i++) {
            if (arr[i] != 0) sb.append(i + "\n");
        }
        System.out.print(sb);
    }
}