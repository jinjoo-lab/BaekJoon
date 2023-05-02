import java.util.*;
class Solution {
    boolean solution(String s) {
        boolean answer = true;
        Stack<Character> stack = new Stack<>();
        
        char[] arr = s.toCharArray();
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]=='(')
            {
                stack.push(arr[i]);
            }
            
            else{
                if(stack.isEmpty())
                {
                    answer = false;
                    break;
                }
                
                else{
                    if(stack.peek().equals('('))
                    {
                        if(arr[i]==')')
                        {
                            stack.pop();
                            continue;
                        }
                        
                        else{
                            answer = false;
                            break;
                        }
                    }
                }
            }
        }
        
        if(answer==true && !stack.isEmpty())
            answer = false;
        return answer;
    }
}