
import java.io.*;
import java.util.*;

public class Main {
    static HashSet<String> result = new HashSet<>();
    static boolean[] visit = new boolean[9];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] arr = new int[9];

        tic(0,arr,1);

        while(true)
        {
            String input = br.readLine();
            if(input.equals("end"))
                break;

            if(result.contains(input))
                bw.write("valid\n");
            else{
                bw.write("invalid\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static boolean garo(int[] arr)
    {
        if(arr[0]!=0&&arr[1]!=0&&arr[2]!=0)
        {
            if(arr[0]==arr[1]&&arr[1]==arr[2])
            {
                return true;
            }
        }

        if(arr[3]!=0&&arr[4]!=0&&arr[5]!=0)
        {
            if(arr[3]==arr[4]&&arr[4]==arr[5])
                return true;
        }

        if(arr[6]!=0&&arr[7]!=0&&arr[8]!=0)
        {
            if(arr[6]==arr[7]&&arr[7]==arr[8])
                return true;
        }

        return false;
    }

    static boolean sero(int[] arr)
    {
        if(arr[0]!=0&&arr[3]!=0&&arr[6]!=0)
        {
            if(arr[0]==arr[3]&&arr[3]==arr[6])
                return true;
        }
        if(arr[1]!=0&&arr[4]!=0&&arr[7]!=0)
        {
            if(arr[1]==arr[4]&&arr[4]==arr[7])
                return true;
        }
        if(arr[2]!=0&&arr[5]!=0&&arr[8]!=0)
        {
            if(arr[2]==arr[5]&&arr[5]==arr[8])
                return true;
        }

        return false;
    }

    static boolean cross(int[] arr)
    {
        if(arr[0]!=0&&arr[4]!=0&&arr[8]!=0)
        {
            if(arr[0]==arr[4]&&arr[4]==arr[8])
                return true;
        }
        if(arr[2]!=0&&arr[4]!=0&&arr[6]!=0)
        {
            if(arr[2]==arr[4]&&arr[4]==arr[6])
                return true;
        }

        return false;
    }

    static void tic(int k,int[] arr,int who)
    {
        if(garo(arr)||sero(arr)||cross(arr))
        {
            result.add(convert(arr));
            return;
        }

        if(k==9)
        {
            result.add(convert(arr));
            return;
        }

        for(int i=0;i<=8;i++)
        {
            if(who==1)
            {
                if(!visit[i])
                {
                    visit[i] = true;
                    arr[i] = 1;
                    tic(k+1,arr,2);
                    arr[i] = 0;
                    visit[i] = false;
                }
            }

            else{
                if(!visit[i])
                {
                    visit[i] = true;
                    arr[i] = 2;
                    tic(k+1,arr,1);
                    arr[i] = 0;
                    visit[i] = false;
                }
            }
        }
    }

    static String convert(int[] arr)
    {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<=8;i++)
        {
            if(arr[i]==0)
            {
                sb.append('.');
            }

            else if(arr[i]==1)
            {
                sb.append('X');
            }

            else{
                sb.append('O');
            }
        }

        return sb.toString();
    }
}