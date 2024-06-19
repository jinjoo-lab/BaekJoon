import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

data class Point(val idx: Int, val h: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine(), " ")
    val n = st.nextToken().toInt()
    val h = IntArray(n + 1)
    val num = IntArray(n + 1)
    val s = Array(n + 1) { IntArray(2) { Int.MAX_VALUE } }

    st = StringTokenizer(br.readLine(), " ")
    for (i in 1..n) {
        h[i] = st.nextToken().toInt()
    }

    val stack = Stack<Point>()

    for (i in 1..n) {
        while (stack.isNotEmpty()) {
            val cur = stack.peek()
            if (cur.h <= h[i]) {
                stack.pop()
            } else {
                if (Math.abs(cur.idx - i) < s[i][1]) {
                    s[i][0] = cur.idx
                    s[i][1] = Math.abs(cur.idx - i)
                }
                num[i] += stack.size
                break
            }
        }
        stack.add(Point(i, h[i]))
    }

    stack.clear()

    for (i in n downTo 1) {
        while (stack.isNotEmpty()) {
            val cur = stack.peek()
            if (cur.h <= h[i]) {
                stack.pop()
            } else {
                if (Math.abs(cur.idx - i) < s[i][1]) {
                    s[i][0] = cur.idx
                    s[i][1] = Math.abs(cur.idx - i)
                } else if (Math.abs(cur.idx - i) == s[i][1]) {
                    s[i][0] = Math.min(cur.idx, s[i][0])
                }
                num[i] += stack.size
                break
            }
        }
        stack.add(Point(i, h[i]))
    }

    val sb = StringBuilder()
    for (i in 1..n) {
        if (num[i] == 0) {
            sb.append("0\n")
        } else {
            sb.append("${num[i]} ${s[i][0]}\n")
        }
    }
    print(sb)
    br.close()
}
