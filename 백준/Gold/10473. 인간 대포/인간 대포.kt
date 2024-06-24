import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    var st = StringTokenizer(br.readLine(), " ")
    val sx = st.nextToken().toDouble()
    val sy = st.nextToken().toDouble()

    st = StringTokenizer(br.readLine(), " ")
    val lx = st.nextToken().toDouble()
    val ly = st.nextToken().toDouble()

    val n = br.readLine().toInt()

    val edges = Array(n + 2) { MyNode(0.0, 0.0) }
    edges[0] = MyNode(sx, sy)

    for (i in 1..n) {
        st = StringTokenizer(br.readLine(), " ")
        val x = st.nextToken().toDouble()
        val y = st.nextToken().toDouble()
        edges[i] = MyNode(x, y)
    }

    edges[n + 1] = MyNode(lx, ly)

    println(go(edges, n, sx, sy))

    br.close()
}

fun go(edges: Array<MyNode>, n: Int, sx: Double, sy: Double): Double {
    val dis = DoubleArray(n + 2) { Double.MAX_VALUE }
    dis[0] = 0.0 // Start node's distance to itself is 0

    val pq = PriorityQueue<MyPoint>(compareBy { it.sec })

    for (i in 1..n + 1) {
        val tmpDis = getDis(sx, sy, edges[i].x, edges[i].y)
        dis[i] = min(dis[i], tmpDis / 5.0)
        if (i <= n) {
            pq.add(MyPoint(i, dis[i]))
        }
    }

    while (pq.isNotEmpty()) {
        val cur = pq.poll()

        for (i in 1..n + 1) {
            if (cur.idx == i) continue

            val tmpDis = getDis(edges[cur.idx].x, edges[cur.idx].y, edges[i].x, edges[i].y)
            var tmpSec = 0.0
            val wSec = tmpDis / 5.0

            if (tmpDis > 50.0) {
                tmpSec += 2.0
                tmpSec += (tmpDis - 50.0) / 5.0
            } else if (tmpDis == 50.0) {
                tmpSec += 2.0
            } else {
                tmpSec += 2.0
                tmpSec += (50.0 - tmpDis) / 5.0
            }

            if (dis[i] > cur.sec + min(wSec, tmpSec)) {
                dis[i] = cur.sec + min(wSec, tmpSec)
                if (i <= n) {
                    pq.add(MyPoint(i, dis[i]))
                }
            }
        }
    }

    return dis[n + 1]
}

fun getDis(x: Double, y: Double, x1: Double, y1: Double): Double {
    val tmpX = (x - x1).pow(2)
    val tmpY = (y - y1).pow(2)
    return sqrt(tmpX + tmpY)
}

data class MyPoint(val idx: Int, val sec: Double)
data class MyNode(val x: Double, val y: Double)
