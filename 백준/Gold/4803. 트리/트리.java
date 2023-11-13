import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int tk = 1;
        while(true) {
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if(n == 0 && m == 0)
                break;

            root = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                root[i] = i;
            }

            for(int i=1;i<=m ;i++){
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                if(find(x) != find(y)){
                    union(x,y);
                }else{
                    union(x,0);
                    union(y,0);
                }
            }

            for(int i=1;i<=n;i++){
                union(i,root[i]);
            }

            HashSet<Integer> set = new HashSet<>();
            for(int i=1;i<=n;i++){
                int p = find(i);
                if(p == 0)
                    continue;
                set.add(find(i));
            }

            int size = set.size();

            if(size == 0){
                sb.append("Case "+tk+": No trees.\n");
            }else if(size == 1){
                sb.append("Case "+tk+": There is one tree.\n");
            }else{
                sb.append("Case "+tk+": A forest of "+size+" trees.\n");
            }

            tk += 1;
        }


        System.out.print(sb);
        br.close();
    }

    static int find(int x){
        if(x == root[x]){
            return root[x];
        }

        return root[x] = find(root[x]);
    }

    static void union(int x,int y){
        x = find(x);
        y = find(y);

        if(x < y)
            root[y] = x;

        else{
            root[x] = y;
        }
    }
}

