
import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static String line;

    static int result = Integer.MIN_VALUE;

    static int calCount = 0;

    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());

        line = br.readLine();

        calCount = n / 2;

        v = new boolean[n];


        comb(1);
        System.out.println(result);
        br.close();
    }

    static void comb(int idx){
        if(idx> n - 1) {
            int tmp = go();
            result = Math.max(result,tmp);
            return;
        }

        v[idx] = true;
        comb(idx + 4);
        v[idx] = false;
        comb(idx + 2);
    }

    static int go(){
        String[] tmpArr = line.split("");

        for(int i = 1 ; i < n ; i+=2){

            if(v[i]){
                int v1 = Integer.parseInt(tmpArr[i-1]);
                int v2 = Integer.parseInt(tmpArr[i+1]);

                int tmpSum = cal(v1,v2,tmpArr[i]);

                tmpArr[i-1] = null;
                tmpArr[i] = String.valueOf(tmpSum);
                tmpArr[i+1] = null;
            }
        }

        for(int i = 1 ; i < n ; i+=2){
            if(v[i])
                continue;

            if(tmpArr[i].equals("*")){

                int lidx = i - 1;
                int ridx = i + 1;

                while(tmpArr[lidx] == null){
                    lidx--;
                }
                while(tmpArr[ridx] == null){
                    ridx++;
                }

                int v1 = Integer.parseInt(tmpArr[lidx]);
                int v2 = Integer.parseInt(tmpArr[ridx]);

                tmpArr[i] = String.valueOf(cal(v1,v2,"*"));
                tmpArr[lidx] = null;
                tmpArr[ridx] = null;
            }
        }

        for(int i = 1 ; i < n ; i+=2) {
            if (v[i] || tmpArr[i] == null || tmpArr[i].equals("*"))
                continue;


            int lidx = i - 1;
            int ridx = i + 1;

            while (lidx >= 0 && tmpArr[lidx] == null) {
                lidx--;
            }
            while (ridx <= n -1 && tmpArr[ridx] == null) {
                ridx++;
            }

            if(lidx < 0 || ridx > n -1)
                continue;

            int v1 = Integer.parseInt(tmpArr[lidx]);
            int v2 = Integer.parseInt(tmpArr[ridx]);

            tmpArr[i] = String.valueOf(cal(v1, v2, tmpArr[i]));
            tmpArr[lidx] = null;
            tmpArr[ridx] = null;
        }


        int result = 0;
        for(int i = 0 ; i < n ;i++){
            if(tmpArr[i] != null){
                result += Integer.parseInt(tmpArr[i]);
            }
        }

        return result;
    }

    static int cal(int v1,int v2,String op){
        if(op.equals("+"))
            return v1 + v2;

        else if(op.equals("*"))
            return v1 * v2;

        else
            return v1 - v2;
    }
}
