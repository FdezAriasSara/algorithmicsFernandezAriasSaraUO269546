package algstudent.s0;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatrixOperations {

	private static final int MOVE_LEFT = 4;
	private static final int MOVE_DOWN = 3;
	private static final int MOVE_RIGHT = 2;
	private static final int MOVE_UP = 1;
	private static final int VISITED = -1;
	private int n;
	private int[][] matrix;

	public MatrixOperations(int n, int min, int max) {
		this.n = n;
		createMatrix(n);
		initializeMatrix(min, max);

	}

	public MatrixOperations(String filename) {
		String line;

		String[] row;
		ArrayList<Integer> values = new ArrayList<Integer>();
		try {
			BufferedReader file = new BufferedReader(new FileReader(filename));
			if (file.ready()) {
				line = file.readLine();
				this.n = Integer.valueOf(line);

			}
			while (file.ready()) {
				line = file.readLine();
				row = line.split("\t");

				collectValues(values, row);

			}
			initializeMatrix(values, n);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initializeMatrix(ArrayList<Integer> values, int n) {
		this.n = n;
		createMatrix(n);
		int num = 0;
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				matrix[row][col] = values.get(num);
				num++;
			}
		}

	}

	private void collectValues(List<Integer> values, String[] row) {
		for (String num : row) {

			values.add(Integer.parseInt(num));
		}

	}

	public int[][] getMatrix() {
		return matrix;
	}

	private void initializeMatrix(int min, int max) {
		Random value = new Random();
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				matrix[row][col] = value.nextInt((max - min) + 1) + min;
			}
		}

	}

	private void createMatrix(int n) {
		this.matrix = new int[n][n];
	}

	@Override
	public String toString() {
		String matrix = "";
		for (int i = 0; i < this.matrix.length; i++) {

			for (int j = 0; j < this.matrix.length; j++) {
				matrix += this.matrix[i][j] + "\t";
			}
			matrix += "\n\n";
		}
		return matrix;
	}

	public void write() {
		System.out.print(toString());
	}

	public int getSize() {
		return n;
	}

	/**
	 * Computes the summation of all the elements of the matrix diagonal. This
	 * implementation must iterate over all the matrix elements, but only sums
	 * appropriate elements. So, the complexity is quadratic.
	 * 
	 * @return the integer sum of the diagonal.
	 */
	public int sumDiagonal1() {
		int sum = 0;
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix.length; col++) {
				if (row == col) {
					sum += matrix[row][col];
				}
			}
		}
		return sum;
	}

	/**
	 * Computes the summation of all the elements of the matrix diagonal. This
	 * second version should only consider the elements of the main diagonal. So,
	 * the complexity is linear.
	 * 
	 * @return
	 */
	public int sumDiagonal2() {
		int index = 0;
		int sum = 0;
		while (index < getSize()) {
			sum += matrix[index][index];
			index++;
		}
		return sum;
	}

	/**
	 * this method iterates through the matrix starting at position (i, j) according
	 * to the following number meanings: 1 – move up; 2 – move right; 3 – move down;
	 * 4 – move left. Traversed elements would be set to -1 value. The process will
	 * finish if it goes beyond the limits of the matrix or an already traversed
	 * position is reached.
	 * 
	 * @param i
	 * @param j
	 */
	public void travelPath(int i, int j) {
		if (inBounds(i, j)) {
			if (matrix[i][j] != VISITED) {

				move(matrix[i][j], i, j);
			}
		}

	}

	private boolean inBounds(int i, int j) {
		if (i >= n || i < 0 || j >= n || j < 0) {
			return false;

		}
		return true;
	}

	private void move(int current, int i, int j) {

		switch (current) {
		case MOVE_UP:
			markAsVisited(i, j);
			travelPath(i - 1, j);

			break;

		case MOVE_RIGHT:
			markAsVisited(i, j);
			travelPath(i, j + 1);
			break;

		case MOVE_DOWN:
			markAsVisited(i, j);
			travelPath(i + 1, j);
			break;

		case MOVE_LEFT:
			markAsVisited(i, j);
			travelPath(i, j - 1);
			break;

		default:
			break;
		}

	}

	private void markAsVisited(int i, int j) {
		matrix[i][j] = VISITED;
	}

}
