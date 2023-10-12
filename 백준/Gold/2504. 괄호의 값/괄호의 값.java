import java.io.*;
import java.util.*;

public class Main {
    static char[] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = br.readLine().toCharArray();

        Stack<Character> stack = new Stack<>();
        int result = 0;
        int tmp = 1;

        for(int i=0;i<board.length;i++){
            if(board[i] == '('){
                tmp = tmp * 2;
                stack.push(board[i]);
            }

            else if(board[i] == '['){
                tmp = tmp * 3;
                stack.push(board[i]);
            }

            else if(board[i] ==')'){
                if(!stack.isEmpty() && stack.peek() =='('){
                    if(board[i-1] == '('){
                        result += tmp;
                    }
                    stack.pop();
                    tmp = tmp / 2;
                }

                else{
                    result = 0;
                    break;
                }
            }

            else{
                if(!stack.isEmpty() && stack.peek() =='['){
                    if(board[i-1] == '['){
                        result += tmp;
                    }
                    stack.pop();
                    tmp  = tmp / 3;
                }

                else{
                    result = 0;
                    break;
                }
            }
        }

        if(stack.isEmpty())
            System.out.println(result);

        else{
            System.out.println(0);
        }
        br.close();
    }
}
