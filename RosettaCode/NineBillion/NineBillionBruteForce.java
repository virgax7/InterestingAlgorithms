public class NineBillionBruteForce {
	public static void solveNineBillion(int n){
		List<List<Integer>> result = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			Map<Integer, Integer> row = new TreeMap<>();
			solveRow(i, i, new ArrayList<>(), row);
			result.add(row.entrySet().stream().map(set -> set.getValue()).collect(Collectors.toList()));
		}
		result.stream().forEach(System.out::println);
	}

	private static void solveRow(int i, int target, ArrayList<Integer> integers, Map<Integer, Integer> row) {
		if (target == 0) {
			row.put(integers.get(0), row.getOrDefault(integers.get(0), 0) + 1);
		}
		if (target > 0) {
			for (int j = i; j > 0; j--) {
				integers.add(j);
				solveRow(j, target - j, integers, row);
				integers.remove(integers.size() - 1);
			}
		}

	}
}
