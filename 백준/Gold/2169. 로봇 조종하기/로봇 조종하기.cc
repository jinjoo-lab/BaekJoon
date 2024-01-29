#include <iostream>
#include <algorithm>
using namespace std;
int dp[1002][1002][3];
int n, m;
int arr[1002][1002];
bool check[1002][1002];
int dx[] = { 1,0,0 };
int dy[] = { 0,-1,1 };

void init() {
	for (int i = 0; i < 1002; i++) {
		for (int j = 0; j < 1002; j++) {
			for(int k=0;k<3;k++) dp[i][j][k] = -1 * 2000000000;
		}
	}
}

bool range(int x, int y) {
	return x >= 1 && x <= n && y >= 1 && y <= m;
}

int recur(int x, int y, int dir) {

	if (dp[x][y][dir] != -1 * 2000000000) return dp[x][y][dir];

	if (x == n && y == m) return arr[n][m];

	check[x][y] = true;

	for (int i = 0; i < 3; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (!range(nx, ny)) continue;

		if (check[nx][ny]) continue;

		dp[x][y][dir] = max(dp[x][y][dir], arr[x][y] + recur(nx, ny, i));
	}
	check[x][y] = false;

	return dp[x][y][dir];
}

int main() {
	cin >> n >> m;

	init();

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= m; j++) cin >> arr[i][j];
	}

	cout << recur(1, 1, 0);

	return 0;
}