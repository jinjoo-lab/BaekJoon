import java.io.*;
import java.util.*;

public class Main {
    static int t = 0;
    static int n = 0;

    static int[] prime = {1,3,7,9};

    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        sb = new StringBuilder();

        travel(2,1);
        travel(3,1);
        travel(5,1);
        travel(7,1);

        System.out.print(sb);
        br.close();
    }

    static void travel(int cur,int num){
        if(num == n){
            sb.append(cur+"\n");
            return;
        }

        for(int i=0;i<4;i++){
            int tmp = cur * 10 + prime[i];

            if(isPrime(tmp)){
                travel(tmp,num + 1);
            }
        }
    }

    static boolean isPrime(int num){
        for(int i=2;i<=Math.sqrt(num);i++){
            if(num % i == 0)
                return false;
        }

        return true;
    }

}

