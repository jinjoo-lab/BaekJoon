import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int m = 0;


    static int[][] dis;

    static boolean[] v;

    static ArrayList<Node> chicken = new ArrayList<>();
    static ArrayList<Node> home = new ArrayList<>();

    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(bf.readLine()," ");

            for(int j=1;j<=n;j++){
                int  cur = Integer.parseInt(st.nextToken());

                if(cur == 1)
                    home.add(new Node(i,j));

                else if(cur == 2){
                    chicken.add(new Node(i,j));
                }
            }
        }

        int size = chicken.size();
        int homeSize = home.size();

        dis = new int[homeSize+1][size+1];

        for(int i=0;i<homeSize;i++){
            for(int j=0;j<size;j++){
                dis[i][j] = dis(home.get(i),chicken.get(j));
            }
        }

        v = new boolean[size + 1];

        com(0,0,size,m);
        System.out.println(result);
        bf.close();
    }

    static void com(int depth,int count,int n,int r){

        if(depth == r){

            int total = 0;

            for(int i=0;i<home.size();i++){

                int d = Integer.MAX_VALUE;

                for(int j=0;j<n;j++){
                    if(v[j]){
                        d = Math.min(d,dis[i][j]);
                    }
                }

                total += d;
            }


            result = Math.min(total,result);
            return;
        }

        for(int i=count;i<n;i++){
            if(!v[i]){
                v[i] = true;
                com(depth + 1,i+1,n,r);
                v[i] = false;
            }
        }

    }

    static int dis(Node tmp,Node tmp2){
        return Math.abs(tmp.x - tmp2.x) + Math.abs(tmp.y - tmp2.y);
    }
}
class Node{
    int x;
    int y;

    Node(int x,int y){
        this.x = x;
        this.y = y;
    }
}