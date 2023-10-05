import java.io.*;
import java.util.*;

public class Main {
    static int k = 0;
    static int n = 0;

    static HashMap<String,Integer> map;
    static int[] root;
    static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int m = 1; m <= k; m++){
            n = Integer.parseInt(br.readLine());
            root = new int[2*(n+1)];
            count = new int[2*(n+1)];

            for(int i =1 ;i<=2*n+1;i++){
                root[i] = i;
                count[i] = 1;
            }
            int start = 1;
            map = new HashMap<>();

            for(int i=1;i<=n;i++){
                StringTokenizer st = new StringTokenizer(br.readLine()," ");
                String x = st.nextToken();
                String y = st.nextToken();

                if(!map.containsKey(x)){
                    map.put(x,start);
                    start = start + 1;
                }

                if(!map.containsKey(y)){
                    map.put(y,start);
                    start = start + 1;
                }

                int nx = map.get(x);
                int ny = map.get(y);

                union(nx,ny);
                sb.append(count[Math.min(find(nx),find(ny))]+"\n");
            }
        }
        System.out.print(sb);
        br.close();
    }

    static int find(int x){
        if(root[x] == x)
            return root[x];

        return root[x] = find(root[x]);
    }

    static void union(int x,int y){
        x = find(x);
        y = find(y);

        if(x == y)
        {
            return;
        }

        else if( x < y) {
            root[y] = x;
            count[x] += count[y];
        }
        else {
            root[x] = y;
            count[y] += count[x];
        }
    }
}
