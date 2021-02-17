package algstudent.s1;

public class Vector4 {
	static int[] v;

	public static void main(String[] arg) {
		 int nTimes = Integer.parseInt(arg[0]); //Size of the problem in the first argument
		long t1, t2;		
		int sum=0;
		for (int n = 10; n <Integer.MAX_VALUE; n*=5) {
			v = new int[n];
			Vector1.fillIn(v);//fill with numbers the array created.

			t1 = System.currentTimeMillis();
			for (int repetitions = 1; repetitions <=nTimes; repetitions++) {
				sum=Vector1.sum(v);
				
			}
			sum = Vector1.sum(v);
			t2 = System.currentTimeMillis();
			System.out.printf("SIZE-%d TIME-%d milliseconds. SUM-%d NTIMES-%d \n", n, t2 - t1, sum, nTimes);
		}
	
	}
}
