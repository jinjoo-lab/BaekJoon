import java.io.*;
import java.util.*;

public class Main {
    static int n= 0;
    static int k=0;
    static int result = 0;
    static String[] words = new String[51];
    static boolean[] isIn = new boolean[27];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        for(int i=1;i<=n;i++)
        {
            words[i] =  br.readLine();
        }
        if(k<5)
        {
            System.out.println(0);
        }
        else{
            isIn[0] = true;isIn[2]=true;isIn[8]= true;
            isIn[13] = true; isIn[19] = true;

            travel(5,0);
            System.out.println(result);
        }
        br.close();
    }
    static boolean possible(String word)
    {
        char[] arr = word.toCharArray();
        for(int i=0;i<arr.length;i++)
        {
            if(!isIn[arr[i]-'a'])
            {
                return false;
            }
        }
        return true;
    }
    static void travel(int s,int cur)
    {
        if(cur>=27)
        {
            return;
        }
        if(s==k)
        {
            int num = 0;
            for(int i=1;i<=n;i++)
            {
                if(possible(words[i]))
                {
                    num++;
                }
            }
            if(num>result)
            {
                result = num;
            }
            return;
        }
        if(!isIn[cur])
        {
            isIn[cur] = true;
            travel(s+1,cur+1);
            isIn[cur] = false;
            travel(s,cur+1);
        }
        if(isAnti(cur))
            travel(s,cur+1);
    }
    static boolean isAnti(int i)
    {
        if(i==0||i==2||i==8||i==13||i==19)
            return true;
        return false;
    }
}
/*
*   호출 조건 : 가르친 알파벳의 수 ?
*   탈출 조건 : 해당 수가 k에 도달했을 때
* */
