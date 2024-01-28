import java.util.*;
import java.io.*;
public class Main {
    static int n = 0;

    static boolean[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());

        board = new boolean[101][101];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(bf.readLine(), " ");

            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            int tmp = 0;

            for(int row = x ; row < x + 10 ; row++){
                for(int cal = y ; cal < y + 10 ; cal++){

                    if(board[row][cal])
                        continue;

                    board[row][cal] = true;
                }
            }
        }

        int count = 0;

        for(int r=1;r<=100;r++){
            for(int c=1;c<=100;c++){
                if(board[r][c]) {
                    count += 1;
                }
            }
        }

        //print();
        System.out.println(count);

        bf.close();
    }

    static void print(){
        for(int r=1;r<=100;r++){
            for(int c=1;c<=100;c++){
                if(board[r][c])
                    System.out.print(1+" ");

                else
                    System.out.print(0+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}





