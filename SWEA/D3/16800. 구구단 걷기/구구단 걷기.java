import java.io.*;
import java.util.*;

public class Solution {
    static int t = 0;
    static long n = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());

        for(int k=1;k<=t;k++){
            n = Long.parseLong(br.readLine());

            sb.append("#"+k+" "+search(n)+"\n");
        }

        System.out.print(sb);
        br.close();
    }

    static long search(long cur){
        long count = -1;

        for(long i = 1; i <= Math.sqrt(cur); i ++){

            if(cur % i == 0){
                long tmp = cur / i;

                if(count == -1 || count > (tmp + i - 2)){
                    count = tmp + i - 2;
                }
            }

        }

        return count;
    }
}
