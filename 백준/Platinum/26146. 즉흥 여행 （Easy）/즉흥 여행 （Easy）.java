import java.util.*;
import java.io.*;
public class Main {
    static int n = 0;
    static int m = 0;

    static int[] p;

    static int count = 0;
    static boolean[] visit;
    static Stack<Integer> stack;
    static ArrayList<Integer>[] graph;

    static boolean result = false;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        p = new int[n+1];
        graph = new ArrayList[n+1];
        visit = new boolean[n+1];

        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        stack = new Stack<>();

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(bf.readLine()," ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph[x].add(y);
        }

        dfs(1);

        if (result) {
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }

        bf.close();
    }

    static int dfs(int v){
        p[v] = ++count;
        stack.add(v);

        int root = p[v];

        for(int next : graph[v]){
            if(p[next] == 0){
                root = Math.min(root,dfs(next));
            }else if(!visit[next]){
                root = Math.min(root,p[next]);
            }
        }

        if(root == p[v]){

            int tmpCount = 0;

            while(true){
                int tmp = stack.pop();
                tmpCount++;

                visit[tmp] = true;

                if(tmp == v)
                    break;
            }

            if(tmpCount == n)
                result = true;

        }

        return root;
    }
}

