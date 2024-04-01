
import java.util.*;
import java.io.*;

public class Main {

    static int n,k,d;

    static Node[] arr = new Node[10001];

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for(int i = 1 ; i <= k ; i++){
            st = new StringTokenizer(br.readLine()," ");

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            arr[i] = new Node(s,e,d);
        }

        System.out.println(bs());

        br.close();
    }

    static boolean count(int num){

        long count = 0;

        for(int i = 1 ; i <= k ; i++){
            int tmpS = arr[i].s;
            int tmpE = arr[i].e;
            int tmpD = arr[i].d;

            if(num >= tmpS) {

                int end = Math.min(num,tmpE);

                count += (end - tmpS) / tmpD + 1;
            }
        }

        return count >= d;
    }

    static int bs(){
        int l = 1;
        int r = 1000_000;

        while(l <= r){
            int mid = (l + r) / 2;

            boolean tmpCount = count(mid);

            if(tmpCount){
                r = mid - 1;
                continue;
            }

            l = mid + 1;

        }

        return l;
    }

    static class Node{
        int s;
        int e;
        int d;

        Node(int s,int e,int d){
            this.s = s;
            this.e = e;
            this.d = d;
        }
    }
}
