import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[] number = new int[51];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine()," ");
        for(int i=0;i<n;i++)
        {
            int num = Integer.parseInt(st.nextToken());

            number[i] = num;
        }

        Arrays.sort(number,0,n);

        boolean more = true;
        for(int i=0;i<n-1;i++)
        {
            boolean kiss = false;
            if(number[i]+1==number[i+1])
            {
                if(more) {
                    for (int j = i + 2; j < n; j++) {
                        if (number[i + 1] != number[j]) {
                            kiss = true;
                            swap(i + 1, j);
                            break;
                        }
                    }
                }
                if(!kiss)
                {
                    int tmp = 0;
                    for(int k=i-1;k>=0;k--)
                    {
                        if(number[k]!=number[i])
                        {
                            tmp = k+1;
                            break;
                        }
                    }
                    swap(tmp,i+1);
                }
            }
        }

        print();

        bw.close();
        br.close();
    }

    static void swap(int i,int j)
    {
        int tmp = number[i];
        number[i] = number[j];
        number[j] = tmp;
    }

    static void print()
    {
        for(int i=0;i<n;i++)
        {
            System.out.print(number[i]+" ");
        }
        System.out.println();
    }
}
