package algstudent.s5;

import java.util.Random;

public class LCS {
	
	CellTable[][] table; //table with local values
	String str1; // 1st string
	String str2; // 2nd string
	String result; // result string
	int size1; //str1 size
	int size2; //str2 size
	
	/**
	 * Constructor
	 * @param s1 1st string to compare
	 * @param s2 2nd string to compare
	 */
	public LCS(String s1, String s2) {
		str1 = "*" + s1; //str1. 1st column is headed with asterisk
		str2 = "*" + s2; //str2. 1st row is headed with asterisk
		System.out.println("String1: " + str1);
		System.out.println("String2: " + str2);
		size1 = str1.length();
		size2 = str2.length();
		result = "";
		table = new CellTable[size1][size2]; // table used for dynamic programming 
	}
	
	/**
	 * Constructor used to measure times
	 * @param n size for the strings that are generated randomly 
	 */
	public LCS(int n) {
		str1 = "*" + this.genRandomSeq(n);
		str2 = "*" + this.genRandomSeq(n);
		size1 = str1.length();
		size2 = str2.length();
		result = "";
		table = new CellTable[size1][size2]; // table used for dynamic programming para guardar todos los valores de la tabla din�mica 
	}
	
	/**
	 * Generates a random sequence
	 * @param n sequence size
	 * @return random sequence, sting of characters
	 */
	private String genRandomSeq(int n){
		char[] dna_elements = {'A', 'C', 'G', 'T'};
		String result = "";
		Random r = new Random();
		for (int i=0; i<n; i++)
			result += dna_elements[r.nextInt(4)];
		return result;
	}
	
	/**
	 * Table values initialization
	 */
	public void initTable() {
		for (int j=0; j<size2; j++)
			for(int i=0; i<size1; i++)
				table[i][j] = new CellTable();
	}
	
	
	/**
	 * Print the table with the results
	 */
	public void printTable() {
		System.out.printf("%11s", "*");
		for (int i=0; i<size1; i++) 
			System.out.printf("%11s", str1.substring(i, i+1)); // chars str1 (horizontal)
		System.out.println();
		
		for (int j=0; j<size2; j++) {
			System.out.printf("%11s", str2.substring(j, j+1)); // chars str2 (vertical)
			for(int i=0; i<size1; i++)
				System.out.printf("%4d(%2d,%2d)", table[i][j].value, table[i][j].iPrev, table[i][j].jPrev); // table values
			System.out.println();
		}
	}
	
	
	/**
	 * Print current MSC result
	 */
	public void printLongestSubseq(){
		System.out.println(result);
	} 

	public class CellTable{
		public static final int NO_COMMON=0;
		public int value; // value for dynamic programming
		public int iPrev; //"pointer" to string 1 character used to compute value 
		public int jPrev; //"pointer" to string 2 character used to compute value
		/**
		 * For cells in between first row and first column
		 * @param value
		 * @param iPrev
		 * @param jprev
		 */
		public CellTable(int value , int iPrev , int jprev) {
			this.value=value;
			this.iPrev=iPrev;
			this.jPrev=jPrev;
			
		}
		/**
		 * For cells in the first row and first column
		 * @param value
		 * @param iPrev
		 * @param jprev
		 */
		public CellTable() {
			this.value=NO_COMMON;
			this.iPrev=NO_COMMON;
			this.jPrev=NO_COMMON;
			
		}
	}
	
	/**
	 * Fill table values for dynamic programming
	 * //compare the values of the previous row in the same column and the previous column in the same row
				*you pick the greatest and that will be the new value.
				*IMPORTANT : you also have to check if the current row char is the same to the column one (f.ex , both are G)
				//Then you increment the previous value.
	 */
	public void fillTable(){
	   char[] sec_1=str1.toCharArray();
	    char[] sec_2=str2.toCharArray();
		initializeFirstRowAndCol();//first row an first column will always be full of zeroes.
		CellTable prev=table[0][0];
		for (int row = 1; row < table.length; row++) {
			for (int col = 1; col < table[row].length; col++) {
				
				if(sec_1[row]==sec_2[col]) {//if the characters of the sequences match, we add one to the previous cell in diagonal and assign it to this.
					table[row][col]=new CellTable(prev.value+1, row-1, col-1);
					
				}else {
					//if no match, the value remains equal to the previous.(in diagonal)
					if(table[ row-1][col].value>table[row][col-1].value) {
						//we put the value of the greatest.
						table[row][col]=new CellTable(table[row-1][col].value, row-1, col-1);
					}else {
						table[row][col]=new CellTable(table[row][col-1].value, row-1, col-1);
					}
				}
				
				prev=table[row][col];
				
			}
		}
	}

	private void initializeFirstRowAndCol() {
		for (int row = 0; row < table.length; row++) {
			for (int col = 0; col < table.length; col++) {
				if(row==0 || col==0) {					
					table[row][col]=new CellTable();//This constructor initializes all values to 0.
					
				}
				
			}
		}
	}
	
	/**
	 * Get the index for the maximum of three numbers
	 * @param num1 e.g. input L1=MSC(S1�, S2). S1� S1 without its last char
	 * @param num2 e.g. input L1=MSC(S1, S2'). S2' S2 without its last char
	 * @param num3 e.g. input L3=MSC(S1�, S2�) or L3+1 when both current chars are equal
	 * @return index of maximum
	 */
	@SuppressWarnings("unused")
	private int longest(int num1, int num2, int num3) {
		return -1;
		// TODO (optional): from three different values (e.g. partial MSC lengths) gets index for the longest
	}
	
	/**
	 * Find the MSC from the table (dynamic programming)
	 * @param v if True verbose mode activated (To show the path trough the different cells)
	 */
	public void findLongestSubseq(boolean v){
		// TODO: After the table is filled, from table last element traces the MSC found
	}

}
