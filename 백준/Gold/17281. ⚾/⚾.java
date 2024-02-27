
import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;

    static int result = 0;
    static int[] player;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());

        board = new int[n+1][10];
        player = new int[10];
        player[4] = 1;

        for(int i = 1 ; i <= n ; i++){
            st = new StringTokenizer(br.readLine()," ");

            for(int j = 1 ; j <= 9 ; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        per(1,0);
        System.out.println(result);
        br.close();
    }

    static void print(){
        for(int i = 1 ; i <= 9 ; i++){
            System.out.print(player[i]+" ");
        }
        System.out.println();
    }

    static void per(int idx ,int v){

        if(idx == 10){
            result = Math.max(result,play());
            return;
        }

        for(int i = 2 ; i <= 9 ; i++){

            if((v & 1 << i) != 0)
                continue;

            player[idx] = i;

            if(idx == 3){
                per(idx + 2 , v | 1 << i);
            }else{
                per(idx + 1 , v | 1 << i);
            }
        }
    }

    static int hit(boolean[] base, int what){
        int count = 0;

        for(int i = 3 ; i >=1 ; i--){
            if(base[i]){
                int next = i + what;

                if(next > 3){
                    count++;
                }else{
                    base[next] = true;
                }

                base[i] = false;
            }
        }

        if(what < 4){
            base[what] = true;
        }else{
            count ++;
        }

        return count;
    }

    static int play(){

        boolean[] base = new boolean[4];
        int result = 0;
        int outCount = 0;
        int playerIdx = 1;
        int inning = 1;

        while(inning <= n){

            int curV = board[inning][player[playerIdx]];

            if(curV == 0){
                outCount ++;
            }else{
                result += hit(base,curV);
            }

            playerIdx = nextPlayer(playerIdx);

            if(outCount == 3){
                outCount = 0;
                Arrays.fill(base,false);
                inning++;
            }
        }


        return result;
    }
    static int nextPlayer(int idx) {

        if (idx + 1 == 10)
            return 1;

        return idx + 1;
    }
}
