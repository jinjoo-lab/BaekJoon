import java.util.*;
import java.io.*;
public class Main {
    static int n = 0;
    static ArrayList<Integer>[] tree;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        tree = new ArrayList[n+1];

        for(int i=1;i<=n;i++){
            tree[i] = new ArrayList<>();
        }

        for(int i=1;i<=n-1;i++){
            st = new StringTokenizer(bf.readLine(), " ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            tree[x].add(y);
            tree[y].add(x);
        }

        Queue<Integer> q = new LinkedList<>();
        int[] p = new int[n+1];
        q.add(1);

        while(!q.isEmpty()){
            int cur = q.poll();

            for(int next : tree[cur]){
                if(p[cur] == next) continue;

                q.add(next);
                p[next] = cur;
            }
        }

        for(int i=2;i<=n;i++){
            sb.append(p[i]);
            sb.append("\n");
        }

        System.out.print(sb);

        bf.close();
    }
}