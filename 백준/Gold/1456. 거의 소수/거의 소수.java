import java.io.*;
import java.util.*;

public class Main {
    static long n = 0;
    static long m = 0;

    static boolean[] prime = new boolean[10000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Long.parseLong(st.nextToken());
        m = Long.parseLong(st.nextToken());

        long count = 0;
        for(int i= 2;i <= Math.sqrt(10000000);i++){
            if(!prime[i]){
                int j= 2;


                while(i*j <= 10000000){
                    prime[i*j] = true;
                    j = j + 1;
                }
            }
        }

        for(int i=2;i<=10000000;i++){
            if(!prime[i]){
                long tmp = i;
                while((double)i <= (double)m/tmp){
                    if((double)i >= (double)n/tmp){count = count + 1;}
                    tmp = tmp * i;
                }
            }
        }

        System.out.println(count);

        br.close();
    }

}

