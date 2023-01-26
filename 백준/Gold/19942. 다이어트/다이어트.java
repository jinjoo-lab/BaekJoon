import java.io.*;
import java.util.*;

public class Main {
    static int limit = 8000;
    static int num = 0;
    static int mp,mf,ms,mv=0;
    static int result = 0;
    static boolean rearr[] = new boolean[16];
    static int[][] arr = new int[16][5];
    static boolean re[] = new boolean[16];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        num = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        mp = Integer.parseInt(st.nextToken());
        mf = Integer.parseInt(st.nextToken());
        ms = Integer.parseInt(st.nextToken());
        mv = Integer.parseInt(st.nextToken());

        for(int i=1;i<=num;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            arr[i][0] =  Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] =  Integer.parseInt(st.nextToken());
            arr[i][3] =  Integer.parseInt(st.nextToken());
            arr[i][4] = Integer.parseInt(st.nextToken());
        }
        result = limit;

        travel(0,0,0,0,0,0);
        if(result==limit) {
            System.out.println(-1);
        }
        else {
            System.out.println(result);
            for(int i=1;i<=num;i++)
            {
                if(re[i])
                    System.out.print(i+" ");
            }
            System.out.println();
        }
        br.close();
    }
    static boolean compare()
    {
        int count1= 0;
        int count2= 0;
        int[] arr1 = new int[20];
        int[] arr2 = new int[20];

        for(int i=1;i<=num;i++)
        {
            if(re[i])
                arr1[++count1] = i;
            if(rearr[i])
                arr2[++count2] = i;
        }

        for(int i=1;i<=num;i++)
        {
            if(arr1[i]<arr2[i])
                return false;
            if(arr1[i]>arr2[i])
                return true;
        }

        return false;
    }
    static void travel(int k,int cost,int p,int f,int s,int v)
    {
        if(k==num)
        {
            if(p>=mp&&f>=mf&&s>=ms&&v>=mv) {
                if (cost < result) {
                    result = cost;
                    for(int i=1;i<=num;i++)
                        re[i] = rearr[i];
                }
                else if(cost == result)
                {
                    if(compare())
                    {
                        for(int i=1;i<=num;i++)
                            re[i] = rearr[i];
                    }
                }
            }
            return;
        }

        rearr[k+1] = true;
        travel(k+1,cost+arr[k+1][4],p+arr[k+1][0],f+arr[k+1][1],s+arr[k+1][2],v+arr[k+1][3]);
        rearr[k+1] = false;
        travel(k+1,cost,p,f,s,v);
    }
}