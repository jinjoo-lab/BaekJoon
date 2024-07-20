import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static int[] arr;
    static int[] result;
    static HashMap<String,Integer> map = new HashMap<>();
    static ArrayList<int[]> cal = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        arr = new int[n+1];
        result = new int[n+1];

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 1  ; i <= n ; i++) {
            arr[i] = Integer.parseInt(st.nextToken()) - 1;
            result[i] = arr[i];
        }

        st = new StringTokenizer(br.readLine()," ");
        m = Integer.parseInt(st.nextToken());

        for(int i = 1 ; i <= m ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int c =  Integer.parseInt(st.nextToken());

            cal.add(new int[]{v1,v2,c});
        }
        Arrays.sort(result,1,n+1);

        map.put(make(arr),0);

        go();

        System.out.println(map.getOrDefault(make(result),-1));

        br.close();
    }

    static void go() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(make(arr),0));

        while(!q.isEmpty()) {
            Node cur = q.poll();

            int[] tmp = remake(cur.cur);

            for(int[] next : cal) {
                int idx1 = next[0];
                int idx2 = next[1];
                int c =  next[2];

                swap(idx1,idx2,tmp);
                String tmpS = make(tmp);
                if(!map.containsKey(tmpS) || cur.c + c < map.get(tmpS)) {
                    map.put(tmpS,cur.c + c);
                    q.add(new Node(tmpS,cur.c+ c));
                }
                swap(idx1,idx2,tmp);
            }
        }
    }

    static int[] remake(String cur) {
        int[] tmp = new int[n+1];

        for(int i = 1 ; i <= n ; i++) {
            tmp[i] = cur.charAt(i-1) - '0';
        }

        return tmp;
    }

    static String make(int[] arr) {
        StringBuilder sb = new StringBuilder();

        for(int i = 1 ; i <= n ; i++) {
            sb.append(arr[i]);
        }

        return sb.toString();
    }

    static boolean isClear() {
        for(int i = 1 ; i <= n ; i++) {
            if(arr[i] != result[i])
                return false;
        }

        return true;
    }

    static class Node {
        String cur;
        int c;

        Node(String cur,int c) {
            this.cur = cur;
            this.c = c;
        }
    }

    static void swap(int i,int j,int[] arr) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
