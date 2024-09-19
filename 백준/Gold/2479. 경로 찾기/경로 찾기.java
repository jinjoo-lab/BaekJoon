import java.util.*;
import java.io.*;

public class Main {

    static int n,k;
    static char[][] inputArr;
    static int[][] graph;
    static int s,e;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        inputArr = new char[n+1][k+1];

        for(int i = 1 ; i <= n ; i++) {
            char[] arr = br.readLine().toCharArray();

            for(int j = 1 ; j <= k ; j++) {
                inputArr[i][j] = arr[j - 1];
            }
        }

        graph = new int[n+1][n+1];

        for(int i = 1 ; i <= n ; i++) {
            for(int j = i + 1 ; j <= n ; j++) {
                if(i == j)
                    continue;

                graph[i][j] = count(i,j);
                graph[j][i] = graph[i][j];
            }
        }

        st = new StringTokenizer(br.readLine()," ");
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        boolean[] v = new boolean[n+1];
        int[] path = new int[n+1];
        Queue<Integer> q = new ArrayDeque<>();
        v[s] = true;
        q.add(s);

        String result = "-1";

        while(!q.isEmpty()) {
            int cur = q.poll();

            if(cur == e) {
                StringBuilder sb = new StringBuilder();
                Stack<Integer> stack = new Stack<>();

                stack.push(e);
                int start = e;

                while(path[start] != 0) {
                    stack.push(path[start]);
                    start = path[start];
                }

                while(!stack.isEmpty()) {
                    sb.append(stack.pop()+" ");
                }

                result = sb.toString();

                break;
            }

            for(int i = 1 ; i <= n ; i++) {
                if(!v[i] && (graph[cur][i] == 1 || graph[i][cur] == 1)) {
                    v[i] = true;
                    path[i] = cur;
                    q.add(i);
                }
            }
        }

        System.out.println(result);

        br.close();
    }

    static void print() {
        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                System.out.print(graph[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int count(int idx1,int idx2) {
        int count = 0;

        for(int i = 1 ; i <= k ; i++) {
            if(inputArr[idx1][i] != inputArr[idx2][i]) {
                count++;
            }
        }

        return count;
    }
}
