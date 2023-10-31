import java.io.*;
import java.util.*;

public class Solution {

    static int t = 0;
    static int n = 0;
    static int m = 0;

    static ArrayList<Node>[] graph;

    static boolean[] visit;

    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        t = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        for(int k=1;k<=t;k++){
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            graph = new ArrayList[n+1];
            for(int i=1;i<=n;i++){
                graph[i] = new ArrayList<>();
            }

            for(int i=1;i<=m;i++){
                st = new StringTokenizer(br.readLine(), " ");
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph[s].add(new Node(e,v));
            }

            result = - 1;
            for(int i=1;i<=n;i++){
                visit = new boolean[n+1];
                find(i,i,0);
            }

            sb.append("#"+k+" "+result+"\n");
        }

        System.out.print(sb);
        br.close();
    }


    static void find(int start,int end,int num){
        if(visit[end]){
            if(start == end){
                if(result == -1)
                    result = num;
                else{
                    result = Math.min(result,num);
                }
            }
            return;
        }

        visit[end] = true;
        for(Node next : graph[end]){
            if(result == -1 || result > num + next.c) {
                find(start, next.v, num + next.c);
            }
        }
    }
}
class Node{
    int v;
    int c;

    Node(int v,int c){
        this.v = v;
        this.c = c;
    }
}
