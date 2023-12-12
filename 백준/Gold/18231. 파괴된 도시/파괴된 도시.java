import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int m = 0;

    static int k = 0;

    static ArrayList<Integer>[] graph;

    static boolean[] isIt;

    static boolean[] v;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        isIt = new boolean[n+1];
        v = new boolean[n+1];

        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(bf.readLine(), " ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph[x].add(y);
            graph[y].add(x);
        }

        k = Integer.parseInt(bf.readLine());
        st = new StringTokenizer(bf.readLine(), " ");

        for(int i=1;i<=k;i++){
            int cur = Integer.parseInt(st.nextToken());

            isIt[cur] = true;
        }

        ArrayList<Integer> result = new ArrayList<>();

        for(int i=1;i<=n;i++){

            if(isIt[i]){

                boolean find = true;

                for(int next : graph[i]){
                    if(!isIt[next]){
                        find = false;
                        break;
                    }
                }

                if(find){
                    result.add(i);
                }

            }
        }

        int count = 0;

        for(int cur : result){

            if(!v[cur]){
                v[cur] = true;
                count += 1;
            }

            for(int next : graph[cur]){

                if(!v[next] && isIt[next]){
                    v[next] = true;
                    count += 1;
                }

            }
        }

        StringBuilder sb = new StringBuilder();

        if(result.size() == 0 || count != k){
            sb.append(-1+"\n");
        }else {
            sb.append(result.size()+"\n");
            for (int cur : result) {
                sb.append(cur + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
        bf.close();
    }


}
