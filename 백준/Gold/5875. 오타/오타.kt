import java.util.*
import java.io.*
import java.lang.*;
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList
import kotlin.math.max
import kotlin.text.StringBuilder


var n : Int = 0
var left : Int = 0
var right : Int = 0
var total : Int = 0

fun main(args: Array<String>) {
    var br = BufferedReader(InputStreamReader(System.`in`))

    var st = StringTokenizer(br.readLine(), " ")

    var arr = st.nextToken().toCharArray()
    n = arr.size

    var result : Int = 0

    for(i in 1..n) {

        if(arr[i - 1] == '(') {
            total += 1
            left += 1
        }else{
            total -= 1
            right += 1
        }

        if(total <= 1) {
            left = 0
        }


        if(total <= -1) {
            result = right
            break
        }
    }

    if(total >= 1) {
        result = left
    }

    println(result)
    br.close()
}
