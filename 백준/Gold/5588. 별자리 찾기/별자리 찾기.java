import java.util.*;
import java.io.*;

public class Main {

    static int n,m;

    static int[][] start;
    static ArrayList<Point> dif = new ArrayList<>();
    static HashSet<Point> set = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        start = new int[n+1][2];
        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            start[i][0] = x;
            start[i][1] = y;
        }

        st = new StringTokenizer(br.readLine()," ");
        m = Integer.parseInt(st.nextToken());

        for(int i = 1 ; i <= m ; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            dif.add(new Point(x - start[1][0], y - start[1][1]));

            set.add(new Point(x,y));
        }

        int reX = 0;
        int reY = 0;

        for(Point cur : dif) {

            boolean find = true;
            
            for(int i = 1 ; i <= n ; i++){
                int tmpX = start[i][0] + cur.x;
                int tmpY = start[i][1] + cur.y;

                if(!set.contains(new Point(tmpX,tmpY))){
                    find = false;
                    break;
                }
            }

            if(find) {
                reX = cur.x;
                reY = cur.y;
            }
        }

        System.out.println(reX+" "+reY);

        br.close();
    }

    static class Point {
        int x;
        int y;

        Point(int x,int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            Point objP = (Point) obj;
            return objP.x == this.x && objP.y == this.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x,y);
        }
    }
}
