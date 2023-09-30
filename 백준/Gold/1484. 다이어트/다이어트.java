import java.io.*;
import java.util.*;

public class Main {

    static int n = 0;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        int left = 1;
        int right = 2;

        while(true){
            if(left == right)
                break;

            int l = left * left;
            int r = right * right;
            int minus = r - l;
            if(minus >= n){
                if(minus == n) {
                    list.add(right);
                }

                left = left + 1;
            }

            else{
                right = right + 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int tmp : list){
            sb.append(tmp+"\n");
        }

        if(list.isEmpty())
            sb.append(-1+"\n");

        System.out.print(sb);

        br.close();
    }
}