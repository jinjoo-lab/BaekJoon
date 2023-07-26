import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int l = 0;
    static int r = 0;



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        ArrayList<Integer> list = new ArrayList<>();
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        for(int i=1;i<= l -1;i++)
        {
            list.add(i);
        }

        list.add(Math.max(l,r));

        for(int i = r -1 ; i >= 1; i --)
        {
            list.add(i);
        }

        if(list.size() > n)
            System.out.println(-1);

        else if(list.size() == n)
            print(list);

        else{
            int tmp = n - list.size();

            print2(list,tmp);
        }


        br.close();
    }

    static void print(ArrayList<Integer> list)
    {
        StringBuilder sb = new StringBuilder();

        for(int num : list)
        {
            sb.append(num+" ");
        }

        System.out.println(sb);
    }

    static void print2(ArrayList<Integer> list,int tmp)
    {


        StringBuilder sb = new StringBuilder();

        sb.append(list.get(0)+" ");

        for(int i=1;i<=tmp;i++)
            sb.append(1+" ");

        for(int i=1;i<list.size();i++)
            sb.append(list.get(i)+" ");

        System.out.println(sb);
    }
}