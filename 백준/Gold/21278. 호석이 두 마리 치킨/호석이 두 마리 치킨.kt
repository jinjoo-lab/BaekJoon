import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.max
import kotlin.math.min

var n = 0
var m = 0
val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var st : StringTokenizer
lateinit var graph : Array<Array<Int>>
fun main() {
   st = StringTokenizer(br.readLine()," ")

    n = st.nextToken().toInt()
    m = st.nextToken().toInt()

    graph = Array(n+1){Array(n+1){10001}}

    for(i in 1..m) {
        st = StringTokenizer(br.readLine()," ")
        var v1 = st.nextToken().toInt()
        var v2 = st.nextToken().toInt()

        graph[v1][v2] = 1
        graph[v2][v1] = 1
    }

    for(k in 1..n) {
        for(i in 1..n) {
            for(j in 1..n) {
                if(i == j)
                    continue

                graph[i][j] = min(graph[i][j],graph[i][k] + graph[k][j])

                graph[j][i] = graph[i][j]
            }
        }
    }

    var result = Int.MAX_VALUE
    var v1 = 0
    var v2 = 0

    for(i in 1..n) {
        for(j in 1..n) {
            if(i == j)
                continue

            var tmp = getResult(i,j)

            if(result > tmp) {
                result = tmp
                v1 = i
                v2 = j
            }
        }
    }

    print(v1)
    print(" ")
    print(v2)
    print(" ")
    print(result * 2)
}

fun getResult(v1 : Int , v2 : Int) : Int{
    var result = 0

    for(i in 1..n) {
        if(i == v1 || i == v2)
            continue

        result += min(graph[i][v1] , graph[i][v2])
    }

    return result
}



