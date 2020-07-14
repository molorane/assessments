/*
	The Kata is a polymorph of the spiral matrix problem of https://stackoverflow.com/questions/37928258/algorithm-to-generate-a-spiral-matrix-injava 
	-	Therein, the solution is a nested for() loop procedure
		-	The nested for() loops construct the solution
		-	The solution is spontaneous; it does not base on postulation
	-	Herein, an attempt to derive a physical formula is in question
		-	There are no nested loops in solution derivation
		-	Nested loops are only for presentation
*/

/*

Test Results:
SpiralizorProdTests
test100
Test for n = 100: arrays first differed at element [0][0]; expected:<1> but was:<0>
Stack Trace
Completed in 23ms
test1through10
Test Passed
Completed in 2ms
Completed in 39ms

*/

/*******
 *******

EMPIRICAL DATA AND PHYSICAL POSTULATION THEREON
===============================================
 
//5x5
00000	[1,1,1,1,1]		//patternA = Integer.toBinaryString(2^5-1)
....0	[0,0,0,0,1]		//patternB = Integer.toBinaryString(1)
000.0	[1,1,1,0,1]		//1] = patternA-(2^1)
0...0	[1,0,0,0,1]		//pattern1 = Integer.toBinaryString(2^(5-1)+1)
00000	[1,1,1,1,1]		//pattern0 = Integer.toBinaryString(2^5-1)

//6x6
000000	[1,1,1,1,1,1]	//patternA = Integer.toBinaryString(2^6-1)	
.....0	[0,0,0,0,0,1]	//patternB = Integer.toBinaryString(1)
0000.0	[1,1,1,1,0,1]	//1] = patternA-(2^1)
0..0.0	[1,0,0,1,0,1]	//2] = patternB+(2^(6-1))+(2^(2))
0....0	[1,0,0,0,0,1]	//pattern1 = Integer.toBinaryString(2^(6-1)+1)
000000	[1,1,1,1,1,1]	//pattern0 = Integer.toBinaryString(2^6-1)

//7x7
0000000	[1,1,1,1,1,1,1]	//patternA = Integer.toBinaryString(2^7-1)
......0	[0,0,0,0,0,0,1]	//patternB = Integer.toBinaryString(1)
00000.0	[1,1,1,1,1,0,1]	//1] = patternA-(2^1)
0...0.0	[1,0,0,0,1,0,1]	//2] = patternB+(2^(7-1))+(2^(2))
0.000.0	[1,0,1,1,1,0,1]	//3] = pattern0-(2^1)-(2^(7-2))
0.....0	[1,0,0,0,0,0,1]	//pattern1 = Integer.toBinaryString(2^(7-1)+1)
0000000	[1,1,1,1,1,1,1]	//pattern0 = Integer.toBinaryString(2^6-1)

//10x10
0000000000	[1,1,1,1,1,1,1,1,1,1]//pA = Integer.toBinaryString(2^10-1)
.........0	[0,0,0,0,0,0,0,0,0,1]//pB = Integer.toBinaryString(1)
00000000.0	[1,1,1,1,1,1,1,1,0,1]//1] = pA-(2^1)
0......0.0	[1,0,0,0,0,0,0,1,0,1]//2] = PB+(2^(10-1))+(2^(2))
0.0000.0.0	[1,0,1,1,1,1,0,1,0,1]//3] = 1]-(2^3)-(2^8)
0.0..0.0.0	[1,0,1,0,0,1,0,1,0,1]//4] = 2]+(2^(10-3))+(2^(4))
0.0....0.0	[1,0,1,0,0,0,0,1,0,1]//5] = p1+(2^2)+(2^(10-3))
0.000000.0	[1,0,1,1,1,1,1,1,0,1]//6] = p0-(2^1)-(2^(10-2))
0........0	[1,0,0,0,0,0,0,0,0,1]//p1 = Integer.toBinaryString(2^(10-1)+1)
0000000000	[1,1,1,1,1,1,1,1,1,1]//p0 = Integer.toBinaryString(2^10-1)
*******
*******/

public class SpiralMatrix{
	public static int[][] getPattern(final int n){
	
		//exceptions of the physical system postulation
		if(n==1) return new int[][]{{1}};
		if(n==2) return new int[][]{{1,1},{0,1}};
		if(n==3) return new int[][]{{1,1,1},{0,0,1},{1,1,1}};
		
		
		//array of patterns
		int[][] pattern = new int[n][];
		
		//the 4 patterns
		
		//patternA
		pattern[0] = new int[]{(int)Math.pow(2,n)-1};
		
		//patternB
		pattern[1] = new int[]{1};
		
		//pattern1
		pattern[n-1-1] = new int[]{(int)Math.pow(2,n-1)+1};
		
		//pattern0
		pattern[n-1] = new int[]{(int)Math.pow(2,n)-1};
		
		
		//generate the n-4 patterns
		
		//the patterns deriving from patternA
		for(int i=2; i<n/2+1; i+=2)
			pattern[i] =
				new int[]{pattern[i-2][0]-
				(int)Math.pow(2,i-1)-
				(i!=2?1:0)*(int)Math.pow(2,n-(i-2))}
			;
			
		//the patterns deriving from patternB
		for(int i=3; i<n/2+1; i+=2)
			pattern[i] =
				new int[]{pattern[i-2][0]+
				(int)Math.pow(2,i-1)+
				(int)Math.pow(2,n-(i-2))}
			;
			
		//the patterns deriving from pattern0
		for(int i=n-3; n/2<i; i-=2)
			pattern[i] =
				new int[]{pattern[i+2][0]-
				(int)Math.pow(2,n-(i+2))-
				(int)Math.pow(2,i+1)}
			;
			
		//the patterns deriving from pattern1
		for(int i=n-4; n/2<i; i-=2)
			pattern[i] =
				new int[]{pattern[i+2][0]+
				(int)Math.pow(2,n-(i+2))+
				(int)Math.pow(2,i+1)}
			;
			
		//format patterns
		char[] currentpattern = new char[n];
		java.util.Arrays.fill(currentpattern, '0');
		for(int i=0; i<n; ++i){
			currentpattern =
				(
					new String(currentpattern)+
					Integer.toBinaryString(pattern[i][0])
				)
				.substring(
					(
						new String(currentpattern)+
						Integer.toBinaryString(pattern[i][0])
					)
					.length()-n
				)
				.toCharArray()
			;
			pattern[i] = new int[n];
			for(int j=0; j<n; ++j)
				pattern[i][j] =
					Integer.parseInt(""+currentpattern[j])
				;
				
			//reset the current pattern
			java.util.Arrays.fill(currentpattern, '0');
		}
			
		return pattern;
	}
	
	public static void main(String ... args){
		System.out.println(java.util.Arrays.deepToString(getPattern(6)));
	}
}
