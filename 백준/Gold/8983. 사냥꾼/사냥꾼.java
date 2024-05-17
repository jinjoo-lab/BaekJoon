import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int n,m,k;

    static int[] shooter;

    static int[][] pig;

    static boolean[] catchPig;
    static int count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        shooter = new int[n];
        st = new StringTokenizer(br.readLine()," ");
        for(int i = 0 ; i < n ; i++) {
            shooter[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(shooter);

        pig = new int[m][2];
        catchPig = new boolean[m];

        for(int i = 0 ; i < m ; i++){
            st = new StringTokenizer(br.readLine()," ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            pig[i][0] = x;
            pig[i][1] = y;
        }

        Arrays.sort(pig,(x,y) -> {
            if(x[0] == y[0])
                return x[1] - y[1];

            return x[0] - y[0];
        });

        for(int i = 0 ; i < m ; i++) {
            int curX = pig[i][0];
            int curY = pig[i][1];

            int s = 0;
            int e = n - 1;
            int mid = 0;
            while(s <= e) {
                mid = (s + e) / 2;

                if(canCatch(curX,curY,shooter[mid])) {
                    catchPig[i] = true;
                    break;
                }

                if(curX < shooter[mid]) {
                    e = mid - 1;
                }else{
                    s = mid + 1;
                }
            }
        }

        System.out.println(count());


        br.close();
    }

    static void printPig(){
        for(int i = 0 ; i < m ; i++){
            System.out.println(pig[i][0]+" "+pig[i][1]);
        }
        System.out.println();
    }

    static int count() {

        int re = 0;

        for(int i = 0 ; i < m ; i++) {
            if(catchPig[i])
                re++;
        }

        return re;
    }



    static boolean canCatch(int x,int y,int s) {
        int dis = Math.abs(x - s) + y;

        return dis <= k;
    }
}
