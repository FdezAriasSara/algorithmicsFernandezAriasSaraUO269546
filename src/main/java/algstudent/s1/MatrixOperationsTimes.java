package algstudent.s1;

import algstudent.s0.MatrixOperations;

public class MatrixOperationsTimes {

	public static void main(String[] arg) {
		
		
		for (int i =10; i < Integer.MAX_VALUE; i*=3) {
			MatrixOperations matrix=new MatrixOperations(i,0,4);
			long t1, t2;
			t1 = System.currentTimeMillis();
			int diagSum1=matrix.sumDiagonal1();		
			t2 = System.currentTimeMillis();
			System.out.printf(" DIAGONAL 1: SIZE OF MATRIX-%d TIME-%d milliseconds. SUM-%d\n", i, t2 - t1, diagSum1);
		


			t1 = System.currentTimeMillis();
			int diagSum2=matrix.sumDiagonal2();		
			t2 = System.currentTimeMillis();
		System.out.printf(" DIAGONAL 2: SIZE OF MATRIX-%d TIME-%d milliseconds. SUM-%d\n", i, t2 - t1, diagSum2);
			System.out.println("################################################################################################");
		}

		
	}

}
