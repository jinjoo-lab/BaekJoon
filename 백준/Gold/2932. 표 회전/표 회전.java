import java.util.*;
import java.io.*;

public class Main {

    static int n,m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] go = new int[m+1][3];

        HashSet<Integer> set = new HashSet<>();

        for(int i = 1 ; i <= m ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int num = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            go[i][0] = num;
            go[i][1] = x - 1;
            go[i][2] = y - 1;

            set.add(num);
        }

        int[][] move = new int[set.size() + 1][3];
        int idx = 1;

        for(int next : set) {
            move[idx][0] = next;
            move[idx][1] = (next - 1) / n;
            move[idx][2] = (next - 1) % n;

            idx++;
        }

        for(int i = 1 ; i <= m ; i++) {
            int cur = go[i][0];
            int nx = go[i][1];
            int ny = go[i][2];


            int mIdx = 0;

            for(int j = 1, size = set.size() + 1 ; j < size ; j++) {
                if(move[j][0] == cur) {
                    mIdx = j;
                    break;
                }
            }

            int mx = nx - move[mIdx][1];
            int my = ny - move[mIdx][2];

            if(mx < 0)
                mx += n;

            if(my < 0)
                my += n;

            System.out.println(mx + my);

            for(int j = 1, size = set.size() + 1 ; j < size ; j++) {
                if(move[j][1] == move[mIdx][1]) {
                    move[j][2] = (move[j][2] + my) % n;
                }
            }

            for(int j = 1, size = set.size() + 1 ; j < size ; j++) {
                if(move[j][2] == move[mIdx][2]) {
                    move[j][1] = (move[j][1] + mx) % n;
                }
            }
        }

        br.close();
    }


}
