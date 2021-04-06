package algstudent.s5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LCSRec {
	private static final int LAST = 0;
	List<Integer> lsc = new ArrayList<Integer>();//to store the sequence
	/**
	 * Generates a random sequence
	 * @param n sequence sizeO
	 * @return random sequence, sting of characters
	 */
	public String genRandomSeq(int n){
		char[] dna_elements = {'A', 'C', 'G', 'T'};
		String result = "";
		Random r = new Random();
		for (int i=0; i<n; i++)
			result += dna_elements[r.nextInt(4)];
		return result;
	}
	
	 /*
     * Find a MSC directly by a recursive approach 
     */
    public String findLongestSubseq(String s1, String s2){
       //indexes.
    	int l1=s1.length()-1;
    	int l2=s2.length()-1;
    	//to access the char.
    	char[] s1C=s1.toCharArray();
    	char[] s2C=s2.toCharArray();
    	if(l1== LAST || l2==LAST) {//stop when all was traveled.
    		return "";
    	}
    	if(s1C[l1]== s2C[l2]) {
    		lsc.add(l2);//WHEN AN ELEMENT FROM BOTH SEQUENCES MATCHES it's added to the lsc(index)
    		return findLongestSubseq(s1.substring(LAST, l1), s2.substring(LAST, l2)) + s1.substring(l1);
    	}
    	else {
    		//if no match is found we divide the problem again , twice-> we evaluate two substrings.
    		String sub1 = findLongestSubseq(s1,s2.substring(LAST, l2));
    		String sub2 = findLongestSubseq(s1.substring(LAST, l1), s2);
    		if( sub1.length()>sub2.length() ) {//pick the longest substring(as in the table we chose the greater value.
    			return sub1;
    		}
    		else {
    			return sub2;
    		}
    	}
       
    }


    
    
    
    
	
	/**
	 * Get the index for the longest of three strings
	 * @param l1 e.g. input L1=MSC(S1’, S2). S1’ S1 without its last char
	 * @param l2 e.g. input L1=MSC(S1, S2'). S2' S2 without its last char
	 * @param l3 e.g. input L3=MSC(S1’, S2’) or L3+1 when both current chars are equal
	 * @return index of the longest string
	 */
	@SuppressWarnings("unused")
	private int longest(String l1, String l2, String l3) {
		return -1;
		// TODO (optional): from three different sequences (e.g. subsequences) gets index for the longest
	}

}
