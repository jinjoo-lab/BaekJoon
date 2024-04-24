import java.util.*;
import java.io.*;

public class Main {

    static boolean[] v;
    static int[] per;
    static int n,k;
    static int[] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n+1];
        per = new int[n+1];
        v = new boolean[n+1];

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 1 ; i <= n ; i++){
            board[i] = Integer.parseInt(st.nextToken());
        }

        makePer(1);
        System.out.println(count);
        br.close();
    }
    static void print(){
        for(int i = 1 ; i <= n ; i++){
            System.out.print(per[i]+" ");
        }
        System.out.println();
    }

    static int count = 0;
    static void makePer(int idx){

        if(idx > n){
            int start = 500;

            for(int i = 1 ; i <= n ; i++){
                start += per[i];
                start -= k;

                if(start < 500){
                    return;
                }
            }

            count++;
            return;
        }

        for(int i = 1 ; i <= n ; i++){
            if(!v[i]){
                v[i] = true;
                per[idx] = board[i];
                makePer(idx + 1);
                v[i] = false;
            }
        }
    }
}
