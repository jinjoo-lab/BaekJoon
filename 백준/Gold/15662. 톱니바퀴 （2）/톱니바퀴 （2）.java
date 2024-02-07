import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;

    static int m = 0;

    static int[] chain;

    static int[] turnTable;

    static boolean[] v;

    static void printChain(){for(int i=1;i<=n;i++){
        System.out.println(Integer.toBinaryString(chain[i]));
    }}

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());

        chain = new int[n+1];
        turnTable = new int[n+1];
        v = new boolean[n+1];

        for(int i=1; i <= n ; i ++){
            char[] arr = br.readLine().toCharArray();

            int idx = 0;

            for(int j= arr.length - 1 ;j >= 0;j--){

                if(arr[j] == '1') {
                    chain[i] |= (1 << idx);
                }

                idx++;
            }
        }

        st = new StringTokenizer(br.readLine()," ");
        m = Integer.parseInt(st.nextToken());

        for(int i=1; i <= m ; i++){
            st = new StringTokenizer(br.readLine()," ");

            int num = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            Arrays.fill(turnTable,0);
            Arrays.fill(v,false);


            findAll(num,dir);


            for(int j= 1; j <= n ; j++){

                if(turnTable[j] == 1){
                    turn(true,j);
                }else if(turnTable[j] == -1){
                    turn(false,j);
                }
            }

        }

        int count = 0;


        for(int i=1 ; i<=n; i++){
            int tmpCount = (chain[i] & ( 1 << 7)) != 0 ? 1 : 0;
            count += tmpCount;
        }

        System.out.println(count);
        br.close();
    }

    static void turn(boolean isTime,int cur){

        if(isTime){

            int zero = (chain[cur] & (1 << 0));

            if(zero == 0){
                chain[cur] >>= 1;
            }else{
                chain[cur] >>= 1;
                chain[cur] |= (1 << 7);
            }

        }else{

            int last = (chain[cur] & (1 << 7));
            chain[cur] &= ~(1 << 7);

            if(last == 0){
                chain[cur] <<= 1;
            }else{
                chain[cur] <<= 1;
                chain[cur] |= (1 << 0);
            }
        }

    }

    static void findAll(int num , int dir){
        turnTable[num] = dir;
        v[num] = true;

        if(num + 1 <= n && !v[num+1]){
            v[num+1] = true;
            int curRight = (chain[num] & (1 << 5)) != 0 ? 1 : 0;
            int tmpLeft = (chain[num + 1] & (1 << 1)) != 0 ? 1 : 0;

           if(curRight != tmpLeft){
               findAll(num + 1, changeDir(dir));
           }
        }

        if(num -1 >= 1 && !v[num-1]){
            v[num-1] = true;
            int curLeft = (chain[num] & (1 << 1)) != 0 ? 1 : 0;
            int tmpRight = (chain[num - 1] & (1 << 5)) != 0 ? 1 : 0;

            if(curLeft != tmpRight){
                findAll(num - 1, changeDir(dir));
            }
        }

    }

    static int changeDir(int dir){
        if(dir == 1)
            return -1;

        return 1;
    }
}



