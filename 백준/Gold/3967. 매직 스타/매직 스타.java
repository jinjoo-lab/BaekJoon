import java.io.*;
import java.util.*;

public class Main {
    static int[] board = new int[13];

    static int[] result = new int[13];
    static boolean[] init = new boolean[13];
    static boolean[] number = new boolean[13];
    static boolean[] visit = new boolean[13];

    static boolean get = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0;i<5;i++)
        {
            String[] line = br.readLine().split("");
            if(i==0)
            {
                char cur = line[4].charAt(0);
                if(cur!='x')
                {
                    board[1] = cur - 'A' + 1;
                }
            }
            if(i==1)
            {
                char cur = line[1].charAt(0);
                if(cur!='x')
                {
                    board[2] = cur - 'A' + 1;
                }
                cur = line[3].charAt(0);
                if(cur!='x')
                {
                    board[3] = cur - 'A' + 1;
                }
                cur = line[5].charAt(0);
                if(cur!='x')
                {
                    board[4] = cur - 'A' + 1;
                }
                cur = line[7].charAt(0);
                if(cur!='x')
                {
                    board[5] = cur - 'A' + 1;
                }
            }

            if(i==2)
            {
                char cur = line[2].charAt(0);
                if(cur!='x')
                {
                    board[6] = cur - 'A' + 1;
                }
                cur = line[6].charAt(0);
                if(cur!='x')
                {
                    board[7] = cur - 'A' + 1;
                }
            }

            if(i==3)
            {
                char cur = line[1].charAt(0);
                if(cur!='x')
                {
                    board[8] = cur - 'A' + 1;
                }
                cur = line[3].charAt(0);
                if(cur!='x')
                {
                    board[9] = cur - 'A' + 1;
                }
                cur = line[5].charAt(0);
                if(cur!='x')
                {
                    board[10] = cur - 'A' + 1;
                }
                cur = line[7].charAt(0);
                if(cur!='x')
                {
                    board[11] = cur - 'A' + 1;
                }
            }

            if(i==4)
            {
                char cur = line[4].charAt(0);
                if(cur!='x')
                {
                    board[12] = cur - 'A' + 1;
                }
            }
        }
        for(int i=1;i<=12;i++)
        {
            if(board[i]>0) {
                init[i] = true;
                number[board[i]] = true;
            }
        }

        makeStar(1);
        print();

        br.close();
    }
    static void print()
    {
        char a = (char)(result[1] + 'A' -1);
        char b = (char)(result[2] + 'A' -1);
        char c = (char)(result[3] + 'A' -1);
        char d = (char)(result[4] + 'A' -1);
        char e = (char)(result[5] + 'A' -1);
        char f = (char)(result[6] + 'A' -1);
        char g = (char)(result[7] + 'A' -1);
        char h = (char)(result[8] + 'A' -1);
        char i = (char)(result[9] + 'A' -1);
        char j = (char)(result[10] + 'A' -1);
        char k = (char)(result[11] + 'A' -1);
        char l = (char)(result[12] + 'A' -1);

        System.out.println("...."+a+"....");
        System.out.println("."+b+"."+c+"."+d+"."+e+".");
        System.out.println(".."+f+"..."+g+"..");
        System.out.println("."+h+"."+i+"."+j+"."+k+".");
        System.out.println("...."+l+"....");
    }

    static void makeStar(int start)
    {
        if(get)
            return;

        if(start==6)
        {
            int num = board[2] + board[3]+ board[4] + board[5];
            if(num!=26)
                return;
        }

        if(start==9)
        {
            int num = board[1] + board[3] + board[6] + board[8];
            if(num!=26)
                return;
        }
        if(start==12)
        {
            int num1 = board[1] + board[4]+board[7] + board[11];
            int num2 = board[8] + board[9] + board[10] + board[11];

            if(num1!=26||num2!=26)
            {
                return;
            }
        }

        if(start > 12)
        {
            int num1 = board[2] + board[6] + board[9] + board[12];
            int num2 = board[5] + board[7] + board[10] + board[12];

            if(num1!=26||num2!=26)
                return;

            if(get==false)
            {
                get = true;
            }
            copy();
            return;
        }

        if(!init[start]) {
            for (int i = 1; i <= 12; i++) {
                if (!number[i]&&!visit[i]) {
                    visit[i] = true;
                    board[start] = i;
                    makeStar(start+1);
                    visit[i] = false;
                }
            }
        }
        else{
            makeStar(start+1);
        }
    }

    static void copy()
    {
        for(int i=1;i<=12;i++)
        {
            result[i] = board[i];
        }
    }
}