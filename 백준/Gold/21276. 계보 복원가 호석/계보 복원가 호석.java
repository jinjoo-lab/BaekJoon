import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int m = 0;

    static int[] board;
    static HashMap<Integer,String> pn = new HashMap<>();
    static HashMap<String,Integer> pn2 = new HashMap<>();
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        board = new int[n+1];

        graph = new ArrayList[n+1];

        st = new StringTokenizer(bf.readLine(), " ");

        for(int i=1;i<=n;i++){
            String cur = st.nextToken();
            graph[i] = new ArrayList<>();
            pn.put(i,cur);
            pn2.put(cur,i);
        }

        st = new StringTokenizer(bf.readLine(), " ");
        m = Integer.parseInt(st.nextToken());

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(bf.readLine(), " ");
            String f = st.nextToken();
            String s = st.nextToken();

            int fn = pn2.get(f);
            int sn = pn2.get(s);

            graph[sn].add(fn);
            board[fn] += 1;
        }

        StringBuilder sb = new StringBuilder();
        int num = 0;

        PriorityQueue<String> pq = new PriorityQueue<>(
                (x,y) -> x.compareTo(y)
        );

        Queue<Integer> q = new LinkedList<>();

        for(int i=1;i<=n;i++){
            if(board[i] == 0){
                num += 1;
                pq.add(pn.get(i));
                q.add(i);
            }
        }

        sb.append(num+"\n");
        while(!pq.isEmpty()){
            sb.append(pq.poll()+" ");
        }sb.append("\n");


        Node[] result = search(q);

        for(int i=0;i<n;i++){

            sb.append(pn.get(result[i].root)+" "+result[i].count+" ");

            StringBuilder ch = new StringBuilder();

            while(!result[i].child.isEmpty()){
                ch.append(result[i].child.poll()+" ");
            }

            sb.append(ch);
            sb.append("\n");
        }


        System.out.print(sb);
        bf.close();
    }

    static Node[] search(Queue<Integer> q){
        int[] childNum = new int[n+1];
        int[] root = new int[n+1];
        ArrayList<Integer>[] childList = new ArrayList[n+1];

        for(int i=0;i<=n;i++){
            childList[i] = new ArrayList<>();
        }

        while(!q.isEmpty()){
            int cur = q.poll();

            for(int next : graph[cur]){
                board[next] -= 1;

                if(board[next] == 0){
                    root[next] = cur;
                    childNum[cur] += 1;
                    childList[cur].add(next);
                    q.add(next);
                }
            }
        }

        Node[] arr = new Node[n];

        for(int i=1;i<=n;i++){
            int num = i-1;

            arr[num] = new Node(i,childNum[i]);
            for(int next : childList[i]){
                arr[num].child.add(pn.get(next));
            }
        }

        Arrays.sort(arr,(x,y) -> {
            return pn.get(x.root).compareTo(pn.get(y.root));
        });

        return arr;
    }
}
class Node{
    int root;
    int count;
    PriorityQueue<String> child;

    Node(int root,int count){
        this.root = root;
        this.count = count;
        this.child = new PriorityQueue<>(
                (x,y) -> x.compareTo(y)
        );
    }
}

