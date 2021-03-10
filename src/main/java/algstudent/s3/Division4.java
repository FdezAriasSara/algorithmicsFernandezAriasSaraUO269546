package algstudent.s3;


public class Division4 {
	
	//using divide and conquer division scheme:
	//We are told that a (nº of subproblems) should be 4. 
	//therefore , in order to obtain a complexity of O(n^2) we need a<b^k.
	//in order to have that complexity , we also need k to be equal to 2.
	//Therefore b MUST be greater than 2, since 2^2=4.
	//I've chosen b=3,. since 3^2=>9 and 4<9
	
		public static long rec4 (int n) { 
			long cont = 0;
			if (n<=0) cont++;
			else { 
				for (int i=1;i<n;i++) 
					for (int j=1;j<n;j++) 
						cont++ ;  //O(n^2)  -> k=exp of the complexity of the overall scheme excluding recursive calls,
		    	rec4(n/3);//subproblem 1
		    	rec4(n/3);//subproblem 2
		    	rec4(n/3);//subproblem 3
		    	rec4(n/3);//subproblem 4
			}
			return cont;   
		}
		

}
