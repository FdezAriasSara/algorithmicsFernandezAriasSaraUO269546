package algstudent.s12;

import java.util.Random;

public class Loop5 {

	public static void loop5(int n) {

		Random rn = new Random();
		@SuppressWarnings("unused")
		int cont = 0;
		for (int i = n * n; i > n / 3; i--) {
			
			for (int a = 0 ; a<= n ;a++) {
				int j = 1;
				while (j < n * n * n) {
					cont += rn.nextInt();
					j = j * 2;
				}
			}
		}
	}

	

	public static void main(String arg []){
		long t1, t2;
		int nTimes = Integer.parseInt(arg[0]);
	
		for (int n=1; n<=Integer.MAX_VALUE; n*=2) {
			t1 = System.currentTimeMillis();
	 
			for (int repetitions=1; repetitions<=nTimes; repetitions++) {
				loop5(n);
			} 
	 
			t2 = System.currentTimeMillis();
			System.out.println("n="+n+ "**TIME="+(t2-t1)+" **nTimes="+nTimes);	
	 }  // for
}
}
