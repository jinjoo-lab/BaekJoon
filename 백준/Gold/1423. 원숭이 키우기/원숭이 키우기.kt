import java.util.*
import java.io.*
import java.lang.*;
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList
import kotlin.math.max
import kotlin.text.StringBuilder


var n : Int = 0
var m : Int = 0
lateinit var number : Array<Int>
lateinit var countA : Array<Int>
var dp : Array<Array<Array<Int>>> = Array(51) {Array(101){Array(101){-1} } }

fun main(args: Array<String>) {
    var br = BufferedReader(InputStreamReader(System.`in`))

    var st = StringTokenizer(br.readLine(), " ")
    n = Integer.parseInt(st.nextToken())

    number = Array<Int>(n+1){0}
    countA = Array<Int>(n+1){0}

    st = StringTokenizer(br.readLine(), " ")
    for(i in 1..n) {
        number[i] = st.nextToken().toInt()
    }

    st = StringTokenizer(br.readLine(), " ")
    for(i in 1..n) {
        countA[i] = st.nextToken().toInt()
    }

    st = StringTokenizer(br.readLine(), " ")
    m = Integer.parseInt(st.nextToken())

    var tmpResult : Long = 0L

    for(i in 1..n) {
        tmpResult += (number[i].toLong() * countA[i])
    }

    tmpResult += solve(0,m,0)

    println(tmpResult)

    br.close()
}

fun solve(level : Int, day : Int, count : Int) : Int {

    if(level == n)
        return 0

    if(dp[level][day][count] != -1)
        return dp[level][day][count]

    dp[level][day][count] = 0

    for(i in 0..count + number[level]) {
        if(day < i)
            break

        dp[level][day][count] = max(dp[level][day][count], solve(level + 1, day - i, i) + (countA[level + 1] - countA[level] ) * i)
    }

    return dp[level][day][count]
}
