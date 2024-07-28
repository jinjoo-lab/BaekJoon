import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static long a;
    static Node[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        a = Long.parseLong(st.nextToken());

        arr = new Node[n+1];

        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int type = Integer.parseInt(st.nextToken());
            int tmpA = Integer.parseInt(st.nextToken());
            int tmpH = Integer.parseInt(st.nextToken());

            arr[i] = new Node(type, tmpA, tmpH);
        }

        long result = Long.MAX_VALUE;
        long max = Long.MAX_VALUE - 1;
        long min = 1;
        long mid = 0;

        while(min <= max) {
            mid = (max + min) / 2;

            boolean tmp = go(mid);

            if(tmp) {
                result = Math.min(result,mid);
                max = mid - 1;
            }else {
                min = mid + 1;
            }
        }
        System.out.println(result);
        br.close();
    }

    static boolean go(long curH) {
        long tmpA = a;
        long tmpH = curH;

        for(int i = 1 ; i <= n ; i++) {

            long monA = arr[i].a;
            long monH = arr[i].h;

            if(arr[i].type == 1) {
                long tmpTurn = monH / tmpA + (monH % tmpA == 0 ? 0 : 1);

                if(tmpH - ((tmpTurn - 1) * monA) <= 0) {
                    return false;
                }

                tmpH -= ((tmpTurn - 1) * monA);
            }else {
                tmpA += monA;
                tmpH = Math.min(curH, tmpH + monH);
            }

        }

        boolean result = tmpH >= 0 ? true : false;

        return result;
    }

    static class Node {
        int type;
        int a;
        int h;

        Node(int type, int a, int h) {
            this.type = type;
            this.a = a;
            this.h = h;
        }
    }
}
