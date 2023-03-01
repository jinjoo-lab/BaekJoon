import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int k = 0;
    static int[][][] board = new int[1001][1001][3];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine()," ");
        k = Integer.parseInt(st.nextToken());
        for(int i=1;i<=n;i++)
        {
            String[] line = br.readLine().split("");
            for(int j=1;j<=m;j++)
            {
                if(line[j-1].equals("J"))
                {
                    board[i][j][0] = board[i][j-1][0] + 1;
                    board[i][j][1] = board[i][j-1][1];
                    board[i][j][2] = board[i][j-1][2];
                }

                else if(line[j-1].equals("O"))
                {
                    board[i][j][0] = board[i][j-1][0];
                    board[i][j][1] = board[i][j-1][1]+1;
                    board[i][j][2] = board[i][j-1][2];
                }
                else{
                    board[i][j][0] = board[i][j-1][0];
                    board[i][j][1] = board[i][j-1][1];
                    board[i][j][2] = board[i][j-1][2]+1;
                }
            }
        }
        for(int i=2;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                board[i][j][0] += board[i-1][j][0];
                board[i][j][1] += board[i-1][j][1];
                board[i][j][2] += board[i-1][j][2];
            }
        }

        for(int i=1;i<=k;i++)
        {
            st = new StringTokenizer(br.readLine()," ");
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int lx =  Integer.parseInt(st.nextToken());
            int ly =  Integer.parseInt(st.nextToken());

            int num1 = 0;
            int num2 = 0;
            int num3 = 0;

            num1 = board[lx][ly][0] - board[sx-1][ly][0] - board[lx][sy-1][0] + board[sx-1][sy-1][0];
            num2 = board[lx][ly][1] - board[sx-1][ly][1] - board[lx][sy-1][1] + board[sx-1][sy-1][1];
            num3 = board[lx][ly][2] - board[sx-1][ly][2] - board[lx][sy-1][2] + board[sx-1][sy-1][2];
            bw.write(num1+" "+num2+" "+num3+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }


}