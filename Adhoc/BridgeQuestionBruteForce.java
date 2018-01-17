import java.util.*;
import java.util.stream.Collectors;

public class BridgeQuestionBruteForce {
	public static void main(String[] args) {
		final int[] people = {1, 2, 5, 10};
		solve(people);
	}

	private static void solve(final int[] people) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < people.length - 1; i++) {
			final Set<Integer> peopleOnLeft = new HashSet<>(Arrays.stream(people).boxed().collect(Collectors.toList()));
			final int[] peopleOnRight = new int[people.length];
			peopleOnRight[i] = people[i];
			peopleOnRight[i + 1] = people[i + 1];
			peopleOnLeft.removeAll(Arrays.stream(peopleOnRight).boxed().filter(e -> e > 0).collect(Collectors.toList()));
			min = Math.min(min, solve(people, Math.max(people[i], people[i + 1]), peopleOnLeft, peopleOnRight, 2));

		}
		System.out.println("The minimum is " + min);
	}

	private static int solve(final int[] people, int cost, final Set<Integer> peopleOnLeft, final int[] peopleOnRight, int peopleOnRightCounter) {
		if (peopleOnRightCounter == people.length) {
			return cost;

		}
		int minCost = Integer.MAX_VALUE;
		for (int i = 0; i < peopleOnRight.length; i++) {
			if (peopleOnRight[i] > 0) {
				int personComingBack = peopleOnRight[i];
				peopleOnLeft.add(personComingBack);
				peopleOnRight[i] = -1;
				for (int person1 = 0; person1 < people.length; person1++) {
					for (int person2 = person1; person2 < people.length; person2++) {
						if (person1 < person2 && peopleOnLeft.contains(people[person1]) && peopleOnLeft.contains(people[person2])) {
							movePeopleToRightSide(people, peopleOnLeft, peopleOnRight, person1, person2);
							minCost = Math.min(minCost, solve(people, cost + Math.max(people[person1], people[person2]) + personComingBack,
										peopleOnLeft, peopleOnRight, peopleOnRightCounter + 1));
							movePeopleToLeftSide(people, peopleOnLeft, peopleOnRight, person1, person2);
						}
					}
				}
				peopleOnLeft.remove(personComingBack);
				peopleOnRight[i] = personComingBack;
			}
		}
		return minCost;
	}

	private static void movePeopleToLeftSide(final int[] people, final Set<Integer> peopleOnLeft, final int[] peopleOnRight, final int person1, final int person2) {
		peopleOnLeft.add(people[person1]);
		peopleOnLeft.add(people[person2]);
		peopleOnRight[person1] = -1;
		peopleOnRight[person2] = -1;
	}

	private static void movePeopleToRightSide(final int[] people, final Set<Integer> peopleOnLeft, final int[] peopleOnRight, final int person1, final int person2) {
		peopleOnLeft.remove(people[person1]);
		peopleOnLeft.remove(people[person2]);
		peopleOnRight[person1] = people[person1];
		peopleOnRight[person2] = people[person2];
	}
}

