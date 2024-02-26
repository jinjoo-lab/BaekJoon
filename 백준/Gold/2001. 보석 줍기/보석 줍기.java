import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;
    static int m = 0;
    static int k = 0;

    static int max = 0;
    static ArrayList<Edge>[] graph;
    static int[] isJ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        isJ = new int[n+1];
        graph = new ArrayList[n+1];

        for(int i=1 ; i<=n ; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 1 ; i <= k ;i++){
            int cur = Integer.parseInt(br.readLine());

            isJ[cur] = i;
        }

        for(int i = 1 ; i <= m ; i++){
            st = new StringTokenizer(br.readLine()," ");

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[v1].add(new Edge(v2,c));
            graph[v2].add(new Edge(v1,c));
        }

        boolean[][] v = new boolean[n+1][1 << (k + 1)];

        Queue<Edge> q = new ArrayDeque<>();
        q.add(new Edge(1,0));

        v[1][0] = true;

        while(!q.isEmpty()){
            Edge cur = q.poll();

            if(cur.v == 1){
                int plus = isJ[1] > 0 ? 1 : 0;

                if(plus == 1){
                    if((cur.c & 1 << 1) != 0){
                        max = Math.max(max,countOne(cur.c));
                    }else{
                        max = Math.max(max,countOne(cur.c) + 1);
                    }
                }else{
                    max = Math.max(max,countOne(cur.c) + plus);
                }
            }

            for(Edge next : graph[cur.v]){

                int count = countOne(cur.c);

                if(count > next.c)
                    continue;

                if(isJ[next.v] > 0){
                    if(count < next.c){
                        // 줍거나 안줍거나
                        if(!v[next.v][cur.c | 1 << isJ[next.v]]){
                            v[next.v][cur.c | 1 << isJ[next.v]] = true;
                            q.add(new Edge(next.v,cur.c | 1 << isJ[next.v]));
                        }

                        if(!v[next.v][cur.c]){
                            v[next.v][cur.c] = true;
                            q.add(new Edge(next.v , cur.c));
                        }

                    }else if(count == next.c){
                        // 안주어야 함
                        if(!v[next.v][cur.c]){
                            v[next.v][cur.c] = true;
                            q.add(new Edge(next.v , cur.c));
                        }
                    }

                }else{
                    if(!v[next.v][cur.c]){
                        v[next.v][cur.c] = true;
                        q.add(new Edge(next.v , cur.c));
                    }
                }

            }
        }


        System.out.println(max);
        br.close();
    }

    static int countOne(int num){
        int count = Integer.bitCount(num);

        return count;
    }
}
class Edge{
    int v;
    int c;

    Edge(int v,int c){
        this.v = v;
        this.c = c;
    }
}
