import java.util.*;
import java.io.*;

public class Main {
    static int n = 0 ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());

        point[] board = new point[n];

        for(int i=0;i<n;i++)
        {
            st = new StringTokenizer(br.readLine()," ");
            board[i] = new point(i,Integer.parseInt(st.nextToken()),st.nextToken());
        }

        Arrays.sort(board,(x,y) -> {
            if(x.age == y.age)
                return x.idx - y.idx;

            return x.age - y.age;
        });

        StringBuilder sb = new StringBuilder();

        for(int i=0;i<n;i++)
        {
            sb.append(board[i].age+" "+board[i].name+"\n");
        }

        System.out.println(sb);

        br.close();
    }
}
class point
{
    int idx;
    int age;
    String name;

    point(int idx,int age,String name)
    {
        this.idx = idx;
        this.age = age;
        this.name = name;
    }
}