import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int[][] egg;

    static boolean[] v;

    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        egg = new int[n+1][2];
        v= new boolean[n+1];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int vt = Integer.parseInt(st.nextToken());
            int wt = Integer.parseInt(st.nextToken());

            egg[i][0] = vt;
            egg[i][1] = wt;
        }
        travel(1);
        System.out.println(result);
        br.close();
    }

    static int count(){
        int re = 0;

        for(int i=1;i<=n;i++){
            if(egg[i][0] <= 0)
                re += 1;
        }

        return re;
    }
    static void travel(int idx){
        if(idx == n+1){
            result = Math.max(count(),result);
            return;
        }

        if(egg[idx][0] <= 0)
        {
            travel(idx + 1);
        }

        else {
            boolean keep = true;

            for (int i = 1; i <= n; i++) {
                if (idx == i || egg[i][0] <= 0)
                    continue;

                egg[idx][0] -= egg[i][1];
                egg[i][0] -= egg[idx][1];
                travel(idx + 1);
                keep = false;
                egg[idx][0] += egg[i][1];
                egg[i][0] += egg[idx][1];
            }

            if(keep)
                travel(idx +1 );
        }
    }

    static void print(){
        for(int i=1;i<=n;i++){
            System.out.print(egg[i][0]+" ");
        }
        System.out.println();
    }
}
