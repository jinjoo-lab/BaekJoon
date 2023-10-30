import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        boolean[] isPrime = new boolean[m+1];

        StringBuilder sb = new StringBuilder();

        for(int i=2;i<=Math.sqrt(m);i++){
            if(!isPrime[i]){
                int j= 2;

                while(i * j <= m){
                    isPrime[i*j] = true;
                    j = j + 1;
                }
            }
        }

        for(int i=n;i<=m;i++){
            if(!isPrime[i] && palindrome(String.valueOf(i))){
                sb.append(i+"\n");
            }
        }

        sb.append("-1\n");
        System.out.print(sb);
        br.close();
    }

    static boolean palindrome(String cur){
        char[] arr = cur.toCharArray();
        int len = arr.length - 1;

        for(int i=0;i<arr.length/2;i++){
            if(arr[i] == arr[len - i]){
                continue;
            }

            return false;
        }

        return true;
    }

}

