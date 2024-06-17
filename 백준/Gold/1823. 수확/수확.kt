import java.util.*
import java.io.*
import java.lang.*;
import kotlin.collections.HashSet
import kotlin.math.max


var n : Int = 0


lateinit var dp : Array<Array<Int>>
lateinit var input : Array<Int>

fun main(args: Array<String>) {
    var br = BufferedReader(InputStreamReader(System.`in`))
    n =  br.readLine().toInt()

    input = Array<Int>(n+1){0}
    for(i in 1..n) {
        input[i] = br.readLine().toInt()
    }

    dp = Array(n+1) { Array<Int>(n + 1){-1} }

    var result : Int = go(1,1,n)

    println(result)

    br.close()
}

fun go(idx : Int, l : Int , r : Int) : Int{
    if(l == r) {
        dp[l][r] = (idx * input[l])
        return dp[l][r]
    }

    if(dp[l][r] != -1)
        return dp[l][r]

    dp[l][r] = 0

    var nl : Int = l + 1
    var nr : Int = r - 1

    if(nl <= n && nl <= r) {
        dp[l][r] = (idx * input[l]) + go(idx + 1, nl, r)
    }

    if(nr >= 1 && nr >= l) {
        dp[l][r] = max(dp[l][r], (idx * input[r]) + go(idx + 1 , l, nr))
    }

    return dp[l][r]
}
