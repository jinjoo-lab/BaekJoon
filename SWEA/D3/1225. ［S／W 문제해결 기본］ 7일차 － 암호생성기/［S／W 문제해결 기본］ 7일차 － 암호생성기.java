import java.io.*;
import java.util.*;

public class Solution {
    static int n = 0;

    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for(int a = 1 ; a <= 10 ; a++){
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine(), " ");

            for(int i=1 ;i <= 8 ; i++){
                q.add(Integer.parseInt(st.nextToken()));
            }

            int idx = 1;
            while(true){
                int cur = q.poll();

                cur -= idx;
                idx += 1;
                if(idx == 6)
                    idx = 1;

                if(cur <= 0) {
                    q.add(0);
                    break;
                }else{
                    q.add(cur);
                }
            }

            sb.append("#"+a+" ");
            while(!q.isEmpty()){
                sb.append(q.poll()+" ");
            }sb.append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}
