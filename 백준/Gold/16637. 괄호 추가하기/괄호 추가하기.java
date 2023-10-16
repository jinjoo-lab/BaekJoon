import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static char[] arr;

    static int result = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = br.readLine().toCharArray();

        travel(2,arr[0] - '0');
        System.out.println(result);
        br.close();
    }
    // cur = idx , num = sum
    static void travel(int cur,int num){
        if(cur >= n){
            result = Math.max(result,num);
            return;
        }


        travel(cur + 2,cal(num,arr[cur] - '0',arr[cur-1]));

        if(cur + 2 < n){
            int right = cal(arr[cur] - '0',arr[cur+2] - '0',arr[cur+1]);
            int left = cal(num,right,arr[cur-1]);
            travel(cur + 4,left);
        }
    }

    static int cal(int a,int b,char what){
        if(what == '+'){
            return a + b;
        }else if(what == '-'){
            return a - b;
        }else{
            return a * b;
        }
    }
}
