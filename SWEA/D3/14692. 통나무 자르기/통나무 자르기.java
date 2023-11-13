import java.io.*;
import java.util.*;

public class Solution {
    static int t = 0;
    static int n = 0;
    static int m = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(st.nextToken());
        for(int k=1;k<=t;k++){
            st = new StringTokenizer(br.readLine()," ");
            n = Integer.parseInt(st.nextToken());

            if(n % 2 == 0){
                sb.append("#"+k+" "+"Alice\n");
            }else{
                sb.append("#"+k+" "+"Bob\n");
            }
        }


        System.out.print(sb);
        br.close();
    }

}


