import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int m = 0;

    static int[] p;

    static boolean[] visit;

    static Stack<Integer> stack = new Stack<>();

    static ArrayList<Integer>[] graph;

    static ArrayList<ArrayList<Integer>> SCC = new ArrayList<>();
    static int num = 0;

    static int count = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        p = new int[n+1];
        graph = new ArrayList[n+1];
        visit = new boolean[n+1];

        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(bf.readLine(), " ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph[x].add(y);
        }

        for(int i=1;i<=n;i++){
            if(p[i] == 0){
                dfs(i);
            }
        }

        Collections.sort(SCC, (x,y) -> x.get(0) - y.get(0));
        System.out.println(count);

        for(ArrayList<Integer> cur : SCC){
            for(int nextV : cur){
                sb.append(nextV+" ");
            }

            sb.append("-1\n");
        }
        System.out.print(sb);

        bf.close();
    }

    static int dfs(int v){

        p[v] = ++num;
        stack.push(v);
        
        int root = p[v];
        for(int next : graph[v]){

            if(p[next] == 0){
                root = Math.min(root,dfs(next));
            }else if(!visit[next]){
                root = Math.min(root,p[next]);
            }
        }

        if(root == p[v]){

            ArrayList<Integer> tmpList = new ArrayList<>();

            while(true){
                int tmp = stack.pop();
                visit[tmp] = true;
                tmpList.add(tmp);
                p[tmp] = root;
                if(tmp == v)
                    break;
            }

            count++;
            Collections.sort(tmpList,(x,y) -> x - y);
            SCC.add(tmpList);
        }

        return root;
    }
}
