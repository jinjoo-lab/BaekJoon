import java.util.*;
import java.io.*;
public class Main {

    static int[] data = new int[10];
    static boolean[] v = new boolean[10];

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();


        for(int i=0;i<9;i++){
            st = new StringTokenizer(bf.readLine()," ");
            data[i] = Integer.parseInt(st.nextToken());
        }


        go(0,0);
        bf.close();
    }

    static void go(int depth,int cur){
        if(depth == 7)
        {
            int sum = 0;
            StringBuilder sb = new StringBuilder();

            for(int i=0;i<9;i++){
                if(v[i]){
                    sum += data[i];
                    sb.append(data[i]+"\n");
                }
            }

            if(sum == 100){
                System.out.print(sb);
            }
            return ;
        }

        for(int i= cur; i < 9 ; i ++)
        {
            if(!v[i]){
                v[i] = true;
                go(depth + 1, i+1);
                v[i] = false;
            }
        }
    }

}
