package algstudent.s3;

public class Substraction4 {
	//Using substraction scheme analysis provided, we have that 
	//in order to reach a complexity like O(3^(n/b)) we need a>1.
	//In order to implement a method that has a complexity if O(3n/2), 
	//a=3 and  b=2 . Then , subproblems will have a size of n-2.
	//In this case, k =1 . Since the complexity of the method without
	//taking into account recursive calls is O(n)
	public static long rec4(int n) {
		long cont = 0;
		if (n<=0) 
			cont++;
		else { 
			for (int i=0;i<n;i++) 
				cont++; // O(n)
			rec4(n-2);//subproblem 1
			rec4(n-2);//subproblem 2
			rec4(n-2); //subproblem 3.
		}
		return cont;   
	}
	
}
