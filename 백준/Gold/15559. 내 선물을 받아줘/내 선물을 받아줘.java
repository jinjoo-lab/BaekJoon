import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int m = 0;

    static int[][] board;

    static int[][] v;

    static int[] dx = {-1,1,0,0}; // N S W E
    static int[] dy = {0,0,-1,1};

    static HashMap<Integer,Integer> root = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board= new int[n+1][m+1];
        v = new int[n+1][m+1];

        for(int i=1;i<=n;i++){
            String line = bf.readLine();

            for(int j=1;j<=m;j++){
                char cur = line.charAt(j-1);

                if(cur == 'N'){
                    board[i][j] = 0;
                }else if(cur == 'S'){
                    board[i][j] = 1;
                }else if(cur == 'W'){
                    board[i][j] = 2;
                }else{
                    board[i][j] = 3;
                }
            }
        }

        int num = 1;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(v[i][j] ==  0){
                    root.put(num,num);
                    bfs(i,j,num);
                    num +=1;
                }
            }
        }

        HashSet<Integer> set = new HashSet<>();

        for(int i=1;i<num;i++){
            set.add(root.get(i));
        }
        System.out.println(set.size());


        bf.close();
    }

    static void bfs(int i,int j,int num){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(i,j));
        v[i][j] = num;

        while(!q.isEmpty()){
            Node cur = q.poll();

            int nx = cur.x + dx[board[cur.x][cur.y]];
            int ny = cur.y + dy[board[cur.x][cur.y]];

            if(v[nx][ny] == 0){
                v[nx][ny] = num;
                q.add(new Node(nx,ny));
            }else{
                union(num,v[nx][ny]);
                break;
            }
        }
    }

    static int find(int x){
        if(x == root.get(x))
            return root.get(x);

        // return root[x] = find(root[x]);
        root.put(x,find(root.get(x)));

        return root.get(x);
    }

    static void union(int x,int y){
        x = root.get(x);
        y = root.get(y);

        if(x < y){
            root.put(y,x);
        }else{
            root.put(x,y);
        }
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
