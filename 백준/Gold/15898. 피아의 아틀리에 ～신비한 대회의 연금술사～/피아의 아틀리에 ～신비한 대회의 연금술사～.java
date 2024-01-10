import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;

    static int[][][] board;

    static int[] ingredient;
    static boolean[] v;

    static int[][][][] pos;
    static int[][][][] color;

    static int[] sx = {1,1,2,2};
    static int[] sy = {1,2,1,2};

    static int re = 0;

    static int[] tmp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(st.nextToken());

        ingredient = new int[n+1];
        v = new boolean[n+1];

        pos = new int[n+1][5][5][5];
        color = new int[n+1][5][5][5];

        for(int k=1;k<=n;k++){

            for(int i = 1; i<=4 ;i ++){
                st = new StringTokenizer(bf.readLine(), " ");

                for(int j=1;j<=4;j++){
                    pos[k][i][j][1] = Integer.parseInt(st.nextToken());
                }
            }


            for(int i = 1; i<=4 ;i ++){
                st = new StringTokenizer(bf.readLine(), " ");

                for(int j=1;j<=4;j++){
                    char cur = st.nextToken().charAt(0);

                    if(cur == 'R'){
                        color[k][i][j][1] = 1;
                    }else if(cur == 'B'){
                        color[k][i][j][1] = 2;
                    }else if(cur == 'G'){
                        color[k][i][j][1] = 3;
                    }else if(cur == 'Y'){
                        color[k][i][j][1] = 4;
                    }
                }
            }
        }

        for(int i=1;i<=n;i++){
            turn(i);
        }


        peek(1);
        System.out.println(re);

        bf.close();
    }

    static void peek(int num){

        if(num > 3){
            make();
            return;
        }

        for(int i= 1;i<=n;i++){
            if(!v[i]){
                v[i] = true;
                ingredient[num] = i;
                peek(num+1);
                v[i] = false;
            }
        }
    }

    static void make() {
        for(int f=1;f<=4;f++){
            for(int s=1;s<=4;s++){
                for(int t=1;t<=4;t++){
                    printAll(f,s,t);
                }
            }
        }
    }

    static void printAll(int t1,int t2,int t3){

        board = new int[6][6][2];

        for(int pos1 = 0; pos1 < 4; pos1 ++){
            for(int pos2 = 0 ; pos2 < 4 ; pos2 ++){
                for(int pos3 = 0; pos3 < 4; pos3 ++){
                    print(pos1,ingredient[1], t1);
                    print(pos2,ingredient[2], t2);
                    print(pos3,ingredient[3], t3);

                    result();
                }
            }
        }
    }

    static void result(){

        tmp = new int[5];

        for(int i=1;i<=5;i++){
            for(int j=1;j<=5;j++){
                tmp[board[i][j][1]] += board[i][j][0];
                board[i][j][0] = board[i][j][1] = 0;
            }
        }

        re = Math.max(re,(7*tmp[1] + 5*tmp[2] + 3*tmp[3] + 2*tmp[4]));
    }

    static void print(int cur,int num,int t){

        int px = 1;
        int py = 1;

        for(int i=sx[cur];i<=sx[cur] + 3;i++){
            for(int j = sy[cur];j <= sy[cur] + 3;j++){

                board[i][j][0] = sum(board[i][j][0],pos[num][px][py][t]);
                board[i][j][1] = color(board[i][j][1],color[num][px][py][t]);
                py += 1;
            }
            px += 1;
            py = 1;
        }
    }

    static void turn(int num){

        for(int k=2;k<=4;k++) {
            for (int i = 1; i <= 4; i++) {
                for (int j = 1; j <= 4; j++) {
                    pos[num][i][j][k] = pos[num][j][5 - i][k-1];
                    color[num][i][j][k] = color[num][j][5 - i][k-1];
                }
            }
        }

    }

    static int sum(int num,int tmp){
        if(num + tmp < 0)
            return 0;

        else if(num + tmp > 9)
            return 9;

        return num + tmp;
    }

    static int color(int num,int tmp){
        if(tmp == 0)
            return num;

        return tmp;
    }
}