import java.io.*
import java.lang.StringBuilder
import java.util.*
import kotlin.reflect.jvm.internal.impl.protobuf.ByteString

fun main(args: Array<String>) {
    var br=BufferedReader(InputStreamReader(System.`in`))
    var bw=BufferedWriter(OutputStreamWriter(System.out))
    var stack=Stack<Int>()

    var num=br.readLine().toInt()
    var arr=Array<Int>(num,{-1})

    var st=StringTokenizer(br.readLine()," ")

    for(i in 0..num-1)
    {
        arr[i]=(st.nextToken().toInt())
    }

    for(i in 0..num-1)
    {

        while(!stack.isEmpty()&&arr[stack.peek()]<arr[i])
        {
            arr[stack.peek()]=arr[i]
            stack.pop()
            continue
        }
        stack.push(i)
    }
    while(!stack.isEmpty())
        arr[stack.pop()]=-1
    

    var str=StringBuilder("")
    for(element in arr)
        str.append("$element ")
    println(str)
    br.close()
    bw.close()
}