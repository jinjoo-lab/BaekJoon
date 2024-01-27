import java.util.*;
import java.io.*;
public class Solution {
    static int n = 0;
    static int t = 0;

    static int m = 0;

    static int[] board;


    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(st.nextToken());

        for(int a= 1; a <= t ;a++){
            st = new StringTokenizer(bf.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            boolean keep = true;

            for(int i=0;i<n;i++){

                if((m & (1 << i)) != 0){
                    continue;
                }

                keep = false;
                break;
            }

            if(keep){
                sb.append("#"+a+" ON\n");
            }else{
                sb.append("#"+a+" OFF\n");
            }

        }

        System.out.print(sb);
        bf.close();
    }
}
