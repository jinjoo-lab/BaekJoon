import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static boolean[] vLine;
    static boolean[] vNum;

    static ArrayList<Integer>[] lines;
    static ArrayList<Integer>[] station;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        vNum = new boolean[n+1];
        vLine = new boolean[m+1];
        lines = new ArrayList[m+1];
        station = new ArrayList[n+1];

        for(int i=1;i<=m;i++){
            lines[i] = new ArrayList<>();
        }
        for(int i=1;i<=n;i++){
            station[i] = new ArrayList<>();
        }

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine(), " ");

            while(st.hasMoreTokens()){
                int tmp = Integer.parseInt(st.nextToken());

                if(tmp == -1)
                    break;

                station[tmp].add(i);
                lines[i].add(tmp);
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        travel(start,end);
        br.close();
    }

    static void travel(int s,int e){
         PriorityQueue<Node> pq = new PriorityQueue<>(
                 (x,y) -> x.count - y.count
         );

         for(int next : station[s]){
             pq.add(new Node(next,s,0));
             vLine[next] = true;
         }
         vNum[s] = true;


         while(!pq.isEmpty()){
             Node cur = pq.poll();

             if(cur.num == e){
                 System.out.println(cur.count);
                 return;
             }

            for(int nextNum : lines[cur.line]){

                if(!vNum[nextNum]){
                    vNum[nextNum] = true;
                    pq.add(new Node(cur.line,nextNum,cur.count));

                    for(int nextLine : station[nextNum]){
                        if(!vLine[nextLine]){
                            vLine[nextLine] = true;
                            pq.add(new Node(nextLine,nextNum,cur.count+1));
                        }
                    }

                }

            }
         }

        System.out.println(-1);
    }
}
class Node{
    int line;
    int num;
    int count;

    Node(int line,int num,int count){
        this.line = line;
        this.num = num;
        this.count = count;
    }
}