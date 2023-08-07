import java.util.*;
import java.io.*;

public class Main {
    static int r = 0;
    static int c = 0;
    static int k = 0;

    static int[][] board = new int[101][101];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i=1;i<=3;i++)
        {
            st = new StringTokenizer(br.readLine()," ");
            for(int j=1;j<=3;j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int row = 3;
        int col = 3;

        if(board[r][c] == k) {
            System.out.println(0);
            return;
        }

        boolean ans = false;
        int sec = 1;
        for(int i=1;i<=100;i++)
        {
            if(row >= col)
            {
                col = calR(row,col);
            }

            else{
                row = calC(row,col);
            }

            if(board[r][c] == k)
            {
                ans = true;
                sec = i;
                break;
            }
        }

        if(ans)
        {
            System.out.println(sec);
        }

        else{
            System.out.println(-1);
        }
        br.close();
    }

    static int calR(int row,int col){
        int maxCol = 0;
        int[][] copy = new int[101][101];

        for(int i=1;i<=row;i++)
        {
            HashMap<Integer, Integer> map = new HashMap<>();
            for(int j=1;j<=col;j++) {
                if(board[i][j] == 0)
                    continue;

                if (map.containsKey(board[i][j])) {
                    map.put(board[i][j],map.get(board[i][j])+1);
                }
                else{
                    map.put(board[i][j],1);
                }
            }

            PriorityQueue<Node> pq = new PriorityQueue<>(
                    (x,y) -> {
                        if(x.c == y.c)
                            return x.v - y.v;

                        return x.c - y.c;
                    }
            );

            map.forEach(
                    (x,y)-> pq.add(new Node(x,y))
            );

            int nCol = pq.size() * 2;
            if(nCol >= 100)
                nCol = 100;

            maxCol = Math.max(maxCol,nCol);

            int idx = 1;

            while(!pq.isEmpty())
            {
                Node cur = pq.poll();
                copy[i][idx] = cur.v;
                idx = idx + 1;
                copy[i][idx] = cur.c;
                idx = idx + 1;

                if(idx >= 100)
                    break;
            }
        }

        board = copy;
        return maxCol;
    }

    static void print(int row,int col)
    {
        for(int i=1;i<=row;i++)
        {
            for(int j=1;j<=col;j++)
            {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int calC(int row,int col){
        int maxRow = 0;
        int copy[][] = new int[101][101];

        for(int j=1;j<=col;j++)
        {
            HashMap<Integer, Integer> map = new HashMap<>();
            for(int i=1;i<=row;i++)
            {
                if(board[i][j] == 0)
                    continue;

                if (map.containsKey(board[i][j])) {
                    map.put(board[i][j],map.get(board[i][j])+1);
                }
                else{
                    map.put(board[i][j],1);
                }
            }

            PriorityQueue<Node> pq = new PriorityQueue<>(
                    (x,y) -> {
                        if(x.c == y.c)
                            return x.v - y.v;

                        return x.c - y.c;
                    }
            );

            map.forEach(
                    (x,y)-> pq.add(new Node(x,y))
            );

            int nRow = pq.size() * 2;
            if(nRow >= 100)
                nRow = 100;

            maxRow = Math.max(maxRow,nRow);

            int idx = 1;

            while(!pq.isEmpty())
            {
                Node cur = pq.poll();
                copy[idx][j] = cur.v;
                idx = idx + 1;
                copy[idx][j] = cur.c;
                idx = idx + 1;

                if(idx >= 100)
                    break;
            }
        }

        board = copy;
        return maxRow;
    }
}
class Node
{
    int v;
    int c;

    Node(int v,int c)
    {
        this.v = v;
        this.c = c;
    }
}