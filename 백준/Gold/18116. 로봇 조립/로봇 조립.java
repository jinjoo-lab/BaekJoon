import java.util.*;
import java.io.*;

public class Main {

    static int n;

    static int[] root = new int[1000_001];

    static HashSet<Integer>[] set = new HashSet[1000_001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();


        for(int i = 1 ; i <= 1000_000 ; i++){
            root[i] = i;
            set[i] = new HashSet<>();
            set[i].add(i);
        }

        n = Integer.parseInt(st.nextToken());

        int tmpRoot = 0;

        for(int i = 1 ; i <= n ; i++){
            st = new StringTokenizer(br.readLine()," ");

            String what = st.nextToken();

            if(what.equals("I")){
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());

                if(find(v1) != find(v2)){
                    union(v1,v2);
                }
            }else{
                int v = Integer.parseInt(st.nextToken());

                tmpRoot = find(v);

                sb.append(set[tmpRoot].size()+"\n");
            }
        }
        System.out.print(sb);

        br.close();
    }

    static int find(int x){
        if(x == root[x])
            return root[x];

        return root[x] = find(root[x]);
    }

    static void union(int x,int y){
        x = find(x);
        y = find(y);

        if(x < y){
            root[y] = x;
            set[x].addAll(set[y]);
        }else{
            root[x] = y;
            set[y].addAll(set[x]);
        }
    }
}
