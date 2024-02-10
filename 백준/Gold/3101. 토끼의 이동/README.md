# [Gold III] 토끼의 이동 - 3101 

[문제 링크](https://www.acmicpc.net/problem/3101) 

### 성능 요약

메모리: 21032 KB, 시간: 216 ms

### 분류

구현, 수학

### 제출 일자

2024년 2월 10일 17:10:56

### 문제 설명

<p>1부터 N<sup>2</sup>까지 수가 지그재그 대각선 순서로 N*N 행렬에 채워져 있다. 아래 그림은 N=6일 때, 행렬의 모습이다.</p>

<table class="table table-bordered" style="width:18%;">
	<tbody>
		<tr>
			<td style="width:3%; text-align:center;">1</td>
			<td style="width:3%; text-align:center;">2</td>
			<td style="width:3%; text-align:center;">6</td>
			<td style="width:3%; text-align:center;">7</td>
			<td style="width:3%; text-align:center;">15</td>
			<td style="width:3%; text-align:center;">16</td>
		</tr>
		<tr>
			<td style="text-align:center;">3</td>
			<td style="text-align:center;">5</td>
			<td style="text-align:center;">8</td>
			<td style="text-align:center;">14</td>
			<td style="text-align:center;">17</td>
			<td style="text-align:center;">26</td>
		</tr>
		<tr>
			<td style="text-align:center;">4</td>
			<td style="text-align:center;">9</td>
			<td style="text-align:center;">13</td>
			<td style="text-align:center;">18</td>
			<td style="text-align:center;">25</td>
			<td style="text-align:center;">27</td>
		</tr>
		<tr>
			<td style="text-align:center;">10</td>
			<td style="text-align:center;">12</td>
			<td style="text-align:center;">19</td>
			<td style="text-align:center;">24</td>
			<td style="text-align:center;">28</td>
			<td style="text-align:center;">33</td>
		</tr>
		<tr>
			<td style="text-align:center;">11</td>
			<td style="text-align:center;">20</td>
			<td style="text-align:center;">23</td>
			<td style="text-align:center;">29</td>
			<td style="text-align:center;">32</td>
			<td style="text-align:center;">34</td>
		</tr>
		<tr>
			<td style="text-align:center;">21</td>
			<td style="text-align:center;">22</td>
			<td style="text-align:center;">30</td>
			<td style="text-align:center;">31</td>
			<td style="text-align:center;">35</td>
			<td style="text-align:center;">36</td>
		</tr>
	</tbody>
</table>

<p>토끼는 지금 1이 있는 칸에 있다. 토끼는 인접한 칸으로 점프할 수 있다. (위, 아래, 오른쪽, 왼쪽)</p>

<p>토끼가 점프한 방법이 주어졌을 때, 토끼가 방문한 칸에 있는 수의 합을 구하는 프로그램을 작성하시오. 같은 칸을 여러 번 방문할 경우에도, 방문할 때 마다 더해야 한다. 토끼가 행렬을 벗어나는 경우는 없다.</p>

### 입력 

 <p>첫째 줄에 N, K가 주어진다. (1 ≤ N ≤ 100,000, 1 ≤ K ≤ 300,000) N은 행렬의 크기, K는 토끼가 점프한 횟수이다.</p>

<p>둘째 줄에는 'U','D','L','R'로 이루어진 문자열이 주어진다. 이 문자열의 길이는 K이며, 토끼가 점프한 방향이다.</p>

### 출력 

 <p>첫째 줄에, 방문한 칸의 수의 합을 출력한다. 이 값은 32비트 정수를 넘을 수도 있다.</p>

