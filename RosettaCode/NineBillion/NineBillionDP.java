public class NineBillionDP {
	public static void solveNineBillion(int n) {
		int[][] dp = new int[n + 1][n + 1];
		int[][] solutionMatrix = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			dp[i][1] = solutionMatrix[i][1] = solutionMatrix[i][i] = 1;
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 2; j <= i; j++) {
				if (i - j == 0) {
					dp[i][j] = dp[i][j - 1] + 1;
				} else {
					dp[i][j] = i - j <= j ? dp[i][j - 1] + dp[i - j][i -j] : dp[i][j - 1] + dp[i - j][j];
					solutionMatrix[i][j] = i - j <= j ? dp[i - j][i - j] : dp[i - j][j];
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			System.out.println(Arrays.stream(solutionMatrix[i]).filter(num -> num > 0).mapToObj(num -> num + "")
					.collect(Collectors.joining(", ", "[", "]")));
		}
	}

	public static void main(String[] args) {
		solveNineBillion(1200);
	}
}
