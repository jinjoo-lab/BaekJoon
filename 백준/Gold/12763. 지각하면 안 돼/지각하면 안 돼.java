import java.util.*;
import java.io.*;

public class Main {

    static int n,t,m,l;

    static ArrayList<Load>[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine()," ");
        t = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine()," ");
        l = Integer.parseInt(st.nextToken());

        arr = new ArrayList[n+1];
        for(int i = 1 ; i <= n ; i++){
            arr[i] = new ArrayList<>();
        }

        for(int i = 1 ; i <= l ; i++){
            st = new StringTokenizer(br.readLine()," ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int tt = Integer.parseInt(st.nextToken());
            int cc = Integer.parseInt(st.nextToken());

            arr[v1].add(new Load(v2,tt,cc));
            arr[v2].add(new Load(v1,tt,cc));
        }

        int returnV = start();
        System.out.println(returnV);


        br.close();
    }

    static int start(){

        int returnV = -1;

        PriorityQueue<Load> pq = new PriorityQueue<>(
                (x,y) -> x.c - y.c
        );

        int[][] d = new int[n+1][t+1];
        for(int i = 1 ; i <= n ; i++){
            for(int j = 0 ; j <= t ; j++){
                d[i][j] = Integer.MAX_VALUE;
            }
        }

        d[1][0] = 0;
        pq.add(new Load(1,0,0));

        while(!pq.isEmpty()){
            Load curP = pq.poll();

            if(curP.v == n){
                returnV = curP.c;
                break;
            }

            if(d[curP.v][curP.t] < curP.c)
                continue;

            for(Load nextP : arr[curP.v]){
                int nextC = curP.c + nextP.c;
                int nextT = curP.t + nextP.t;

                if(nextC > m || nextT > t)
                    continue;

                if(d[nextP.v][nextT] > nextC){
                    d[nextP.v][nextT] = nextC;
                    pq.add(new Load(nextP.v,nextT,nextC));
                }
            }
        }

        return returnV;
    }

    static class Load{
        int v;
        int t;
        int c;

        Load(int v,int t,int c){
            this.v = v;
            this.t = t;
            this.c = c;
        }
    }
}
