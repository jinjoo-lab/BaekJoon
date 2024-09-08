import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[] start;
    static int[] end;
    static int[] minus;

    static int result = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());

        start = new int[n+1];
        end = new int[n+1];
        minus = new int[n+1];

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 1 ; i <= n ; i++) {
            start[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 1 ; i <= n ; i++) {
            end[i] = Integer.parseInt(st.nextToken());

            minus[i] = end[i] - start[i];
        }

        while(!isIt()) {
            go();
        }
        System.out.println(result);

        br.close();
    }

    static void print() {
        for(int i= 1 ; i<= n ; i++) {
            System.out.print(minus[i]+" ");
        }
        System.out.println();
    }

    static boolean isIt() {
        for(int i = 1 ; i <= n ; i++) {
            if(minus[i] != 0)
                return false;
        }

        return true;
    }

    static void go() {
        int past = 1;
        int go = minus[1];
        int idx = 1;
        int w = skrr(1);

        while(idx <= n) {
            int tmpW = skrr(idx);

            if(w == tmpW) {
                if(w == 1) {
                    go = Math.min(go, minus[idx]);
                }else if(w == -1) {
                    go = Math.max(go, minus[idx]);
                }
                idx++;
            }

            else {
                if(w == 1 || w == -1) {
                    for(int i = past ; i <= idx - 1 ; i++) {
                        minus[i] -= go;
                    }
                    result += Math.abs(go);
                }
                w = tmpW;
                go = minus[idx];
                past = idx;
                idx++;
            }
        }

        for(int i = past ; i <= idx - 1 ; i++) {
            minus[i] -= go;
        }
        result += Math.abs(go);
    }

    static int skrr(int idx) {
        if(minus[idx] == 0)
            return 0;
        else if(minus[idx] >= 1)
            return 1;
        else
            return -1;
    }
}
