import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int m = 0;

    static int r = 0;

    static int result = 0;

    static int[] words;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        words = new int[n+1];


        for(int i=1;i<=n;i++){
            char[] arr = bf.readLine().toCharArray();

            for(int j=0 , size = arr.length; j < size ; j++){
                words[i] |= 1 << (arr[j] - 'a');
            }
        }


        if(m < 5) {
            System.out.println(0);
            return;
        }

        if(m == 26){
            System.out.println(n);
            return;
        }

        int v = (1 << 'a' - 'a') | (1 << 'n' - 'a') | 1 << ('t' - 'a') | 1 << ('i' - 'a') | 1 << ('c' - 'a');

        r = m - 5;


        go(0,0,v);
        System.out.println(result);
        bf.close();
    }

    static void go(int depth,int start,int v){

        if(depth == r){

            int count = 0;

            for(int i=1;i<=n;i++){


                if((words[i] & v) == words[i]) {
                    count++;
                }
            }

            result = Math.max(result,count);
            return;
        }


        for(int i=start;i<26;i++){

            if(i == 'a' - 'a' || i == 'n' - 'a' || i == 't' - 'a' || i == 'i' - 'a' || i == 'c' - 'a')
                continue;

            if((v & 1 << i) != 0)
                continue;

            go(depth + 1 , i+1,v | 1 << i);
        }
    }
}
