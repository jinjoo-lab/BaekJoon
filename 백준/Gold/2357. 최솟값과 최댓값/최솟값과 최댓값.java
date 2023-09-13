import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static long[] data;

    static long[] tree;
    static long[] tree2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        data = new long[n];

        for(int i=0;i<n;i++){
            data[i] = Integer.parseInt(br.readLine());
        }

        tree = new long[n * 4];
        tree2 = new long[n * 4];

        makeST1(0,n-1,1);
        makeST2(0,n-1,1);

        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken()) -1;
            int end = Integer.parseInt(st.nextToken()) -1;

            long min = findMin(0,n-1,start,end,1);
            long max = findMax(0,n-1,start,end,1);

            sb.append(min+" "+max+"\n");
        }
        System.out.println(sb);
        br.close();
    }

    static long makeST1(int left , int right , int idx){

        if(left == right){
            return tree[idx] = data[left];
        }

        int mid = (left + right) / 2;

        return tree[idx] = Math.min(makeST1(left,mid,idx*2) ,makeST1(mid+1,right,idx*2 + 1));
    }

    static long makeST2(int left,int right,int idx){
        if(left == right){
            return tree2[idx] = data[left];
        }

        int mid = (left + right) / 2;

        return tree2[idx] = Math.max(makeST2(left,mid,idx*2) ,makeST2(mid+1,right,idx*2 + 1));
    }

    static void print(){
        for(int i=1;i<=4*n;i++){
            System.out.print(tree[i]+" ");
        }
        System.out.println();
    }
    static long findMin(int start,int last,int left,int right,int idx){
        if(right < start || left > last)
            return Long.MAX_VALUE;

        if(left <= start && right >= last)
            return tree[idx];

        int mid = (start + last) / 2;

        long l = findMin(start , mid , left,right,idx*2);
        long r = findMin(mid+1,last,left,right,idx*2+1);

        return Math.min(l,r);
    }

    static long findMax(int start,int last,int left,int right,int idx){
        if(right < start || left > last)
            return -1;

        if(left <= start && right >= last)
            return tree2[idx];


        int mid = (start + last) / 2;

        long l = findMax(start , mid , left,right,idx*2);
        long r = findMax(mid+1,last,left,right,idx*2+1);

        return Math.max(l,r);
    }
}