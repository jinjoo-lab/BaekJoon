import java.util.*;
import java.io.*;

public class Main {

    static int n,m,q;
    static ArrayList<Integer>[] graph;
    static int[] root;
    static int[] count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i = 1 ; i <= n ; i++) {
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine()," ");
        count = new int[n+1];
        for(int i = 1 ; i <= n ; i++) {
            count[i] = Integer.parseInt(st.nextToken());

            if(count[i] == 0)
                count[i] = -1;
        }

        root = new int[n+1];
        for(int i = 1 ; i <= n ; i++) {
            root[i] = i;
        }

        for(int i = 1 ; i <= m ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(find(x) != find(y)) {
                union(x, y);
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 1 ; i <= q ; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int curV = Integer.parseInt(st.nextToken());

            int tmpR = count[find(curV)] > 0 ? 1 : 0;

            sb.append(tmpR+"\n");
        }

        System.out.print(sb);

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

        if(x < y) {
            root[y] = x;
            count[x] += count[y];
        }
        else {
            root[x] = y;
            count[y] += count[x];
        }
    }
}
