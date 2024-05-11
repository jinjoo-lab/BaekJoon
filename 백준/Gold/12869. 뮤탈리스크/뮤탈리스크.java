import java.util.*;
import java.io.*;

public class Main {

    static int[] dx = {-9,-9,-3,-3,-1,-1};
    static int[] dy = {-3,-1,-9,-1,-9,-3};
    static int[] dz = {-1,-3,-1,-9,-3,-9};
    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        int sx = 0,sy = 0,sz = 0;

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 1 ; i <= n ; i++){
            if(i == 1)
                sx = Integer.parseInt(st.nextToken());

            else if(i == 2)
                sy = Integer.parseInt(st.nextToken());

            else
                sz = Integer.parseInt(st.nextToken());
        }

        Node start = new Node(sx,sy,sz,0);
        boolean[][][] v = new boolean[61][61][61];
        Queue<Node> q = new ArrayDeque<>();
        q.add(start);
        v[sx][sy][sz] = true;

        int result = 0;

        while(!q.isEmpty()){
            Node cur = q.poll();

            if(cur.x == 0 && cur.y == 0 && cur.z == 0){
                result = cur.c;
                break;
            }

            for(int i = 0 ; i < 6 ; i++){
                int nx = cur.x + dx[i] > 0 ? cur.x + dx[i] : 0;
                int ny = cur.y + dy[i] > 0 ? cur.y + dy[i] : 0;
                int nz = cur.z + dz[i] > 0 ? cur.z + dz[i] : 0;

                if(!v[nx][ny][nz]){
                    v[nx][ny][nz] = true;
                    q.add(new Node(nx,ny,nz,cur.c + 1));
                }
            }
        }

        System.out.println(result);
        br.close();
    }

    static class Node{
        int x;
        int y;
        int z;

        int c;

        Node(int x,int y,int z,int c){
            this.x = x;
            this.y = y;
            this.z = z;
            this.c = c;
        }
    }
}
