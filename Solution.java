import java.util.*;

public class Solution {

	public static String getResults(int[] elements) {

		int[] elementsSorted = Arrays.copyOf(elements, elements.length);
		Arrays.sort(elementsSorted);

		if (Arrays.equals(elements, elementsSorted)) {
			return "yes";
		}

		boolean checkForSawp = false;
		boolean checkForReverse = false;
		int firstBreakIndex = 0;
		int secondBreakIndex = 0;

		for (int i = 1; i < elements.length; i++) {
			if (elements[i] < elements[i - 1]) {

				firstBreakIndex = i - 1;
				if (i == elements.length - 1) {
					return "yes\nswap " + (firstBreakIndex + 1) + " " + (i + 1);
				}
				if (elements[i + 1] > elements[i]) {
					checkForSawp = true;
					break;
				}
				if (elements[i + 1] < elements[i]) {
					checkForReverse = true;
					break;
				}
			}
		}

		if (checkForSawp) {
			boolean consequtiveElements = true;
			for (int i = firstBreakIndex + 2; i < elements.length; i++) {
				if (elements[i] < elements[i - 1]) {
					secondBreakIndex = i;
					consequtiveElements = false;
					break;
				}
			}
			if (consequtiveElements) {
				secondBreakIndex = firstBreakIndex + 1;
			}
			
			int firstElement = elements[firstBreakIndex];
			elements[firstBreakIndex] = elements[secondBreakIndex];
			elements[secondBreakIndex] = firstElement;
			if (Arrays.equals(elementsSorted, elements)) {
				return "yes\nswap " + (firstBreakIndex + 1) + " " + (secondBreakIndex + 1);
			}
		}

		if (checkForReverse) {

			boolean secondBreakIndexIsLatsElement = true;
			for (int i = firstBreakIndex + 2; i < elements.length; i++) {
				if (elements[i] > elements[i - 1]) {
					secondBreakIndex = i - 1;
					secondBreakIndexIsLatsElement = false;
					break;
				}
			}

			if (secondBreakIndexIsLatsElement) {
				secondBreakIndex = elements.length - 1;
			}
			
			int[] firstHalf = Arrays.copyOfRange(elements, 0, firstBreakIndex);
			int[] secondHalf = Arrays.copyOfRange(elements, firstBreakIndex, secondBreakIndex + 1);
			int[] thirdHalf = Arrays.copyOfRange(elements, secondBreakIndex + 1, elements.length);
			Arrays.sort(secondHalf);

			if (Arrays.equals(Arrays.copyOfRange(elementsSorted, 0, firstBreakIndex), firstHalf)
					&& Arrays.equals(Arrays.copyOfRange(elementsSorted, firstBreakIndex, secondBreakIndex + 1),
							secondHalf)
					&& Arrays.equals(Arrays.copyOfRange(elementsSorted, secondBreakIndex + 1, elements.length),
							thirdHalf)) {
				return "yes\nreverse " + (firstBreakIndex + 1) + " " + (secondBreakIndex + 1);
			}
		}
		return "no";
	}

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int numberOfElements = reader.nextInt();
		int[] elements = new int[numberOfElements];

		for (int i = 0; i < numberOfElements; i++) {
			int inputElement = reader.nextInt();
			elements[i] = inputElement;
		}
		String result = getResults(elements);
		System.out.println(result);
	}
}
