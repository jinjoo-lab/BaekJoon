import java.util.*;
import java.io.*;
public class Main {
    static int n = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());

        int count = n*2 -1;

        for(int i=1;i<=n;i++){
            for(int j=i;j>1;j--){
                System.out.print(" ");
            }

            for(int j=1;j<=count;j++){
                System.out.print("*");
            }
            System.out.println();
            count -= 2;
        }


        bf.close();
    }
}




