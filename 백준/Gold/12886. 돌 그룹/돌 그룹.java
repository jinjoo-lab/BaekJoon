import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int k = 0;

    static boolean[][] v;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        v = new boolean[1500][1500];


        bfs();
        br.close();
    }

    static void bfs(){
        Queue<Group> q = new LinkedList<>();
        q.add(new Group(n,m,k));
        v[Math.min(n,Math.min(m,k))][Math.max(n,Math.max(m,k))] = true;

        while(!q.isEmpty()){
            Group cur = q.poll();

            if(cur.x == cur.y && cur.y == cur.z){
                System.out.println(1);
                return;
            }

            // 1 ,2
            if(cur.x != 0 && cur.y !=0){
                int nx = 0;
                int ny = 0;

                if(cur.x < cur.y){
                    nx = cur.x + cur.x;
                    ny = cur.y - cur.x;
                }
                else if(cur.x > cur.y){
                    nx = cur.x - cur.y;
                    ny = cur.y + cur.y;
                }

                int min = Math.min(Math.min(nx,ny),cur.z);
                int max = Math.max(Math.max(nx,ny),cur.z);

                if(!v[min][max]){
                    v[min][max] = true;
                    q.add(new Group(nx,ny,cur.z));
                }
            }
            // 1, 3
            if(cur.x !=0 && cur.z !=0) {
                int nx = 0;
                int nz = 0;

                if (cur.x < cur.z) {
                    nx = cur.x + cur.x;
                    nz = cur.z - cur.x;
                } else if (cur.x > cur.z) {
                    nx = cur.x - cur.z;
                    nz = cur.z + cur.z;
                }

                int min = Math.min(Math.min(nx,cur.y),nz);
                int max = Math.max(Math.max(nx,cur.y),nz);

                if(!v[min][max]){
                    v[min][max] = true;
                    q.add(new Group(nx,cur.y,nz));
                }
            }
            // 2, 3
            if(cur.y!=0 &&cur.z !=0) {
                int ny = 0;
                int nz = 0;
                if (cur.y < cur.z) {
                    ny = cur.y + cur.y;
                    nz = cur.z - cur.y;
                } else if (cur.y > cur.z) {
                    ny = cur.y - cur.z;
                    nz = cur.z + cur.z;
                }

                int min = Math.min(Math.min(cur.x,ny),nz);
                int max = Math.max(Math.max(cur.x,ny),nz);

                if(!v[min][max]){
                    v[min][max] = true;
                    q.add(new Group(cur.x,ny,nz));
                }
            }
        }

        System.out.println(0);
    }
}
class Group{
    int x;
    int y;
    int z;

    Group(int x,int y,int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
