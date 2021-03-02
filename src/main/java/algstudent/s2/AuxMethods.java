package algstudent.s2;

public class AuxMethods {
	public static int minPos(int[] elements, int i) {
		int minIndex = i;
		for (int j = i; j < elements.length-1; j++) {
			
				if (elements[j] > elements[j + 1]) {
					minIndex = j + 1;
				}
			
		}
		return minIndex;
	}

	public static int[] swapElements(int[] elements, int i, int j) {
		int aux = elements[j];
		elements[j] = elements[i];
		elements[i] = aux;
		return elements;
	}

}
