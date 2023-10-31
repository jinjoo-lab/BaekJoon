import java.io.*;
import java.util.*;

public class Solution {
    static int t = 0;
    static int n = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        t = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        for(int i=1;i<=t;i++){
            st = new StringTokenizer(br.readLine(), " ");

            String first = st.nextToken();
            String second = st.nextToken();

            if(first.equals(second)){
                sb.append("#"+i+" "+first+"\n");
            }

            else{
                sb.append("#"+i+" "+1+"\n");
            }
        }

        System.out.print(sb);
        br.close();
    }

}

