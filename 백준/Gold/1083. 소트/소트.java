import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        arr = new int[n];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }


        int count = Integer.parseInt(br.readLine());

        for(int i=0;i<n;i++){
            int maxV = arr[i];
            int maxIdx = i;

            for(int j=i+1;j<n;j++){
                if(maxV < arr[j] && (j - i) <= count){
                    maxV = arr[j];
                    maxIdx = j;
                }
            }

            for(int j=maxIdx;j>i;j--){
                swap(j,j-1);
            }
            count -= (maxIdx - i);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            sb.append(arr[i]+" ");
        }
        System.out.println(sb);

        br.close();
    }

    static void print(){
        for(int i=0;i<n;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    static void swap(int i,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
class Node{
    int idx;
    int v;

    Node(int idx,int v){
        this.idx = idx;
        this.v = v;
    }
}
