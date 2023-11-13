import java.io.*;
import java.util.*;

public class Solution {
    static int t = 0;
    static long a = 0;
    static long b = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(st.nextToken());

        for(int i=1;i<=t;i++){
            st = new StringTokenizer(br.readLine(), " ");

            a = Long.parseLong(st.nextToken());
            b = Long.parseLong(st.nextToken());

            if(a > b){
                sb.append("#"+i+" "+-1+"\n");
                continue;
            }
            
            long minus = Math.abs(a - b);
            if(minus == 1l){
                sb.append("#"+i+" "+-1+"\n");
            }
            else {
                long cur = minus / 2l;

                sb.append("#"+i+" "+cur+"\n");
            }

        }

        System.out.print(sb);
        br.close();
    }
}
