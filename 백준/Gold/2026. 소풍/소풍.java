import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int k = 0;

    static int[][] graph;

    static ArrayList<Integer> peek = new ArrayList<>();

    static boolean[] v;
    static int[] count;

    static boolean find = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n+1][n+1];
        v= new boolean[n+1];
        count = new int[n+1];

        for(int i=1;i<=n;i++){
            count[i] = 1;
        }


        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph[x][y] = 1;
            graph[y][x] = 1;

            count[x] += 1;
            count[y] += 1;
        }

        for(int i=1;i<=n;i++){

            if(count[i] >= k){
                peek.add(i);
                v[i] = true;
                travel(i,1);
                peek.remove(peek.indexOf(i));
                v[i] = false;

                if(find){break;}
            }
        }

        if(!find){
            System.out.println(-1);
        }
        br.close();
    }

    static void travel(int cur,int count){
        if(find){
            return;
        }

        if(count == k){
            StringBuilder sb = new StringBuilder();

            for(int next: peek){
                sb.append(next+"\n");
            }

            find = true;
            System.out.print(sb);
            return;
        }

        for(int i=cur+1;i<=n;i++){
            if(graph[cur][i] == 1 && !v[i]){
                boolean keep = true;

                for(int next : peek){
                    if(graph[i][next] == 0){
                        keep = false;
                        break;
                    }
                }

                if(keep){
                    v[i] = true;
                    peek.add(i);
                    travel(i,count + 1);
                    peek.remove(peek.indexOf(i));
                    v[i] = false;
                }
            }
        }
    }
}
