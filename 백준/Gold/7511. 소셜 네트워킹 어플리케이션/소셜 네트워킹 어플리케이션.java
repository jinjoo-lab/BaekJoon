import java.util.*;
import java.io.*;

public class Main {

    static int t,n,m,k;
    static int[] root = new int[1000001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(st.nextToken());

        for(int a = 1 ; a <= t ; a++){
            st = new StringTokenizer(br.readLine()," ");
            n = Integer.parseInt(st.nextToken());

            for(int i = 0 ; i < n ; i++) {
                root[i] = i;
            }

            st = new StringTokenizer(br.readLine()," ");
            m = Integer.parseInt(st.nextToken());

            for(int i = 0 ; i < m ; i++) {
                st = new StringTokenizer(br.readLine()," ");
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());

                if(find(v1) != find(v2)) {
                    union(v1,v2);
                }
            }

            st = new StringTokenizer(br.readLine()," ");
            k = Integer.parseInt(st.nextToken());

            sb.append("Scenario "+a+":\n");

            for(int i = 0 ; i < k ; i++){
                st = new StringTokenizer(br.readLine()," ");
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());

                if(find(v1) == find(v2)) {
                    sb.append("1\n");
                }else{
                    sb.append("0\n");
                }
            }sb.append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    static int find(int x) {
        if(x == root[x])
            return root[x];

        return root[x] = find(root[x]);
    }

    static void union(int x,int y) {
        x = find(x);
        y = find(y);

        if(x < y)
            root[y] = x;
        else
            root[x] = y;
    }
}
