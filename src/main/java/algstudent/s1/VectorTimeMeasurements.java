package algstudent.s1;
//class created for Activity 3. Taking small execution times
public class VectorTimeMeasurements {
	static int[] v;
	static int[] vForMax=new int[2];

	public static void main(String[] arg) {

		long t1, t2;		
		int sum=0;
		
		for (int n = 10; n <Integer.MAX_VALUE; n*=3) {
			v = new int[n];
			t1 = System.currentTimeMillis();
			Vector1.fillIn(v);//fill with numbers the array created.
			t2 = System.currentTimeMillis();
			System.out.printf("FILLIN- SIZE-%d TIME-%d milliseconds \n", n, t2 - t1);
			t1 = System.currentTimeMillis();
			sum = Vector1.sum(v);
			t2 = System.currentTimeMillis();
			System.out.printf("SUM-SIZE-%d TIME-%d milliseconds. SUM-%d\n", n, t2 - t1, sum);
			t1 = System.currentTimeMillis();
			Vector1.maximum(v, vForMax);
			t2 = System.currentTimeMillis();
			System.out.printf("MAXIMUM -SIZE-%d TIME-%d milliseconds. \n", n, t2 - t1);
			System.out.println("#####################################################");
		}
	
	}
}
