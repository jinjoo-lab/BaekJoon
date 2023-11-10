import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[] num;
    static int[] past;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        num = new int[n];
        past = new int[n];
        result = new int[n];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<n;i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        result[0] = num[0];
        int i = 1;
        int j= 0;

        int maxIdx= 0;

        while(i < n){

            if(result[j] < num[i]){
                result[j+1] = num[i];
                past[i] = j + 1;
                j = j + 1;

            }else{
                int idx = bs(0,j,num[i]);
                result[idx] = num[i];
                past[i] = idx;
            }

            i = i + 1;

        }

        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        sb.append(j+1+"\n");

        int find = j;
        for(int k= n-1;k>=0;k--){
            if(find == past[k]){
                stack.push(num[k]);
                find = find - 1;
            }
        }

        while(!stack.isEmpty()){
            sb.append(stack.pop()+" ");
        }
        sb.append("\n");

        System.out.print(sb);
        br.close();
    }

    static void print(){
        for(int i=0;i<n;i++){
            System.out.print(past[i]+" ");
        }
        System.out.println();
    }

    static void print2(){
        for(int i=0;i<n;i++){
            System.out.print(result[i]+" ");
        }
        System.out.println();
    }

    static int bs(int l,int r,int t){
        int mid;

        while(l < r){
            mid = (l + r) / 2;

            if(result[mid] < t){
                l = mid + 1;
            }else{
                r = mid;
            }

        }

        return r;
    }
}

