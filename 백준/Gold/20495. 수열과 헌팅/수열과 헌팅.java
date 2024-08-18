import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static Node[] left;
    static Node[] right;
    static int[][] result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());

        left = new Node[n];
        right = new Node[n];
        result = new int[n+1][2];

        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            left[i-1] = new Node(i,v - c);
            right[i-1] = new Node(i,v + c);
        }

        Arrays.sort(left, Comparator.comparingInt(x -> x.v));
        Arrays.sort(right, Comparator.comparingInt(x -> x.v));


        for(int i = 0 ; i < n ; i++) {
            findL(right[i].idx,right[i].v);
            findR(left[i].idx,left[i].v);
        }

        print();

        br.close();
    }

    static void print() {
        StringBuilder sb = new StringBuilder();

        for(int i = 1 ; i <= n ; i++) {
            sb.append(result[i][0]+" "+result[i][1]+"\n");
        }

        System.out.print(sb.toString());
    }

    static void findL(int idx,int v) {

        int l = 0;
        int r = n - 1;
        int mid = 0;

        while(l <= r) {
            mid = (l + r) / 2;

            if(left[mid].v > v) {
                r = mid - 1;
            }else {
                l = mid + 1;
            }
        }

        result[idx][1] = l;
    }

    static void findR(int idx,int v) {
        int l = 0;
        int r = n - 1;
        int mid = 0;

        while(l <= r) {
            mid = (l + r) / 2;

            if(right[mid].v >= v) {
                r = mid - 1;
            }else {
                l = mid + 1;
            }
        }

        result[idx][0] = l + 1;
    }

    static class Node {
        int idx;
        int v;

        Node(int idx,int v) {
            this.idx = idx;
            this.v = v;
        }
    }
}
