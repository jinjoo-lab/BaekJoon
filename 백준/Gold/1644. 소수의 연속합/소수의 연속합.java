import java.io.*;
import java.util.*;

public class Main {
    static int t = 0;
    static int n = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        boolean[] prime = new boolean[n+1];

        for(int i=2;i<=Math.sqrt(n);i++){
            if(!prime[i]){
                int j= 2;

                while(i * j <= n){
                    prime[i * j] = true;
                    j = j + 1;
                }
            }
        }

        ArrayList<Integer> primeNumber = new ArrayList<>();

        for(int i=2;i<=n;i++){
            if(!prime[i]){
                primeNumber.add(i);
            }
        }

        int left = 0;
        int right = 0;
        int count = 0;
        int number = 0;

        while(left <= right && right < primeNumber.size()){
            int rN = primeNumber.get(right);
            number += rN;
            if(number < n){
                right += 1;
            }

            else{
                if(number == n){
                    count = count + 1;
                }
                number -= primeNumber.get(left);
                left = left + 1;
                number -= rN;
            }

        }

        System.out.println(count);
        br.close();
    }



}

