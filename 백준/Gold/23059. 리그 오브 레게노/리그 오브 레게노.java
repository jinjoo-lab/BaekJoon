import java.util.*;
import java.io.*;

public class Main {

    static int[] count;
    static int n;
    static HashMap<String,Integer> map = new HashMap<>();
    static HashMap<Integer,String> map2 = new HashMap<>();
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        count = new int[2*n + 1];

        graph = new ArrayList[2 * n+1];
        for(int i = 1 ; i <= 2 * n ; i++) {
            graph[i] = new ArrayList<>();
        }

        int number = 1;

        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            String s1 = st.nextToken();
            String s2 = st.nextToken();

            int tmpNumF = 0;
            if(!map.containsKey(s1)) {
                tmpNumF = number;
                map.put(s1,tmpNumF);
                map2.put(tmpNumF,s1);
                number++;
            }else {
                tmpNumF = map.get(s1);
            }

            int tmpNumS = 0;
            if(!map.containsKey(s2)) {
                tmpNumS = number;
                map.put(s2,tmpNumS );
                map2.put(tmpNumS,s2);
                number++;
            }else {
                tmpNumS = map.get(s2);
            }

            graph[tmpNumF].add(tmpNumS);
            count[tmpNumS]++;
        }

        Queue<Turn> q = new ArrayDeque<>();
        for(int i = 1 ; i < number ; i++) {
            if(count[i] == 0) {
                q.add(new Turn(i,0));
            }
        }

        PriorityQueue<Turn> pq = new PriorityQueue<>(
                (x,y) -> {
                    if(x.turn == y.turn)
                        return map2.get(x.cur).compareTo(map2.get(y.cur));

                    return x.turn - y.turn;}
        );

        int findNum = 0;
        int[] turn = new int[number + 1];

        while(!q.isEmpty()) {
            Turn cur = q.poll();
            findNum++;
            pq.add(new Turn(cur.cur,cur.turn));

            for(int next : graph[cur.cur]) {
                count[next]--;
                turn[next] = Math.max(turn[next],cur.turn + 1);

                if(count[next] == 0) {
                    q.add(new Turn(next,turn[next]));
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        if(findNum != number - 1){
            sb.append("-1\n");
        }else {
            while (!pq.isEmpty()) {
                sb.append(map2.get(pq.poll().cur)).append("\n");
            }
        }

        System.out.print(sb);

        br.close();
    }

    static class Turn {
        int cur;
        int turn;

        Turn(int cur,int turn) {
            this.cur = cur;
            this.turn = turn;
        }
    }
}
