import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;

    static boolean[] prime = new boolean[5000001];
    static StringBuilder sb;

    static ArrayList<Integer> num = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        sb = new StringBuilder();

        for(int i=2;i<=Math.sqrt(5000001);i++){
            if(!prime[i]){
                int j = 2;

                while(i * j <= 5000000){
                    prime[i*j] = true;
                    j = j + 1;
                }

                num.add(i);
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1;i <= n ;i ++){
            int cur = Integer.parseInt(st.nextToken());
            turn(cur);
        }
        System.out.print(sb);
        br.close();
    }


    static void turn(int cur){
        if(!prime[cur]){
            sb.append(cur+"\n");
            return;
        }

        for(int tmp : num){
            if(cur % tmp == 0){
                sb.append(tmp+" ");
                turn(cur/tmp);
                break;
            }
        }
    }

}