package algstudent.s4;



public class SegmentPlacementTimes {
	public static final int GREEDY_1 = 1;
	public static final int GREEDY_2 = 2;
	public static final int GREEDY_3 = 3;
	static int nTimes=40000;
	private static SegmentsPlacement segPlacement;
	
	public static void main(String[] args) {
		long t;
		for (int n = 100; n <Integer.MAX_VALUE; n *= 2) {
			System.out.println(String.format("For a size of n=%d",n));
				t=measureTimes(nTimes,GREEDY_1, n);
				System.out.println(String.format("-Time taken for greedy1 :%d ms", t));
				t=measureTimes(100, GREEDY_2, n);
				System.out.println(String.format("-Time taken for greedy2 :%d ms", t));
				t=measureTimes(100, GREEDY_3, n);
				System.out.println(String.format("-Time taken for greedy3 :%d ms", t));
				System.out.println(
						"##################################################################################################");

		}

	}

	public static long measureTimes(int iterations,int greedy ,int n) {
		segPlacement=new SegmentsPlacement(n);
		long t=0,t1,t2;
		switch (greedy) {
		case GREEDY_1:
			
			for (int i = 1; i <= iterations ; i++) {
				t1 = System.currentTimeMillis();
				segPlacement.greedy1();
				t2 = System.currentTimeMillis();
				t += t2-t1;
				
			}
			return t;
		case GREEDY_2:		
			for (int i = 1; i <= iterations ; i++) {
				t1 = System.currentTimeMillis();
				segPlacement.greedy2();
				t2 = System.currentTimeMillis();
				t += t2-t1;
				
			}
			return t;
		case GREEDY_3:
			
			for (int i = 1; i <= iterations ; i++) {
				t1 = System.currentTimeMillis();
				segPlacement.greedy3();
				t2 = System.currentTimeMillis();
				t += t2-t1;
				
			}
			return t;

		default:
			System.out.println("An error happened.");
			return -1;
		}
		
	
		
	}
}
