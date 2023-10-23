import java.io.*;
import java.util.*;

public class Solution {

    static int t = 0;
    static int n = 0;

    static int[] board;
    static boolean[] v;

    static double result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        t = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        
        for(int k=1;k<=t;k++){
            n = Integer.parseInt(br.readLine());
            board = new int[n+1];
            v= new boolean[n+1];
            result = 0;
            st = new StringTokenizer(br.readLine(), " ");

            for(int i=1;i<=n;i++){
                board[i] = Integer.parseInt(st.nextToken());
            }

            travel(1,0);
            double printResult = result / (Math.pow(2,n) - 1);

            if((int)printResult == printResult){
                sb.append("#"+k+" "+(int)printResult+"\n");
            }

            else{
                sb.append("#"+k+" "+printResult+"\n");
            }
        }
        System.out.print(sb);
        br.close();
    }
    static void print(){
        for(int i=1;i<=n;i++){
            if(v[i])
                System.out.print(1+" ");
            else
                System.out.print(0+" ");
        }
        System.out.println();
    }

    static double cal(int cur){
        double tmp = (double)cur;
        int num = 0;
        for(int i=1;i<=n;i++){
            if(v[i])
                num = num + 1;
        }

        if(num == 0)
            return 0;

        return tmp / num;
    }

    static void travel(int cur,int tmp){
        if(cur > n){
            double tmpResult = cal(tmp);
            result += tmpResult;
            return;
        }

        if(!v[cur]){
            v[cur] = true;
            travel(cur+1,tmp+board[cur]);
            v[cur] = false;
            travel(cur+1,tmp);
        }
    }
}