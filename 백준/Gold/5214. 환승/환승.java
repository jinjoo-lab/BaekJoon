import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int k = 0;

    static ArrayList<Integer>[] station;
    static ArrayList<Integer>[] cube;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());


        station = new ArrayList[n+1];
        cube = new ArrayList[k+1];

        for(int i=1;i<=n;i++){
            station[i] = new ArrayList<>();
        }

        for(int i=1;i<=k;i++){
            cube[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1;j<=m;j++){
                int tmp = Integer.parseInt(st.nextToken());

                station[tmp].add(i);
                cube[i].add(tmp);
            }
        }


        search();

        br.close();
    }

    static void search(){
        boolean[] vNum = new boolean[n+1];
        boolean[] vCube = new boolean[k+1];

        vNum[1] =  true;
        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x,y) -> x.count - y.count
        );

        for(int cur : station[1]){
            pq.add(new Node(1,cur,1));
            vCube[cur] = true;
        }

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(cur.num == 1 && n == 1){
                System.out.println(1);
                return;
            }

            for(int nextNode : cube[cur.cube]){

                if(nextNode == n){
                    System.out.println(cur.count + 1);
                    return;
                }

                if(!vNum[nextNode]){
                    vNum[nextNode] = true;
                    pq.add(new Node(nextNode,cur.cube,cur.count + 1));

                    for(int nextCube : station[nextNode]){
                        if(!vCube[nextCube]){
                            vCube[nextCube] = true;
                            pq.add(new Node(nextNode,nextCube,cur.count+1));
                        }
                    }
                }

            }
        }

        System.out.println(-1);
    }
}
class Node{
    int num;
    int cube;
    int count;

    Node(int num,int cube,int count){
        this.num = num;
        this.cube = cube;
        this.count = count;
    }
}
