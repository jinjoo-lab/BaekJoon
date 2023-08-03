import java.util.*;
import java.io.*;

public class Main {
    static char[] arr;
    static char[] boom;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = br.readLine().toCharArray();
        boom = br.readLine().toCharArray();

        Stack<Character> stack = new Stack();

        for(int i=0;i<arr.length;i++)
        {
            stack.push(arr[i]);

            if(!stack.isEmpty() && stack.size() >= boom.length)
            {
                int idx = stack.size()-1;
                boolean pop = true;
                for(int j=boom.length-1;j>=0;j--)
                {
                    if(stack.get(idx) != boom[j])
                    {
                        pop = false;
                        break;
                    }

                    idx = idx -1;
                }

                if(pop)
                {
                    for(int j=boom.length-1;j>=0;j--)
                    {
                        stack.pop();
                    }
                }
            }

        }

        if(stack.isEmpty())
            System.out.println("FRULA");

        else{
            StringBuilder sb = new StringBuilder();

            for(int i=0;i<stack.size();i++)
            {
                sb.append(stack.get(i));
            }

            System.out.println(sb.toString());
        }
        br.close();
    }
}
