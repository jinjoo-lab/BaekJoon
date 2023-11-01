import java.io.*;
import java.util.*;

public class Solution {
    static int t = 0;
    static int n = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(st.nextToken());
        for(int k=1;k<=t;k++){
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());

            char[] arr = br.readLine().toCharArray();

            if(n %2 == 1)
            {
                sb.append("#"+k+" "+"No\n");
                continue;
            }

            int half = n / 2;
            boolean can = true;
            for(int i=1;i<=half;i++){
                int l = i - 1;
                int r = half + i -1;

                if(arr[l] != arr[r])
                {
                    can = false;
                    break;
                }
            }

            if(can){
                sb.append("#"+k+" "+"Yes\n");
            }else{
                sb.append("#"+k+" "+"No\n");
            }
        }
        System.out.print(sb);
        br.close();
    }
}