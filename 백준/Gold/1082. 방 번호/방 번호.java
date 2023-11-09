import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int[] result = new int[51];
    static int[] num;

    static int minIdx = 0;
    static int minCost = 51;
    static int minIdx2 = 0;
    static int minCost2 = 51;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        num = new int[n];
        for(int i=0;i<n;i++){
            num[i] = Integer.parseInt(st.nextToken());

            if(i > 0 && minCost >= num[i]){
                minCost = num[i];
                minIdx = i;
            }

            if(minCost2 >= num[i]){
                minCost2 = num[i];
                minIdx2 = i;
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());


        if(minCost > m){
            System.out.println(0);
            return;
        }

        int size = 0;
        int tmp = m;
        if(minIdx2 == 0){
            tmp -= minCost;
            result[size] = minIdx;
            size += 1;
            while(tmp >= minCost2){
                tmp -= minCost2;
                result[size] = minIdx2;
                size += 1;
            }

            for(int i=0;i<size;i++){
                for(int j=n-1;j>=1;j--){
                    if(tmp >= num[j] - num[result[i]]){
                        tmp -= num[j] - num[result[i]];
                        result[i] = j;
                        break;
                    }
                }
            }
        }else{
            while(tmp >= minCost2){
                tmp -= minCost2;
                result[size] = minIdx2;
                size += 1;
            }

            for(int i=0;i<size;i++){
                for(int j=n-1;j>=1;j--){
                    if(tmp >= num[j] - num[result[i]]){
                        tmp -= num[j] - num[result[i]];
                        result[i] = j;
                        break;
                    }
                }
            }
        }



        print(size);
        br.close();
    }

    static void print(int size){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<size;i++){
        sb.append(result[i]);
        }

        System.out.println(sb);
    }
}

